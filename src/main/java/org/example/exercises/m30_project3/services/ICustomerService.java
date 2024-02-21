package org.example.exercises.m30_project3.services;

import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.services.generic.IGenericService;

public interface ICustomerService extends IGenericService<Customer, Long> {

  Customer retrieveBy(Long cpf) throws DAOException;
}
