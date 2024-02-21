package org.example.exercises.m32_jpa.dao;

import org.example.exercises.m32_jpa.domain.Product;

public interface IProductDAO {

  Product retrieveSingle(String name);
  Product save(Product product);
  void delete(Long id);
}
