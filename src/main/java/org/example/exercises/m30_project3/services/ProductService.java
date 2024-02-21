package org.example.exercises.m30_project3.services;

import org.example.exercises.m30_project3.dao.IProductDAO;
import org.example.exercises.m30_project3.domain.Product;
import org.example.exercises.m30_project3.services.generic.GenericService;

public class ProductService extends GenericService<Product, String> implements IProductService {


  public ProductService(IProductDAO dao) {
    super(dao);
  }
}
