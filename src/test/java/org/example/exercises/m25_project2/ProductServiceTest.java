package org.example.exercises.m25_project2;

import org.example.exercises.m25_project2.dao.IProductDAO;
import org.example.exercises.m25_project2.dao.ProductDAOMock;
import org.example.exercises.m25_project2.domain.Product;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;
import org.example.exercises.m25_project2.services.IProductService;
import org.example.exercises.m25_project2.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceTest {

  private final IProductService productService;

  private Product product;

  public ProductServiceTest() {
    IProductDAO dao = new ProductDAOMock();
    this.productService = new ProductService(dao);
  }

  @Before
  public void init() {
    this.product = new Product();
    this.product.setCode("A1");
    this.product.setDescription("Product 1");
    this.product.setName("Product 1");
    this.product.setPrice(BigDecimal.TEN);
  }

  @Test
  public void ensureProductCanBeRetrieved() {
    Product prod = this.productService.retrieve(this.product.getCode());
    assertNotNull(prod);
  }

  @Test
  public void ensureProductCanBeSave() throws KeyTypeNotFoundException {
    boolean result = this.productService.save(this.product);
    assertTrue(result);
  }

  @Test
  public void ensureProductCanBeDelete() {
    this.productService.delete(this.product.getCode());
  }

  @Test
  public void ensureProductCanBeUpdated() throws KeyTypeNotFoundException {
    this.product.setName("Product Test Name");
    this.productService.update(this.product);

    assertEquals("Product Test Name", this.product.getName());
  }
}
