package org.example.exercises.m17_generics.first.dao.generic;

import org.example.exercises.m17_generics.first.Storage;
import org.example.exercises.m17_generics.first.domain.Persistent;

import java.util.Collection;
import java.util.Map;

import static java.util.Objects.nonNull;

public abstract class GenericDAO<T extends Persistent> implements IGenericDAO<T> {

  private Storage storage;
  public abstract Class<T> getClassType();
  public abstract void updateData(T newEntity, T entity);

  public GenericDAO() {
    this.storage = Storage.getInstance();
  }

  @Override
  public Boolean save(final T entity) {
    final Map<String, T> entityMap = getEntityMap();
    if (entityMap.containsKey(entity.getIdentifier())) return false;

    entityMap.put(entity.getIdentifier(), entity);
    return true;
  }

  @Override
  public void delete(final String code) {
    final Map<String, T> entityMap = getEntityMap();
    if(nonNull(entityMap.get(code))) entityMap.remove(code);
  }

  @Override
  public void update(final T entity) {
    final Map<String, T> entityMap = getEntityMap();
    if (nonNull(entityMap.get(entity.getIdentifier()))) updateData(entity, entityMap.get(entity.getIdentifier()));
  }

  @Override
  public T retrieve(final Long code) {
    final Map<String, T> entityMap = getEntityMap();
    return entityMap.get(code);
  }

  @Override
  public Collection<T> retrieveAll() {
    return getEntityMap().values();
  }

  private Map<String, T> getEntityMap() {
    return (Map<String, T>) this.storage.getMap().get(getClassType());
  }
}
