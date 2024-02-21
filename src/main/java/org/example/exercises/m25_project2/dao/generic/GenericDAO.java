package org.example.exercises.m25_project2.dao.generic;

import org.example.exercises.m25_project2.annotation.KeyType;
import org.example.exercises.m25_project2.dao.Persistable;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class GenericDAO<T extends Persistable, E extends Serializable> implements IGenericDAO<T, E> {

  private final Storage storage;
  public abstract Class<T> getClassType();
  public abstract void updateData(T newEntity, T entity);

  public GenericDAO() {
    this.storage = Storage.getInstance();
  }

  public E getKey(T entity) throws KeyTypeNotFoundException {
    Field[] fields = entity.getClass().getDeclaredFields();
    E retrieveValue = null;

    for (Field f : fields) {
      if (f.isAnnotationPresent(KeyType.class)) {
        KeyType key = f.getAnnotation(KeyType.class);
        String methodName = key.value();

        try {
          Method method = entity.getClass().getMethod(methodName);
          retrieveValue = (E) method.invoke(entity);
          return retrieveValue;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
          e.printStackTrace();
          throw new KeyTypeNotFoundException("Principal key from object " + entity.getClass() + " not found. ", e);
        }
      }
    }
    if (isNull(retrieveValue)) {
      String msg = "Principal Key from object" + entity.getClass() + " not found.";
      System.out.println("**** ERROR ****" + msg);
      throw new KeyTypeNotFoundException(msg);
    }
    return null;
  }

  @Override
  public boolean save(final T entity) throws KeyTypeNotFoundException {
    final Map<E, T> entityMap = this.getEntityMap();
    E key = this.getKey(entity);
    if (entityMap.containsKey(key)) return false;

    entityMap.put(key, entity);
    return true;
  }

  private Map<E, T> getEntityMap() {
    Map<E, T> entityMap =  (Map<E, T>) this.storage.getMap().get(getClassType());
    if (isNull(entityMap)) {
      entityMap = new HashMap<>();
      this.storage.getMap().put(getClassType(), entityMap);
    }
    return entityMap;
  }

  @Override
  public void delete(E value) {
    Map<E, T> entityMap = this.getEntityMap();
    T object = entityMap.get(value);
    if (nonNull(object)) entityMap.remove(value, object);
  }

  @Override
  public void update(T entity) throws KeyTypeNotFoundException {
    Map<E, T> entityMap = this.getEntityMap();
    E key = this.getKey(entity);
    T object = entityMap.get(key);
    if (nonNull(object)) this.updateData(entity, object);
  }

  @Override
  public T retrieve(E value) {
    Map<E, T> entityMap = getEntityMap();
    return entityMap.get(value);
  }

  @Override
  public Collection<T> retrieveAll() {
    return getEntityMap().values();
  }
}

