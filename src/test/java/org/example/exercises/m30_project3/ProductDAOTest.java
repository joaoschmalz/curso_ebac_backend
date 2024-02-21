package org.example.exercises.m30_project3;

import org.example.exercises.m30_project3.dao.IProductDAO;
import org.example.exercises.m30_project3.dao.ProductDAO;
import org.example.exercises.m30_project3.dao.generic.jdbc.ConnectionFactory;
import org.example.exercises.m30_project3.domain.Product;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class ProductDAOTest {

  private final IProductDAO productDAO = new ProductDAO();

  @After
  public void clearDatabase() throws DAOException {
    Connection connection = this.getConnection();
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement("DELETE FROM product");
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was an error deleting object", e);
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  private Product createProduct(String code) throws DAOException, KeyNotFoundException {
    Product product = new Product();

    product.setCode(code);
    product.setDescription("Test 01");
    product.setName("Prod 01");
    product.setPrice(BigDecimal.TEN);
    this.productDAO.save(product);

    return product;
  }

  @Test
  public void retrieveAll() throws DAOException, KeyNotFoundException, InterruptedException {
    this.createProduct("A1");
    this.createProduct("A2");

    Collection<Product> list = this.productDAO.retrieveAll();

    assertNotNull(list);
    assertEquals(2, list.size());

    for (Product prod : list) {
      this.productDAO.delete(prod.getCode());
    }

    list = this.productDAO.retrieveAll();
    assertNotNull(list);
    assertEquals(0, list.size());
  }

  @Test
  public void search() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    Product product = createProduct("A1");
    assertNotNull(product);

    Product searchedProduct = this.productDAO.retrieve(product.getCode());
    assertNotNull(searchedProduct);
  }

  @Test
  public void save() throws DAOException, KeyNotFoundException {
    Product product = this.createProduct("A1");
    assertNotNull(product);
  }

  @Test
  public void delete() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    Product product = this.createProduct("A1");
    assertNotNull(product);

    this.productDAO.delete(product.getCode());
    Product searchedProduct = this.productDAO.retrieve(product.getCode());
    assertNull(searchedProduct);
  }

  @Test
  public void updateProduct() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    Product product = this.createProduct("A1");
    product.setName("Product Test 01");
    this.productDAO.update(product);

    Product searchedProduct = this.productDAO.retrieve(product.getCode());
    assertNotNull(searchedProduct);
    assertEquals("Product Test 01", product.getName());
  }

  private Connection getConnection() throws DAOException {
    try {
      return ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new DAOException("Couldn't open database connection", e);
    }
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
