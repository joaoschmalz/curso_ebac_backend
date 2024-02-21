package org.example.exercises.m17_generics.first.dao.generic;

import org.example.exercises.m17_generics.first.domain.Persistent;

import java.util.Collection;

public interface IGenericDAO <T extends Persistent> {

  Boolean save(final T entity);
  void delete(final String code);
  void update(final T entity);
  T retrieve(final Long code);
  Collection<T> retrieveAll();
}
