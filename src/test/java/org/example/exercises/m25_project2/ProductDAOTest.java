package org.example.exercises.m25_project2;

import org.example.exercises.m25_project2.dao.IProductDAO;
import org.example.exercises.m25_project2.dao.ProductDAO;
import org.example.exercises.m25_project2.domain.Product;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class ProductDAOTest {

  private final IProductDAO productDAO;

  private Product product;

  public ProductDAOTest() {
    this.productDAO = new ProductDAO();
  }

  @Before
  public void init() throws KeyTypeNotFoundException {
    this.product = new Product();
    this.product.setCode("A1B2C3");
    this.product.setDescription("Produto 1");
    this.product.setName("Produto 1");
    this.product.setPrice(BigDecimal.TEN);
    this.productDAO.save(product);
  }

  @Test
  public void ensureCanRetrieveProduct() {
    Product product = this.productDAO.retrieve(this.product.getCode());
    assertNotNull(product);
  }

  @Test
  public void ensureProductCanBeSaved() throws KeyTypeNotFoundException {
    this.product.setCode("A2B3C4");
    boolean result = this.productDAO.save(this.product);
    assertTrue(result);
  }

  @Test
  public void ensureProductCanBeDeleted() {
    this.productDAO.delete(this.product.getCode());
  }

  @Test
  public void ensureProductCanBeUpdated() throws KeyTypeNotFoundException {
    this.product.setName("Xablau's product");
    this.productDAO.update(this.product);

    assertEquals("Xablau's product", this.product.getName());
  }

  @Test
  public void ensureCanRetrieveAllProducts() {
    Collection<Product> products = this.productDAO.retrieveAll();
    assertTrue(nonNull(products));
    assertFalse(products.isEmpty());
  }
}
