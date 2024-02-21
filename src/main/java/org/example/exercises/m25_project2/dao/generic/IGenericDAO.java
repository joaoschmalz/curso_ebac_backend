package org.example.exercises.m25_project2.dao.generic;

import org.example.exercises.m25_project2.dao.Persistable;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO<T extends Persistable, E extends Serializable> {

  boolean save(final T entity) throws KeyTypeNotFoundException;
  void delete(E value);
  void update(T entity) throws KeyTypeNotFoundException;
  T retrieve(E value);
  Collection<T> retrieveAll();

}
