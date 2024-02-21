package org.example.exercises.m30_project3.dao.factory;

import org.example.exercises.m30_project3.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductFactory {

  public static Product mapper(final ResultSet result) throws SQLException {
    final Product product = new Product();

    product.setId(result.getLong("product_id"));
    product.setCode(result.getString("code"));
    product.setName(result.getString("name"));
    product.setDescription(result.getString("description"));
    product.setPrice(result.getBigDecimal("price"));
    product.setActive(result.getBoolean("active"));

    return product;
  }
}
