package org.example.exercises.m25_project2.dao;

import org.example.exercises.m25_project2.dao.generic.GenericDAO;
import org.example.exercises.m25_project2.domain.Customer;

public class CustomerDAO extends GenericDAO<Customer, Long> implements ICustomerDAO {

  @Override
  public Class<Customer> getClassType() {
    return Customer.class;
  }

  @Override
  public void updateData(Customer newEntity, Customer entity) {
    entity.setCpf(newEntity.getCpf());
    entity.setName(newEntity.getName());
    entity.setEmail(newEntity.getEmail());
  }

}
