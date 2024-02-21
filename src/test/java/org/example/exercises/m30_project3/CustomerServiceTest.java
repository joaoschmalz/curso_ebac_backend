package org.example.exercises.m30_project3;

import org.example.exercises.m30_project3.dao.CustomerDAOMock;
import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.services.CustomerService;
import org.example.exercises.m30_project3.services.ICustomerService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerServiceTest {

  private final ICustomerService customerService = new CustomerService(new CustomerDAOMock());

  private Customer customer;

  @Before
  public void init() {
    customer = new Customer();
    customer.setCpf(12345678910L);
    customer.setName("Xablau");
    customer.setCity("Xablauville");
    customer.setAddress("Address");
    customer.setState("XB");
    customer.setAddressNumber(10);
    customer.setPhone(999999999L);
  }

  @Test
  public void retrieveCustomer() throws DAOException {
    Customer searchedCustomer = this.customerService.retrieveBy(this.customer.getCpf());
    assertNotNull(searchedCustomer);
  }

  @Test
  public void saveCustomer() throws DAOException, KeyNotFoundException {
    Boolean result = this.customerService.save(this.customer);
    assertTrue(result);
  }

  @Test
  public void deleteCustomer() throws DAOException {
    this.customerService.delete(this.customer.getCpf());
  }

  @Test
  public void updateCustomer() throws DAOException, KeyNotFoundException {
    this.customer.setName("Xablau Tester");
    this.customerService.update(this.customer);

    assertEquals("Xablau Tester", this.customer.getName());
  }

}
