package org.example.exercises.m30_project3.dao.factory;

import org.example.exercises.m30_project3.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFactory {

  public static Customer mapper(final ResultSet result) throws SQLException {
    final Customer customer = new Customer();

    customer.setId(result.getLong("customer_id"));
    customer.setName(result.getString("name"));
    customer.setCpf(result.getLong("cpf"));
    customer.setPhone(result.getLong("phone"));
    customer.setAddress(result.getString("address"));
    customer.setAddressNumber(result.getInt("address_number"));
    customer.setCity(result.getString("city"));
    customer.setState(result.getString("state"));
    customer.setActive(result.getBoolean("active"));

    return customer;
  }
}
