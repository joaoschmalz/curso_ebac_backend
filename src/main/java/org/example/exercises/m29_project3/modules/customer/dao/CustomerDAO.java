package org.example.exercises.m29_project3.modules.customer.dao;

import org.example.exercises.m29_project3.modules.customer.domain.Customer;
import org.example.exercises.m29_project3.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class CustomerDAO implements ICustomerDAO {
  @Override
  public Integer save(final Customer customer) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = this.getSqlInsert();
      statement = connection.prepareStatement(sql);
      this.addInsertParam(statement, customer);
      return statement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public Integer update(final Customer customer) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlUpdate();
      statement = connection.prepareStatement(sql);
      addUpdateParam(statement, customer);
      return statement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public Customer retrieve(final String customerCode) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    Customer customer = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSelect();
      statement = connection.prepareStatement(sql);
      addSelectParam(statement, customerCode);
      result = statement.executeQuery();

      if (result.next()) {
        customer = new Customer();
        customer.setId(result.getLong("id"));
        customer.setName(result.getString("name"));
        customer.setCode(result.getString("code"));
      }
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, result);
    }

    return customer;
  }

  @Override
  public List<Customer> retrieveAll() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    List<Customer> customers = new ArrayList<>();
    Customer customer = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSelectAll();
      statement = connection.prepareStatement(sql);
      result = statement.executeQuery();

      while (result.next()) {
        customer = new Customer();
        customer.setId(result.getLong("id"));
        customer.setName(result.getString("name"));
        customer.setCode(result.getString("code"));
        customers.add(customer);
      }
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, result);
    }

    return customers;
  }

  @Override
  public Integer delete(Customer customer) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlDelete();
      statement = connection.prepareStatement(sql);
      this.addDeleteParam(statement, customer);
      return statement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  private String getSelect() {
    return " SELECT * "
         + " FROM customer "
         + " WHERE code = ? ";
  }

  private String getSelectAll() {
    return " SELECT * "
         + " FROM customer ";
  }

  private String getSqlInsert() {
    return " INSERT INTO customer (code, name) "
         + " VALUES (?, ?) ";
  }

  private String getSqlUpdate() {
    return " UPDATE customer "
         + " SET code = ?, name = ? "
         + " WHERE id = ? ";
  }

  private String getSqlDelete() {
    return " DELETE FROM customer "
         + " WHERE code = ? ";
  }

  private void addSelectParam(final PreparedStatement statement, final String code) throws SQLException {
    statement.setString(1, code);
  }

  private void addInsertParam(final PreparedStatement statement, final Customer customer) throws SQLException {
    statement.setString(1, customer.getCode());
    statement.setString(2, customer.getName());
  }

  private void addUpdateParam(final PreparedStatement statement, final Customer customer) throws SQLException {
    statement.setString(1, customer.getCode());
    statement.setString(2, customer.getName());
    statement.setLong(3, customer.getId());
  }

  private void addDeleteParam(final PreparedStatement statement, final Customer customer) throws  SQLException {
    statement.setString(1, customer.getCode());
  }

  private void closeConnection(final Connection connection, final PreparedStatement statement, final ResultSet result) {
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
