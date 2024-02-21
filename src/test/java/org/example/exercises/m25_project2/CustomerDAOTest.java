package org.example.exercises.m25_project2;

import org.example.exercises.m25_project2.dao.CustomerDAO;
import org.example.exercises.m25_project2.dao.ICustomerDAO;
import org.example.exercises.m25_project2.domain.Customer;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class CustomerDAOTest {

  private final ICustomerDAO customerDAO;
  private Customer customer;

  public CustomerDAOTest() {
    customerDAO = new CustomerDAO();
  }

  @Before
  public void init() throws KeyTypeNotFoundException {
    this.customer = new Customer();
    customer.setCpf(342391L);
    customer.setName("Xablau");
    customer.setEmail("xablau@email.com");
    this.customerDAO.save(customer);
  }

  @Test
  public void ensureCanRetrieveCustomerByCPF() {
    Customer retrieved = customerDAO.retrieve(customer.getCpf());
    assertNotNull(retrieved);
  }

  @Test
  public void ensureCustomerCanBeSaved() throws KeyTypeNotFoundException {
    customer.setCpf(12345L);
    boolean result = customerDAO.save(customer);
    assertTrue(result);
  }

  @Test
  public void ensureCustomerCanBeDeleted() {
    customerDAO.delete(customer.getCpf());
  }

  @Test
  public void ensureCustomerCanBeUpdated() throws KeyTypeNotFoundException {
    customer.setName("Tester");
    customerDAO.update(customer);
    assertEquals("Tester", customer.getName());
  }

  @Test
  public void ensureCanRetrieveAllCustomers() {
    Collection<Customer> customers = customerDAO.retrieveAll();
    assertTrue(nonNull(customers));
  }
}
