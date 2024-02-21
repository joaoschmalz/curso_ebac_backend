package org.example.exercises.m30_project3;

import org.example.exercises.m30_project3.dao.*;
import org.example.exercises.m30_project3.dao.generic.jdbc.ConnectionFactory;
import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.domain.Product;
import org.example.exercises.m30_project3.domain.Sale;
import org.example.exercises.m30_project3.domain.Sale.Status;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class SaleDAOTest {

  private final ISalesDAO salesDAO = new SalesDAO();
  private final ICustomerDAO customerDAO = new CustomerDAO();
  private final IProductDAO productDAO = new ProductDAO();
  private Customer customer;
  private Product product;

  @Before
  public void init() throws DAOException, KeyNotFoundException {
    this.customer = this.createCustomer();
    this.product = this.createProduct("A1", BigDecimal.TEN);
  }

  @After
  public void finish() throws DAOException {
    this.clearDatabase();
  }

  @Test
  public void search() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    Sale sale = this.createSale("A1");
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);

    Sale searchedSale = this.salesDAO.retrieve(sale.getCode());
    assertNotNull(searchedSale);
    assertEquals(sale.getCode(), searchedSale.getCode());
  }

  @Test
  public void save() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    Sale sale = this.createSale("A1");
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);

    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(20));
    assertEquals(sale.getStatus(), Sale.Status.STARTED);

    Sale searchedSale = this.salesDAO.retrieve(sale.getCode());
    assertNotNull(searchedSale.getId());
    assertEquals(sale.getCode(), searchedSale.getCode());
  }

  @Test
  public void cancelSale() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    this.salesDAO.cancelSale(sale);

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    assertEquals(saleCode, searchedSale.getCode());
    assertEquals(Status.CANCELED, searchedSale.getStatus());
  }

  @Test
  public void addMoreOfTheSameProduct() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    searchedSale.addProduct(this.product, 1);

    assertEquals(3, (int) searchedSale.getTotalProductsAmount());
    BigDecimal totalPrice = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), totalPrice);
    assertEquals(searchedSale.getStatus(), Status.STARTED);
  }

  @Test
  public void addMoreDifferentProducts() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    Product prod = this.createProduct(saleCode, BigDecimal.valueOf(50));
    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    searchedSale.addProduct(prod, 1);

    assertEquals(3, (int) searchedSale.getTotalProductsAmount());
    BigDecimal totalPrice = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), totalPrice);
    assertEquals(searchedSale.getStatus(), Status.STARTED);
  }

  @Test(expected = DAOException.class)
  public void ensureCantSaveSaleWithDuplicatedCode() throws DAOException, KeyNotFoundException {
    Sale sale = this.createSale("A1");
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);

    this.salesDAO.save(sale);
  }

  @Test
  public void removeProduct() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    Product prod = this.createProduct(saleCode, BigDecimal.valueOf(50));
    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    searchedSale.addProduct(prod, 1);
    assertEquals(3, (int) searchedSale.getTotalProductsAmount());
    BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), valorTotal);


    searchedSale.removeProduct(prod, 1);
    assertEquals(2, (int) searchedSale.getTotalProductsAmount());
    valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), valorTotal);
    assertEquals(searchedSale.getStatus(), Status.STARTED);
  }

  @Test
  public void removeSingleProduct() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    Product prod = this.createProduct(saleCode, BigDecimal.valueOf(50));
    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    searchedSale.addProduct(prod, 1);
    assertEquals(3, (int) searchedSale.getTotalProductsAmount());
    BigDecimal totalPrice = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), totalPrice);


    searchedSale.removeProduct(prod, 1);
    assertEquals(2, (int) searchedSale.getTotalProductsAmount());
    totalPrice = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), totalPrice);
    assertEquals(searchedSale.getStatus(), Status.STARTED);
  }

  @Test
  public void removeAllProducts() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    Product prod = createProduct(saleCode, BigDecimal.valueOf(50));
    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    searchedSale.addProduct(prod, 1);
    assertEquals(3, (int) searchedSale.getTotalProductsAmount());
    BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
    assertEquals(searchedSale.getTotalPrice(), valorTotal);


    searchedSale.removeAllProducts();
    assertEquals(0, (int) searchedSale.getTotalProductsAmount());
    assertEquals(searchedSale.getTotalPrice(), BigDecimal.valueOf(0));
    assertEquals(searchedSale.getStatus(), Status.STARTED);
  }

  @Test
  public void finishSale() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    this.salesDAO.finishSale(sale);

    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    assertEquals(sale.getCode(), searchedSale.getCode());
    assertEquals(Status.FINISHED, searchedSale.getStatus());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void ensureCantUpdateFinishedSale() throws DAOException, KeyNotFoundException, DuplicatedEntryException, TableException {
    String saleCode = "A1";
    Sale sale = this.createSale(saleCode);
    Boolean result = this.salesDAO.save(sale);
    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    this.salesDAO.finishSale(sale);
    Sale searchedSale = this.salesDAO.retrieve(saleCode);
    assertEquals(sale.getCode(), searchedSale.getCode());
    assertEquals(Status.FINISHED, searchedSale.getStatus());

    searchedSale.addProduct(this.product, 1);
  }

  private Product createProduct(String code, BigDecimal value) throws DAOException, KeyNotFoundException {
    Product prod = new Product();
    prod.setCode(code);
    prod.setDescription("Produto 1");
    prod.setName("Produto 1");
    prod.setPrice(value);
    this.productDAO.save(prod);
    return prod;
  }

  private Customer createCustomer() throws DAOException, KeyNotFoundException {
    Customer customer01 = new Customer();
    customer01.setCpf(12312312312L);
    customer01.setName("Xablau");
    customer01.setCity("Xablauville");
    customer01.setAddress("End");
    customer01.setState("SP");
    customer01.setAddressNumber(10);
    customer01.setPhone(1199999999L);
    this.customerDAO.save(customer01);
    return customer01;
  }

  private Sale createSale(String code) {
    Sale sale = new Sale();

    sale.setCode(code);
    sale.setCreatedAt(Instant.now());
    sale.setCustomer(this.customer);
    sale.setStatus(Status.STARTED);
    sale.addProduct(this.product, 2);

    return sale;
  }

  private void clearDatabase() throws DAOException {
    String sqlProdAmount = "DELETE FROM product_amount";
    executeDelete(sqlProdAmount);

    String sqlV = "DELETE FROM sale";
    executeDelete(sqlV);

    String sqlCustomer = "DELETE FROM customer";
    executeDelete(sqlCustomer);

    String sqlProduct = "DELETE FROM product";
    executeDelete(sqlProduct);
  }

  private void executeDelete(String sql) throws DAOException {
    Connection connection = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
      connection = getConnection();
      stm = connection.prepareStatement(sql);
      stm.executeUpdate();

    } catch (SQLException e) {
      throw new DAOException("Failed to delete object ", e);
    } finally {
      closeConnection(connection, stm, rs);
    }
  }

  protected void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
    try {
      if (nonNull(rs) && !rs.isClosed()) {
        rs.close();
      }
      if (nonNull(stm) && !stm.isClosed()) {
        stm.close();
      }
      if (nonNull(connection) && !stm.isClosed()) {
        connection.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  protected Connection getConnection() throws DAOException {
    try {
      return ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new DAOException("ERRO ABRINDO CONEXAO COM BANCO DE DADOS ", e);
    }
  }

}
