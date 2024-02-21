package org.example.exercises.m14_customerCRUD.repositories;

import org.example.exercises.m14_customerCRUD.infra.localDatabase.entities.Customer;

import java.util.List;

public interface ICustomerRepository {

  void create(final Customer customer) throws Exception;
  Customer retrieveSingle(final String cpf);
  List<Customer> retrieveAll();
  void delete(final String cpf);
  void update(final Customer customer) throws Exception;
}
