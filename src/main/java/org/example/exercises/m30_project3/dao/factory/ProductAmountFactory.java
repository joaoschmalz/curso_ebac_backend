package org.example.exercises.m30_project3.dao.factory;


import org.example.exercises.m30_project3.domain.Product;
import org.example.exercises.m30_project3.domain.ProductAmount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductAmountFactory {

  public static ProductAmount mapper(final ResultSet result) throws SQLException {
    final Product product = ProductFactory.mapper(result);
    final ProductAmount productAmount = new ProductAmount();

    productAmount.setProduct(product);
    productAmount.setId(result.getLong("id"));
    productAmount.setAmount(result.getInt("amount"));
    productAmount.setTotalPrice(result.getBigDecimal("total_price"));

    return productAmount;
  }
}
