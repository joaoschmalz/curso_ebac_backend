package org.example.exercises.m29_project3;

import org.example.exercises.m29_project3.modules.customer.dao.CustomerDAO;
import org.example.exercises.m29_project3.modules.customer.dao.ICustomerDAO;
import org.example.exercises.m29_project3.modules.customer.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {

  private final ICustomerDAO customerDAO = new CustomerDAO();
  private final Customer customer = new Customer();

  @Before
  public void init() {
    this.customer.setCode("10");
    this.customer.setName("Xablau");
  }
  @After
  public void clearDatabase() throws Exception {
    List<Customer> customerList = this.customerDAO.retrieveAll();
    for (Customer customer : customerList) {
      this.customerDAO.delete(customer);
    }
  }

  @Test
  public void saveTest() throws Exception {
    Integer count = this.customerDAO.save(this.customer);
    assertEquals(1, (int) count);

    Customer customerDB = this.customerDAO.retrieve(this.customer.getCode());
    assertNotNull(customerDB.getCode());
    assertEquals(this.customer.getCode(), customerDB.getCode());
    assertEquals(this.customer.getName(), customerDB.getName());
  }

  @Test
  public void retrieveSingleTest() throws Exception {
    Integer count = this.customerDAO.save(this.customer);
    assertEquals(1, (int) count);

    Customer customerDB = this.customerDAO.retrieve(this.customer.getCode());
    assertNotNull(customerDB);
    assertEquals(this.customer.getCode(), customerDB.getCode());
    assertEquals(this.customer.getName(), customerDB.getName());
  }

  @Test
  public void retrieveAllTest() throws Exception{
    Integer count = this.customerDAO.save(this.customer);
    assertEquals(1, (int) count);

    Customer customer = new Customer();
    customer.setCode("11");
    customer.setName("Xablau Tester");
    Integer count02 = this.customerDAO.save(customer);
    assertEquals(1, (int) count02);

    List<Customer> customerList = this.customerDAO.retrieveAll();
    assertNotNull(customerList);
    assertEquals(2, customerList.size());
  }

  @Test
  public void updateTest() throws Exception {
    Integer count = this.customerDAO.save(this.customer);
    assertEquals(1, (int) count);

    Customer customerDB = this.customerDAO.retrieve(this.customer.getCode());
    assertNotNull(customerDB);
    assertEquals(this.customer.getCode(), customerDB.getCode());
    assertEquals(this.customer.getName(), customerDB.getName());

    customerDB.setCode("20");
    customerDB.setName("Xablau Tester");
    Integer countUpdate = this.customerDAO.update(customerDB);
    assertEquals(1, (int) countUpdate);

    Customer customerDB01 = this.customerDAO.retrieve(this.customer.getCode());
    assertNull(customerDB01);

    Customer customerDB02 = this.customerDAO.retrieve(customerDB.getCode());
    assertNotNull(customerDB02);
    assertEquals(customerDB.getId(), customerDB02.getId());
    assertEquals(customerDB.getCode(), customerDB02.getCode());
    assertEquals(customerDB.getName(), customerDB02.getName());
  }

  @Test
  public void delteTest() throws Exception {
    Integer count = this.customerDAO.save(this.customer);
    assertEquals(1, (int) count);

    Customer customer = this.customerDAO.retrieve(this.customer.getCode());
    assertNotNull(customer);

    this.customerDAO.delete(customer);

    Customer newCustomer = this.customerDAO.retrieve(this.customer.getCode());
    assertNull(newCustomer);
  }

}
