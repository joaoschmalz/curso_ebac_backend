package org.example.exercises.m14_customerCRUD;

import org.example.exercises.m14_customerCRUD.infra.localDatabase.entities.Customer;
import org.example.exercises.m14_customerCRUD.infra.localDatabase.repositories.CustomerRepository;
import org.example.exercises.m14_customerCRUD.repositories.ICustomerRepository;

import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class App {
  private static final ICustomerRepository repository = new CustomerRepository();

  public static void main(String[] args) {

    boolean exit = false;
    while (!exit) {
      System.out.println("Welcome to Customer management v1");
      System.out.println("Select one of the options below:");
      System.out.println("1 - Create new Customer");
      System.out.println("2 - Retrieve single Customer");
      System.out.println("3 - Retrieve all Customers");
      System.out.println("4 - Update target Customer");
      System.out.println("5 - Delete target Customer");
      System.out.println("6 - Exit");
      System.out.println("-----------------------------------");

      Scanner s = new Scanner(System.in);
      int option = s.nextInt();

      switch (option) {
        case 1:
          create();
          break;
        case 2:
          retrieveSingle();
          break;
        case 3:
          retrieveAll();
          break;
        case 4:
          update();
          break;
        case 5:
          delete();
          break;
        case 6:
          System.out.println("Good bye...");
          exit = true;
          break;
        default:
          System.out.println("Invalid option");
      }
    }
  }

  private static void create(){
    try {
      repository.create(createCustomerObj(true));
      System.out.println("Customer created !!!");
      System.out.println("-----------------------------------");
    } catch (Exception e) {
      System.out.println("Failed to create new Customer");
      System.out.println(e.getMessage());
      System.out.println("-----------------------------------");
    }

  }

  public static void retrieveSingle() {
    Scanner s = new Scanner(System.in);
    try {
      System.out.println("Enter customer CPF");
      String cpf = s.nextLine();
      final Customer customer = repository.retrieveSingle(cpf);

      if (isNull(customer)) throw new Exception("Customer not found");
      System.out.println(customer);
      System.out.println("-----------------------------------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("-----------------------------------");
    }
  }

  public static void retrieveAll() {
    final List<Customer> customers = repository.retrieveAll();

    for (Customer c : customers) System.out.println(c);
    System.out.println("-----------------------------------");
  }

  public static void update() {
    Scanner s = new Scanner(System.in);
    try {
      System.out.println("Enter Customer CPF:");
      String cpf = s.nextLine();
      Customer retrievedCustomer = repository.retrieveSingle(cpf);

      if(isNull(retrievedCustomer)) {
        System.out.println("Customer not found");
        return;
      }

      final Customer customer = createCustomerObj(false);
      customer.setCpf(cpf);
      repository.update(customer);
      System.out.println("Customer updated !!!");
      System.out.println("-----------------------------------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Failed to update customer !!!");
      System.out.println("-----------------------------------");
    }
  }

  public static void delete() {
    Scanner s = new Scanner(System.in);
    System.out.println("Enter Customer CPF");
    repository.delete(s.nextLine());
    System.out.println("Customer deleted !!!");
    System.out.println("-----------------------------------");
  }
  private static Customer createCustomerObj(final boolean needCpf) {
    Scanner s = new Scanner(System.in);
    final Customer customer = new Customer();
    System.out.println("Enter Customer information");

    System.out.println("Customer name:");
    customer.setName(s.nextLine());
    if (needCpf) {
      System.out.println("Customer CPF:");
      customer.setCpf(s.nextLine());
    }
    System.out.println("Customer phone:");
    customer.setPhone(s.nextLine());
    System.out.println("Customer address:");
    customer.setAddress(s.nextLine());
    System.out.println("Customer city:");
    customer.setCity(s.nextLine());
    System.out.println("Customer state:");
    customer.setState(s.nextLine());

    return customer;
  }
}
