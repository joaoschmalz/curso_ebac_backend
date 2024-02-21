package org.example.exercises.m29_project3.modules.product.dao;

import org.example.exercises.m29_project3.modules.product.domain.Product;
import org.example.exercises.m29_project3.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class ProductDAO implements IProductDAO {
  @Override
  public Integer save(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = this.getSqlInsert();
      statement = connection.prepareStatement(sql);
      this.addInsertParam(statement, product);
      return statement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public Integer update(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlUpdate();
      statement = connection.prepareStatement(sql);
      addUpdateParam(statement, product);
      return statement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public Product retrieveSingle(String code) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    Product product = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSelect();
      statement = connection.prepareStatement(sql);
      addSelectParam(statement, code);
      result = statement.executeQuery();

      if (result.next()) {
        product = new Product();
        product.setId(result.getLong("id"));
        product.setCode(result.getString("code"));
        product.setName(result.getString("name"));
        product.setDescription(result.getString("description"));
        product.setPrice(result.getBigDecimal("price"));
      }
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, result);
    }

    return product;
  }

  @Override
  public List<Product> retrieveAll() throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;
    List<Product> products = new ArrayList<>();
    Product product = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSelectAll();
      statement = connection.prepareStatement(sql);
      result = statement.executeQuery();

      while (result.next()) {
        product = new Product();
        product.setId(result.getLong("id"));
        product.setCode(result.getString("code"));
        product.setName(result.getString("name"));
        product.setDescription(result.getString("description"));
        product.setPrice(result.getBigDecimal("price"));
        products.add(product);
      }
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, result);
    }

    return products;
  }

  @Override
  public Integer delete(Product product) throws Exception {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlDelete();
      statement = connection.prepareStatement(sql);
      this.addDeleteParam(statement, product);
      return statement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  private String getSqlInsert() {
    return " INSERT INTO products (code, name, description, price) "
         + " VALUES (?, ?, ?, ?) ";
  }

  private String getSqlUpdate() {
    return " UPDATE products "
         + " SET code = ?, name = ?, description = ?, price = ? "
         + " WHERE id = ? ";
  }

  private String getSelect() {
    return " SELECT * "
         + " FROM products "
         + " WHERE code = ? ";
  }

  private String getSelectAll() {
    return " SELECT * "
         + " FROM products ";
  }

  private String getSqlDelete() {
    return " DELETE FROM products "
         + " WHERE code = ? ";
  }

  private void addInsertParam(final PreparedStatement statement, final Product product) throws SQLException {
    statement.setString(1, product.getCode());
    statement.setString(2, product.getName());
    statement.setString(3, product.getDescription());
    statement.setBigDecimal(4, product.getPrice());
  }

  private void addUpdateParam(final PreparedStatement statement, final Product product) throws SQLException {
    statement.setString(1, product.getCode());
    statement.setString(2, product.getName());
    statement.setString(3, product.getDescription());
    statement.setBigDecimal(4, product.getPrice());
    statement.setLong(5, product.getId());
  }

  private void addSelectParam(final PreparedStatement statement, final String code) throws SQLException {
    statement.setString(1, code);
  }

  private void addDeleteParam(final PreparedStatement statement, final Product product) throws  SQLException {
    statement.setString(1, product.getCode());
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
