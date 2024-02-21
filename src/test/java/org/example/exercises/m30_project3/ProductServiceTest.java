package org.example.exercises.m30_project3;

import org.example.exercises.m30_project3.dao.ProductDAOMock;
import org.example.exercises.m30_project3.domain.Product;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.services.IProductService;
import org.example.exercises.m30_project3.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceTest {

  private final IProductService productService = new ProductService(new ProductDAOMock());
  private Product product;

  @Before
  public void init() {
    this.product = new Product();

    this.product.setCode("A1");
    this.product.setDescription("Prod 01");
    this.product.setName("Product 01");
    this.product.setPrice(BigDecimal.TEN);
  }

  @Test
  public void search() throws DAOException {
    Product searchedProduct = this.productService.retrieve(this.product.getCode());
    assertNotNull(product);
  }

  @Test
  public void save() throws DAOException, KeyNotFoundException {
    Boolean result = this.productService.save(this.product);
    assertTrue(result);
  }

  @Test
  public void delete() throws DAOException {
    this.productService.delete(this.product.getCode());
  }

  @Test
  public void updateProduct() throws DAOException, KeyNotFoundException {
    this.product.setName("Product Test 02");
    this.productService.update(this.product);

    assertEquals("Product Test 02", this.product.getName());
  }
}
