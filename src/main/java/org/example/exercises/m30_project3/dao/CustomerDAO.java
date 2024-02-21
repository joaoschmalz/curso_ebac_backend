package org.example.exercises.m30_project3.dao;

import org.example.exercises.m30_project3.dao.generic.GenericDAO;
import org.example.exercises.m30_project3.domain.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO extends GenericDAO<Customer, Long> implements ICustomerDAO {

  @Override
  public Class<Customer> getClassType() {
    return Customer.class;
  }

  @Override
  public void updateData(final Customer newEntity, final Customer entity) {
    entity.setName(newEntity.getName());
    entity.setCpf(newEntity.getCpf());
    entity.setPhone(newEntity.getPhone());
    entity.setAddress(newEntity.getAddress());
    entity.setAddressNumber(newEntity.getAddressNumber());
    entity.setCity(newEntity.getCity());
    entity.setState(newEntity.getState());
    entity.setActive(newEntity.isActive());
  }

  @Override
  protected String getInsertQuery() {
    return " INSERT INTO customer (name, cpf, phone, address, address_number, city, state, active) "
         + " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
  }

  @Override
  protected String getDeleteQuery() {
    return " DELETE FROM customer WHERE cpf = ? ";
  }

  @Override
  protected String getUpdateQuery() {
    return " UPDATE customer "
         + " SET name = ?, "
         + "     phone = ?, "
         + "     address = ?, "
         + "     address_number = ?, "
         + "     city = ?, "
         + "     state = ?, "
         + "     active = ? "
         + " WHERE cpf = ?  ";
  }

  @Override
  protected void setInsertParam(final PreparedStatement statement, final Customer entity) throws SQLException {
    statement.setString(1, entity.getName());
    statement.setLong(2, entity.getCpf());
    statement.setLong(3, entity.getPhone());
    statement.setString(4, entity.getAddress());
    statement.setLong(5, entity.getAddressNumber());
    statement.setString(6, entity.getCity());
    statement.setString(7, entity.getState());
    statement.setBoolean(8, entity.isActive());
  }

  @Override
  protected void setDeleteParam(final PreparedStatement statement, final Long value) throws SQLException {
    statement.setLong(1, value);
  }

  @Override
  protected void setUpdateParam(final PreparedStatement statement, final Customer entity) throws SQLException {
    statement.setString(1, entity.getName());
    statement.setLong(2, entity.getPhone());
    statement.setString(3, entity.getAddress());
    statement.setLong(4, entity.getAddressNumber());
    statement.setString(5, entity.getCity());
    statement.setString(6, entity.getState());
    statement.setBoolean(7, entity.isActive());
    statement.setLong(8, entity.getCpf());
  }

  @Override
  protected void setSelectParam(final PreparedStatement statement, final Long value) throws SQLException {
    statement.setLong(1, value);
  }
}
