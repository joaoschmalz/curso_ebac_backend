package org.example.exercises.m30_project3.services.generic;

import org.example.exercises.m30_project3.dao.Persistent;
import org.example.exercises.m30_project3.dao.generic.IGenericDAO;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;

import java.io.Serializable;
import java.util.Collection;

public abstract class GenericService<T extends Persistent, E extends Serializable> implements IGenericService<T,E> {

  protected IGenericDAO<T, E> dao;

  public GenericService(IGenericDAO<T, E> dao) {
    this.dao = dao;
  }

  @Override
  public Boolean save(T entity) throws DAOException, KeyNotFoundException {
    return this.dao.save(entity);
  }

  @Override
  public void delete(E valor) throws DAOException {
    this.dao.delete(valor);
  }

  @Override
  public void update(T entity) throws DAOException, KeyNotFoundException {
    this.dao.update(entity);
  }

  @Override
  public T retrieve(E valor) throws DAOException {
    try {
      return this.dao.retrieve(valor);
    } catch (DuplicatedEntryException | TableException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Collection<T> retrieveAll() throws DAOException {
    return this.dao.retrieveAll();
  }

}
