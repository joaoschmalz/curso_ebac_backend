package org.example.exercises.m17_generics.second.dao.generic;

import org.example.exercises.m17_generics.second.domain.Car;

public interface IGenericDAO<T extends Car> {

  Boolean save(final T entity);
}
