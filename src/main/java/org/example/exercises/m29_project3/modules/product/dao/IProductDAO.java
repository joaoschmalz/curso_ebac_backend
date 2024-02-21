package org.example.exercises.m29_project3.modules.product.dao;

import org.example.exercises.m29_project3.modules.product.domain.Product;

import java.util.List;

public interface IProductDAO {

  Integer save(Product product) throws Exception;
  Integer update(Product product) throws Exception;
  Product retrieveSingle(String code) throws Exception;
  List<Product> retrieveAll() throws Exception;
  Integer delete(Product product) throws Exception;
}
