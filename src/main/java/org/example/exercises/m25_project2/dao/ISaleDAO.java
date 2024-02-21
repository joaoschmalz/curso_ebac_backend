package org.example.exercises.m25_project2.dao;

import org.example.exercises.m25_project2.dao.generic.IGenericDAO;
import org.example.exercises.m25_project2.domain.Sale;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;

public interface ISaleDAO extends IGenericDAO<Sale, String> {

  void finishSale(Sale sale) throws KeyTypeNotFoundException;
}
