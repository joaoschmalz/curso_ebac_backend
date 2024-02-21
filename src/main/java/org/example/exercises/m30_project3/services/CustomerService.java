package org.example.exercises.m30_project3.services;

import org.example.exercises.m30_project3.dao.ICustomerDAO;
import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;
import org.example.exercises.m30_project3.services.generic.GenericService;

public class CustomerService extends GenericService<Customer, Long> implements ICustomerService {

  private final ICustomerDAO customerDAO;

  public CustomerService(ICustomerDAO dao) {
    super(dao);
    this.customerDAO = dao;
  }

  @Override
  public Customer retrieveBy(Long cpf) throws DAOException {
    try {
      return this.dao.retrieve(cpf);
    } catch (DuplicatedEntryException | TableException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Boolean save(final Customer entity) throws DAOException, KeyNotFoundException {
    return customerDAO.save(entity);
  }

  @Override
  public void update(Customer entity) throws DAOException, KeyNotFoundException {
    customerDAO.update(entity);
  }
}
