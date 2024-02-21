package org.example.exercises.m25_project2.services;

import org.example.exercises.m25_project2.dao.generic.IGenericDAO;
import org.example.exercises.m25_project2.domain.Product;
import org.example.exercises.m25_project2.services.generic.GenericService;

public class ProductService extends GenericService<Product, String> implements IProductService {

  public ProductService(IGenericDAO<Product, String> dao) {
    super(dao);
  }
}
