package org.example.exercises.m30_project3.dao;

import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;

import java.util.Collection;

public class CustomerDAOMock implements ICustomerDAO {
  @Override
  public Boolean save(Customer entity) {
    return true;
  }

  @Override
  public void delete(Long value) throws DAOException {

  }

  @Override
  public void update(Customer entity) throws KeyNotFoundException {

  }

  @Override
  public Customer retrieve(Long value) {
    Customer customer = new Customer();
    customer.setCpf(value);
    return customer;
  }

  @Override
  public Collection<Customer> retrieveAll() {
    return null;
  }

}
