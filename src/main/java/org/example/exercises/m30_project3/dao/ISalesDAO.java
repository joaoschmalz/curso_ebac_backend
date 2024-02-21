package org.example.exercises.m30_project3.dao;

import org.example.exercises.m30_project3.dao.generic.IGenericDAO;
import org.example.exercises.m30_project3.domain.Sale;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;

public interface ISalesDAO extends IGenericDAO<Sale, String> {

  void finishSale(Sale sale) throws KeyNotFoundException, DAOException;
  void cancelSale(Sale sale) throws KeyNotFoundException, DAOException;
}
