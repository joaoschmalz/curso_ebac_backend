package org.example.exercises.m29_project3.modules.customer.dao;

import org.example.exercises.m29_project3.modules.customer.domain.Customer;

import java.util.List;

public interface ICustomerDAO {

  Integer save(final Customer customer) throws Exception;
  Integer update(final Customer customer) throws Exception;
  Customer retrieve(final String code) throws Exception;
  List<Customer> retrieveAll() throws Exception;
  Integer delete (final Customer customer) throws Exception;
}
