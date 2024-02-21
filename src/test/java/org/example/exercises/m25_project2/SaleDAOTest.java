package org.example.exercises.m25_project2;

import org.example.exercises.m25_project2.dao.*;
import org.example.exercises.m25_project2.domain.Customer;
import org.example.exercises.m25_project2.domain.Product;
import org.example.exercises.m25_project2.domain.Sale;
import org.example.exercises.m25_project2.domain.Sale.Status;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.*;

public class SaleDAOTest {

  private final ISaleDAO saleDAO;
  private final ICustomerDAO customerDAO;
  private final IProductDAO productDAO;
  private Customer customer;
  private Product product;

  public SaleDAOTest() {
    this.saleDAO = new SaleDAO();
    this.customerDAO = new CustomerDAO();
    this.productDAO = new ProductDAO();
  }

  @Before
  public void init() throws KeyTypeNotFoundException {
    this.customer = createCustomer();
    this.product = createProduct("A1", BigDecimal.TEN);
  }


  @Test
  public void ensureSaleCanBeRetrieved() throws KeyTypeNotFoundException {
    Sale sale = createSale("A1");
    boolean result = this.saleDAO.save(sale);
    Sale saleRetrieved = this.saleDAO.retrieve(sale.getCode());

    assertTrue(result);
    assertNotNull(saleRetrieved);
    assertEquals(sale.getCode(), saleRetrieved.getCode());
  }

  @Test
  public void ensureSaleCanBeSaved() throws KeyTypeNotFoundException {
    Sale sale = createSale("A2");
    boolean result = this.saleDAO.save(sale);

    assertTrue(result);
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(20));
    assertEquals(sale.getStatus(), Status.STARTED);
  }


  @Test
  public void ensureSaleCanBeCanceled() throws KeyTypeNotFoundException {
    String saleCode = "A3";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    sale.setStatus(Status.CANCELED);
    this.saleDAO.update(sale);
    Sale saleRetrieved = this.saleDAO.retrieve(saleCode);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());
    assertEquals(saleCode, saleRetrieved.getCode());
    assertEquals(Status.CANCELED, saleRetrieved.getStatus());
  }

  @Test
  public void ensureCanAddMoreOfTheSameProduct() throws KeyTypeNotFoundException {
    String saleCode = "A4";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    Sale retrievedSale = this.saleDAO.retrieve(saleCode);
    retrievedSale.addProduct(this.product, 1);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());
    assertEquals(3, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(30));
    assertEquals(sale.getStatus(), Status.STARTED);
  }

  @Test
  public void ensureCanAddDifferentProducts() throws KeyTypeNotFoundException {
    String saleCode = "A5";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    Product prod = createProduct(saleCode, BigDecimal.valueOf(50));
    Sale saleRetrieved = this.saleDAO.retrieve(saleCode);
    saleRetrieved.addProduct(prod, 1);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());
    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());
    assertEquals(3, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(70));
    assertEquals(sale.getStatus(), Status.STARTED);
  }

  @Test
  public void ensureCanSaveExistentProduct() throws KeyTypeNotFoundException {
    Sale sale = createSale("A6");
    boolean result = this.saleDAO.save(sale);
    boolean newResult = this.saleDAO.save(sale);

    assertTrue(result);
    assertFalse(newResult);
    assertEquals(sale.getStatus(), Status.STARTED);
  }

  @Test
  public void ensureCanRemoveProductFromSale() throws KeyTypeNotFoundException {
    String saleCode = "A7";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    Product prod = createProduct(saleCode, BigDecimal.valueOf(50));
    Sale retrievedSale = this.saleDAO.retrieve(saleCode);
    retrievedSale.addProduct(prod, 1);
    retrievedSale.removeProduct(prod, 1);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());
    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());
    assertEquals(2, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(20));
    assertEquals(2, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(20));
    assertEquals(sale.getStatus(), Status.STARTED);
  }

  @Test
  public void ensureCanRemoveASingleProduct() throws KeyTypeNotFoundException {
    String saleCode = "A8";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    Product prod = createProduct(saleCode, BigDecimal.valueOf(50));
    Sale saleRetrieved = this.saleDAO.retrieve(saleCode);
    saleRetrieved.addProduct(prod, 1);
    saleRetrieved.removeProduct(prod, 1);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());

    assertEquals(2, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(20));

    assertEquals(2, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(20));
    assertEquals(sale.getStatus(), Status.STARTED);
  }

  @Test
  public void ensureCanClearSale() throws KeyTypeNotFoundException {
    String saleCode = "A9";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    Product prod = createProduct(saleCode, BigDecimal.valueOf(50));
    Sale saleRetrieved = this.saleDAO.retrieve(saleCode);
    saleRetrieved.addProduct(prod, 1);
    saleRetrieved.clearSale();

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    assertNotNull(prod);
    assertEquals(saleCode, prod.getCode());

    assertEquals(0, (int) sale.getTotalProducts());
    assertEquals(sale.getTotalPrice(), BigDecimal.valueOf(0));
    assertEquals(sale.getStatus(), Status.STARTED);
  }

  @Test
  public void ensureCanFinishSale() throws KeyTypeNotFoundException {
    String saleCode = "A10";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    this.saleDAO.finishSale(sale);
    Sale saleRetrieved = this.saleDAO.retrieve(saleCode);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    assertEquals(sale.getCode(), saleRetrieved.getCode());
    assertEquals(sale.getStatus(), saleRetrieved.getStatus());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void ensureCannotAddProductsIntoFinishedSale() throws KeyTypeNotFoundException {
    String saleCode = "A11";
    Sale sale = createSale(saleCode);
    boolean result = this.saleDAO.save(sale);

    this.saleDAO.finishSale(sale);
    Sale saleRetrieved = this.saleDAO.retrieve(saleCode);

    assertTrue(result);
    assertNotNull(sale);
    assertEquals(saleCode, sale.getCode());

    assertEquals(sale.getCode(), saleRetrieved.getCode());
    assertEquals(sale.getStatus(), saleRetrieved.getStatus());

    saleRetrieved.addProduct(this.product, 1);
  }

  private Product createProduct(String code, BigDecimal valor) throws KeyTypeNotFoundException {
    Product prod = new Product();
    prod.setCode(code);
    prod.setDescription("Product 1");
    prod.setName("Product 1");
    prod.setPrice(valor);
    this.productDAO.save(prod);
    return prod;
  }

  private Customer createCustomer() throws KeyTypeNotFoundException {
    Customer newCustomer = new Customer();
    newCustomer.setCpf(12312312312L);
    newCustomer.setName("Xablau");
    newCustomer.setEmail("xablau@email.com");
    this.customerDAO.save(newCustomer);
    return newCustomer;
  }

  private Sale createSale(String code) {
    Sale sale = new Sale();
    sale.setCode(code);
    sale.setSaleDate(Instant.now());
    sale.setCustomer(this.customer);
    sale.setStatus(Sale.Status.STARTED);
    sale.addProduct(this.product, 2);
    return sale;
  }
}
