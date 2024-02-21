package org.example.exercises.m25_project2.dao;

import org.example.exercises.m25_project2.domain.Product;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;

import java.util.Collection;

public class ProductDAOMock implements IProductDAO {
  @Override
  public boolean save(Product entity) throws KeyTypeNotFoundException {
    return true;
  }

  @Override
  public void delete(String value) {

  }

  @Override
  public void update(Product entity) throws KeyTypeNotFoundException {

  }

  @Override
  public Product retrieve(String value) {
    Product product = new Product();
    product.setCode(value);
    return product;
  }

  @Override
  public Collection<Product> retrieveAll() {
    return null;
  }
}
