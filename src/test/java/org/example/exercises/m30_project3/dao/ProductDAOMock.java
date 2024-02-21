package org.example.exercises.m30_project3.dao;

import org.example.exercises.m30_project3.domain.Product;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;

import java.util.Collection;

public class ProductDAOMock implements IProductDAO {
  @Override
  public Boolean save(Product entity) throws KeyNotFoundException, DAOException {
    return true;
  }

  @Override
  public void delete(String value) throws DAOException {

  }

  @Override
  public void update(Product entity) throws KeyNotFoundException, DAOException {

  }

  @Override
  public Product retrieve(String value) throws DuplicatedEntryException, TableException, DAOException {
    Product product = new Product();
    product.setCode(value);
    return product;
  }

  @Override
  public Collection<Product> retrieveAll() throws DAOException {
    return null;
  }

}
