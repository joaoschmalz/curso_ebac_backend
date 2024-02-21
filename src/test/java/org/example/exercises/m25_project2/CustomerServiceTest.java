package org.example.exercises.m25_project2;

import org.example.exercises.m25_project2.dao.CustomerDAOMock;
import org.example.exercises.m25_project2.dao.ICustomerDAO;
import org.example.exercises.m25_project2.domain.Customer;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;
import org.example.exercises.m25_project2.services.CustomerService;
import org.example.exercises.m25_project2.services.ICustomerService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerServiceTest {

  private final ICustomerService customerService;
  private Customer customer;

  public CustomerServiceTest() {
    ICustomerDAO dao = new CustomerDAOMock();
    this.customerService = new CustomerService(dao);
  }

  @Before
  public void init() {
    this.customer = new Customer();
    this.customer.setCpf(12314L);
    this.customer.setName("Xablau");
    this.customer.setEmail("xablau@email.com");
  }

  @Test
  public void ensureCanRetrieveCustomer() {
    Customer retrieved = this.customerService.retrieveBy(this.customer.getCpf());
    assertNotNull(retrieved);
  }

  @Test
  public void ensureCustomerCanBeSaved() throws KeyTypeNotFoundException {
    boolean result = this.customerService.save(this.customer);
    assertTrue(result);
  }

  @Test
  public void ensureCustomerCanBeDeleted() {
    this.customerService.delete(this.customer.getCpf());
  }

  @Test
  public void ensureCustomerCanBeUpdated() throws KeyTypeNotFoundException {
    this.customer.setName("Tester");
    this.customerService.update(this.customer);

    assertEquals("Tester", this.customer.getName());
  }
}
