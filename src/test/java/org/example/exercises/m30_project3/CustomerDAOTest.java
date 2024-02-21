package org.example.exercises.m30_project3;

import org.example.exercises.m30_project3.dao.CustomerDAO;
import org.example.exercises.m30_project3.dao.ICustomerDAO;
import org.example.exercises.m30_project3.dao.generic.jdbc.ConnectionFactory;
import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class CustomerDAOTest {

  private final ICustomerDAO customerDAO = new CustomerDAO();

  @After
  public void clearDatabase() throws DAOException {
    Connection connection = this.getConnection();
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement("DELETE FROM customer");
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was an error deleting object", e);
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Test
  public void retrieveAll() throws DAOException, KeyNotFoundException, InterruptedException {
    Customer customer01 = createCustomerForTests();
    Boolean result01 = this.customerDAO.save(customer01);
    assertTrue(result01);

    Customer customer02 = createCustomerForTests();
    customer02.setCpf(23456789101L);
    customer02.setName("Xablau Tester");
    Boolean result02 = this.customerDAO.save(customer02);
    assertTrue(result02);

    Collection<Customer> list = this.customerDAO.retrieveAll();
    assertTrue(nonNull(list));
    assertEquals(2, list.size());
  }

  @Test
  public void searchCustomer() throws DuplicatedEntryException, TableException, KeyNotFoundException, DAOException {
    Customer customer = this.createCustomerForTests();

    customerDAO.save(customer);
    Customer searchedCustomer = this.customerDAO.retrieve(customer.getCpf());
    assertNotNull(searchedCustomer);
  }

  @Test
  public void saveCustomer() throws DAOException, DuplicatedEntryException, TableException, KeyNotFoundException {
    Customer customer = this.createCustomerForTests();
    Boolean result = this.customerDAO.save(customer);
    assertTrue(result);

    Customer searched = this.customerDAO.retrieve(customer.getCpf());
    assertNotNull(searched);
  }

  @Test
  public void deleteCustomer() throws DAOException, DuplicatedEntryException, TableException, KeyNotFoundException {
    Customer customer = createCustomerForTests();
    Boolean result = this.customerDAO.save(customer);
    assertTrue(result);

    Customer searchedCustomer = this.customerDAO.retrieve(customer.getCpf());
    assertNotNull(searchedCustomer);

    this.customerDAO.delete(customer.getCpf());
    searchedCustomer = this.customerDAO.retrieve(customer.getCpf());
    assertNull(searchedCustomer);
  }

  @Test
  public void updateCustomer() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    Customer customer = createCustomerForTests();
    Boolean result = this.customerDAO.save(customer);
    assertTrue(result);

    Customer searchedCustomer = this.customerDAO.retrieve(customer.getCpf());
    assertNotNull(searchedCustomer);

    searchedCustomer.setName("Xablau Tester");
    this.customerDAO.update(searchedCustomer);

    Customer updatedCustomer = this.customerDAO.retrieve(searchedCustomer.getCpf());
    assertNotNull(updatedCustomer);
    assertEquals("Xablau Tester", updatedCustomer.getName());
  }

  private Customer createCustomerForTests() {
    Customer customer = new Customer();
    customer.setCpf(12345678910L);
    customer.setName("Xablau");
    customer.setCity("Xablauville");
    customer.setAddress("Address");
    customer.setState("XB");
    customer.setAddressNumber(0);
    customer.setPhone(999999999L);
    customer.setActive(true);

    return customer;
  }

  protected Connection getConnection() throws DAOException {
    try {
      return ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new DAOException("Couldn't open database connection", e);
    }
  }

  protected void closeConnection(final Connection connection, final PreparedStatement statement, final ResultSet result) {
    try {
      if (nonNull(result) && !result.isClosed()) {
        result.close();
      }
      if (nonNull(statement) && !statement.isClosed()) {
        statement.close();
      }
      if (nonNull(connection) && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
