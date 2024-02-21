package org.example.exercises.m30_project3.dao.generic;

import org.example.exercises.m30_project3.annotations.Column;
import org.example.exercises.m30_project3.annotations.KeyType;
import org.example.exercises.m30_project3.annotations.Table;
import org.example.exercises.m30_project3.dao.Persistent;
import org.example.exercises.m30_project3.dao.generic.jdbc.ConnectionFactory;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E> {

  public abstract Class<T> getClassType();

  public abstract void updateData(T newEntity, T entity);

  protected abstract String getInsertQuery();

  protected abstract String getDeleteQuery();

  protected abstract String getUpdateQuery();

  protected abstract void setInsertParam(PreparedStatement statement, T entity) throws SQLException;

  protected abstract void setDeleteParam(PreparedStatement statement, E value) throws SQLException;

  protected abstract void setUpdateParam(PreparedStatement statement, T entity) throws SQLException;

  protected abstract void setSelectParam(PreparedStatement statement, E value) throws SQLException;

  @Override
  public Boolean save(T entity) throws KeyNotFoundException, DAOException {
    Connection connection = null;
    PreparedStatement stm = null;

    try {
      connection = this.getConnection();
      stm = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
      setInsertParam(stm, entity);
      int rowsAffected = stm.executeUpdate();

      if (rowsAffected > 0) {
        try (ResultSet result = stm.getGeneratedKeys()) {
          if (result.next()) {
            (entity).setId(result.getLong(1));
          }
        }
        return true;
      }
    } catch (SQLException e) {
      throw new DAOException("There was an error saving object", e);
    } finally {
      closeConnection(connection, stm, null);
    }
    return false;
  }

  @Override
  public void delete(E value) throws DAOException {
    Connection connection = this.getConnection();
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement(getDeleteQuery());
      setDeleteParam(statement, value);
      int rowsAffected = statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was an error deleting object", e);
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public void update(T entity) throws KeyNotFoundException, DAOException {
    Connection connection = this.getConnection();
    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement(getUpdateQuery());
      setUpdateParam(statement, entity);
      int rowsAffected = statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was an error updating object", e);
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public T retrieve(E value) throws DuplicatedEntryException, TableException, DAOException {
    try {
      checkDuplicateEntry(value);
      Connection connection = this.getConnection();
      PreparedStatement statement = connection.prepareStatement(" SELECT * FROM " + getTableName() + " WHERE " + getFieldNameKey(getClassType()) + " = ? ");
      setSelectParam(statement, value);
      ResultSet result = statement.executeQuery();

      if (result.next()) {
        T entity = getClassType().getConstructor(null).newInstance(null);
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
          if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            String dbName = column.dbName();
            String javaSetName = column.setJavaName();
            Class<?> classField = field.getType();

            try {
              Method method = entity.getClass().getMethod(javaSetName, classField);
              setValueByType(entity, method, classField, result, dbName);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                     KeyNotFoundException e) {
              throw new DAOException("Failed to retrieve object", e);
            }
          }
        }
        return entity;
      }
    } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException |
             InvocationTargetException | NoSuchMethodException | SecurityException | KeyNotFoundException e) {
      throw new DAOException("Couldn't retrieve object", e);
    }
    return null;
  }

  @Override
  public Collection<T> retrieveAll() throws DAOException {
    List<T> list = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
      connection = this.getConnection();
      statement = connection.prepareStatement(" SELECT * FROM " + this.getTableName());
      result = statement.executeQuery();

      while (result.next()) {
        T entity = this.getClassType().getConstructor(null).newInstance(null);
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
          if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            String dbName = column.dbName();
            String javaSetName = column.setJavaName();
            Class<?> classField = field.getType();

            try {
              Method method = entity.getClass().getMethod(javaSetName, classField);
              setValueByType(entity, method, classField, result, dbName);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                     KeyNotFoundException e) {
              throw new DAOException("Failed retrieving objects", e);
            }
          }
        }
        list.add(entity);
      }
    } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException |
             InvocationTargetException | NoSuchMethodException | SecurityException | TableException e) {
      throw new DAOException("Failed retrieving objects", e);
    } finally {
      closeConnection(connection, statement, result);
    }
    return list;
  }

  private void checkDuplicateEntry(E value) throws DuplicatedEntryException, TableException, KeyNotFoundException, DAOException {
    Connection connection = this.getConnection();
    PreparedStatement statement = null;
    ResultSet result = null;
    Long count = null;

    try {
      statement = connection.prepareStatement(" SELECT count(*) FROM " + this.getTableName() + " WHERE " + getFieldNameKey(getClassType()) + " = ? ");
      setSelectParam(statement, value);
      result = statement.executeQuery();

      if (result.next()) {
        count = result.getLong(1);
        if (count > 1) {
          throw new DuplicatedEntryException("Found more then one " + getTableName());
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection(connection, statement, result);
    }
  }

  private String getTableName() throws TableException {
    if (getClassType().isAnnotationPresent(Table.class)) {
      Table table = getClassType().getAnnotation(Table.class);
      return table.value();
    } else {
      throw new TableException(" Table " + getClassType().getName() + " not found");
    }
  }

  private String getFieldNameKey(final Class clazz) throws KeyNotFoundException {
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(KeyType.class) && field.isAnnotationPresent(Column.class)) {
        Column column = field.getAnnotation(Column.class);
        return column.dbName();
      }
    }
    return null;
  }

  private Object getValueByType(final Class<?> typeField, final ResultSet result, final String fieldName) throws SQLException, KeyNotFoundException {
    if (typeField.equals(Integer.TYPE)) {
      return result.getInt(fieldName);
    } else if (typeField.equals(Long.TYPE)) {
      return result.getLong(fieldName);
    } else if (typeField.equals(Double.TYPE)) {
      return result.getDouble(fieldName);
    } else if (typeField.equals(Short.TYPE)) {
      return result.getShort(fieldName);
    } else if (typeField.equals(BigDecimal.class)) {
      return result.getBigDecimal(fieldName);
    } else if (typeField.equals(String.class)) {
      return result.getString(fieldName);
    } else {
      throw new KeyNotFoundException("Class type unknown: " + typeField);
    }
  }

  private void setValueByType(final T entity, final Method method, final Class<?> classField, final ResultSet result, final String fieldName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, KeyNotFoundException {
    if (classField.equals(Integer.class)) {
      final Integer val = result.getInt(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(Long.class)) {
      final Long val = result.getLong(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(Double.class)) {
      final Double val = result.getDouble(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(Short.class)) {
      final Short val = result.getShort(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(BigDecimal.class)) {
      final BigDecimal val = result.getBigDecimal(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(String.class)) {
      final String val = result.getString(fieldName);
      method.invoke(entity, val);
    } else if (classField.equals(boolean.class)) {
      final Boolean val = result.getBoolean(fieldName);
      method.invoke(entity, val);
    } else {
      throw new KeyNotFoundException("Class type unknown: " + classField);
    }
  }

  protected Connection getConnection() throws DAOException {
    try {
      return ConnectionFactory.getConnection();
    } catch (SQLException e) {
      throw new DAOException("Couldn't open database connection", e);
    }
  }

  protected void closeConnection(final Connection connection, final PreparedStatement statement, final ResultSet result) {
    try {
      if (nonNull(result) && !result.isClosed()) {
        result.close();
      }
      if (nonNull(statement) && !statement.isClosed()) {
        statement.close();
      }
      if (nonNull(connection) && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
