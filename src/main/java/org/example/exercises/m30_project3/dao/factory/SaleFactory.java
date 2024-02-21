package org.example.exercises.m30_project3.dao.factory;

import org.example.exercises.m30_project3.domain.Customer;
import org.example.exercises.m30_project3.domain.Sale;
import org.example.exercises.m30_project3.domain.Sale.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleFactory {

  public static Sale mapper(final ResultSet result) throws SQLException {
    final Customer customer = CustomerFactory.mapper(result);
    final Sale sale = new Sale();

    sale.setCustomer(customer);
    sale.setId(result.getLong("sale_id"));
    sale.setCode(result.getString("code"));
    sale.setTotalPrice(result.getBigDecimal("total_price"));
    sale.setCreatedAt(result.getTimestamp("created_at").toInstant());
    sale.setStatus(Status.from(result.getString("status")));

    return sale;
  }
}
