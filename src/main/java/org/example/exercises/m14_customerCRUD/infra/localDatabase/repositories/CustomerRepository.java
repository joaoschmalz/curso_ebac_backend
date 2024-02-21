package org.example.exercises.m14_customerCRUD.infra.localDatabase.repositories;

import org.example.exercises.m14_customerCRUD.infra.localDatabase.entities.Customer;
import org.example.exercises.m14_customerCRUD.repositories.ICustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.example.exercises.m14_customerCRUD.utils.Utils.isNullOrEmpty;

public class CustomerRepository implements ICustomerRepository {

  private final Map<String, Customer> customers = new HashMap<>();

  @Override
  public void create(Customer customer) throws Exception {
    this.checkers(customer);
    this.customers.put(customer.getCpf(), customer);
  }

  @Override
  public Customer retrieveSingle(final String cpf) {
    return this.customers.get(cpf);
  }

  @Override
  public List<Customer> retrieveAll() {
    return new ArrayList<>(this.customers.values());
  }

  @Override
  public void delete(final String cpf) {
    this.customers.remove(cpf);
  }

  @Override
  public void update(final Customer customer) throws Exception {
    final Customer existentCustomer = this.retrieveSingle(customer.getCpf());

    if (isNull(existentCustomer)) throw new Exception("Customer not found for given CPF");

    this.customers.replace(customer.getCpf(), customer);
  }


  private void checkers(final Customer customer) throws Exception {
    this.doesExist(customer.getCpf());
    this.checkRequired(customer);
  }

  private void doesExist(final String cpf) throws Exception {
    final Customer customer = this.retrieveSingle(cpf);

    if (nonNull(customer)) throw new Exception("Already exists customer for the informed CPF");
  }

  private void checkRequired(final Customer customer) throws Exception {
    if(isNullOrEmpty(customer.getName())) throw new Exception("Customer name can't be null or empty");
    if(isNullOrEmpty(customer.getCpf())) throw new Exception("Customer CPF can't be null or empty");
  }
}
