package org.example.exercises.m25_project2.services.generic;

import org.example.exercises.m25_project2.dao.Persistable;
import org.example.exercises.m25_project2.dao.generic.IGenericDAO;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistable, E extends Serializable> implements IGenericService<T, E> {

  protected IGenericDAO<T, E> dao;

  public GenericService(IGenericDAO<T,E> dao) {
    this.dao = dao;
  }

  @Override
  public boolean save(T entity) throws KeyTypeNotFoundException {
    return this.dao.save(entity);
  }

  @Override
  public void delete(E value) {
    this.dao.delete(value);
  }

  @Override
  public void update(T entity) throws KeyTypeNotFoundException {
    this.dao.update(entity);
  }

  @Override
  public T retrieve(E value) {
    return this.dao.retrieve(value);
  }

  @Override
  public Collection<T> retrieveAll() {
    return this.dao.retrieveAll();
  }
}
