package org.example.exercises.m29_project3;

import org.example.exercises.m29_project3.modules.product.dao.IProductDAO;
import org.example.exercises.m29_project3.modules.product.dao.ProductDAO;
import org.example.exercises.m29_project3.modules.product.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProductsTest {

  private final IProductDAO productDAO = new ProductDAO();
  private final Product product = new Product();

  @Before
  public void init() {
    this.product.setCode("10");
    this.product.setName("Product Test");
    this.product.setDescription("Initial product");
    this.product.setPrice(new BigDecimal(1101));
  }

  @After
  public void clearDatabase() throws Exception {

    List<Product> productsList = this.productDAO.retrieveAll();
    for(Product product : productsList) {
      this.productDAO.delete(product);
    }
  }

  @Test
  public void save() throws Exception {
    Integer count = this.productDAO.save(this.product);
    assertEquals(1, (int) count);

    Product productDB = this.productDAO.retrieveSingle(this.product.getCode());
    assertNotNull(productDB.getCode());
    assertEquals(this.product.getCode(), productDB.getCode());
    assertEquals(this.product.getName(), productDB.getName());
    assertEquals(this.product.getDescription(), productDB.getDescription());
    assertEquals(this.product.getPrice(), productDB.getPrice());
  }

  @Test
  public void retrieveSingleTest() throws Exception {
    Integer count = this.productDAO.save(this.product);
    assertEquals(1, (int) count);

    Product productDB = this.productDAO.retrieveSingle(this.product.getCode());
    assertNotNull(productDB.getCode());
    assertEquals(this.product.getCode(), productDB.getCode());
    assertEquals(this.product.getName(), productDB.getName());
    assertEquals(this.product.getDescription(), productDB.getDescription());
    assertEquals(this.product.getPrice(), productDB.getPrice());
  }

  @Test
  public void retrieveAllTest() throws Exception {
    Integer count = this.productDAO.save(this.product);
    assertEquals(1, (int) count);

    Product product = new Product();
    product.setCode("11");
    product.setName("Product Xablau Test");
    product.setDescription("Second product");
    product.setPrice(new BigDecimal(9999));
    Integer count02 = this.productDAO.save(product);
    assertEquals(1, (int) count02);

    List<Product> productList = this.productDAO.retrieveAll();
    assertNotNull(productList);
    assertEquals(2, productList.size());
  }

  @Test
  public void updateTest() throws Exception {
    Integer count = this.productDAO.save(this.product);
    assertEquals(1, (int) count);

    Product productDB = this.productDAO.retrieveSingle(this.product.getCode());
    assertNotNull(productDB);
    assertEquals(this.product.getCode(), productDB.getCode());
    assertEquals(this.product.getName(), productDB.getName());
    assertEquals(this.product.getDescription(), productDB.getDescription());
    assertEquals(this.product.getPrice(), productDB.getPrice());

    productDB.setCode("20");
    productDB.setName("Product Xablau Test");
    productDB.setDescription("Second product");
    productDB.setPrice(new BigDecimal(9999));
    Integer countUpdate = this.productDAO.update(productDB);
    assertEquals(1, (int) countUpdate);

    Product productDB01 = this.productDAO.retrieveSingle(this.product.getCode());
    assertNull(productDB01);

    Product productDB02 = this.productDAO.retrieveSingle(productDB.getCode());
    assertNotNull(productDB02);
    assertEquals(productDB.getCode(), productDB02.getCode());
    assertEquals(productDB.getName(), productDB02.getName());
    assertEquals(productDB.getDescription(), productDB02.getDescription());
    assertEquals(productDB.getPrice(), productDB02.getPrice());
  }

  @Test
  public void deleteTest() throws Exception {
    Integer count = this.productDAO.save(this.product);
    assertEquals(1, (int) count);

    Product product = this.productDAO.retrieveSingle(this.product.getCode());
    assertNotNull(product);

    this.productDAO.delete(product);

    Product newProduct = this.productDAO.retrieveSingle(this.product.getCode());
    assertNull(newProduct);
  }
}
