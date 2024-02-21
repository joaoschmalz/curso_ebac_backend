package org.example.exercises.m32_jpa;

import org.example.exercises.m32_jpa.dao.IProductDAO;
import org.example.exercises.m32_jpa.dao.ProductDAO;
import org.example.exercises.m32_jpa.domain.Product;
import org.junit.After;
import org.junit.Test;

import javax.persistence.*;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

  private final IProductDAO dao = new ProductDAO();

  @After
  public void init() {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = factory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();

    entityManager.createQuery("DELETE FROM Product ").executeUpdate();
    transaction.commit();
  }

  @Test
  public void save() {
    Product product = new Product();

    product.setCode("A1");
    product.setActive(true);
    product.setDescription("Test");
    product.setName("Product Test");
    product.setPrice(BigDecimal.TEN);

    dao.save(product);

    assertNotNull(product);
    assertNotNull(product.getId());
  }

  @Test
  public void delete() {
    Product product = new Product();

    product.setCode("A1");
    product.setActive(true);
    product.setDescription("Test");
    product.setName("Product Test");
    product.setPrice(BigDecimal.TEN);

    dao.save(product);
    dao.delete(product.getId());

    Product searchedProduct = dao.retrieveSingle(product.getName());
    assertNull(searchedProduct);
  }

  @Test
  public void retrieve() {
    Product product = new Product();

    product.setCode("A1");
    product.setActive(true);
    product.setDescription("Test");
    product.setName("Product Test");
    product.setPrice(BigDecimal.TEN);

    dao.save(product);

    Product searchedProduct = dao.retrieveSingle("Product Test");

    assertEquals(product.getId(), searchedProduct.getId());
    assertEquals(product.getName(), searchedProduct.getName());
  }
}
