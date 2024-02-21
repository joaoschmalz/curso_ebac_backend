package org.example.exercises.m30_project3.dao;

import org.example.exercises.m30_project3.dao.generic.GenericDAO;
import org.example.exercises.m30_project3.domain.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO extends GenericDAO<Product, String> implements IProductDAO {
  @Override
  public Class<Product> getClassType() {
    return Product.class;
  }

  @Override
  public void updateData(final Product newEntity, final Product entity) {
    entity.setCode(newEntity.getCode());
    entity.setDescription(newEntity.getDescription());
    entity.setName(newEntity.getName());
    entity.setPrice(newEntity.getPrice());
  }

  @Override
  protected String getInsertQuery() {
    return " INSERT INTO product (code, name, description, price) "
         + " VALUES(?, ?, ?, ?) ";
  }

  @Override
  protected String getDeleteQuery() {
    return " DELETE FROM product WHERE code = ?";
  }

  @Override
  protected String getUpdateQuery() {
    return " UPDATE product "
         + " SET code = ?, "
         + "     name = ?, "
         + "     description = ?, "
         + "     price = ?, "
         + "     active = ? "
         + " WHERE code = ? ";
  }

  @Override
  protected void setInsertParam(final PreparedStatement statement, final Product entity) throws SQLException {
    statement.setString(1, entity.getCode());
    statement.setString(2, entity.getName());
    statement.setString(3, entity.getDescription());
    statement.setBigDecimal(4, entity.getPrice());
  }

  @Override
  protected void setDeleteParam(final PreparedStatement statement, final String value) throws SQLException {
    statement.setString(1, value);
  }

  @Override
  protected void setUpdateParam(final PreparedStatement statement, final Product entity) throws SQLException {
    statement.setString(1, entity.getCode());
    statement.setString(2, entity.getName());
    statement.setString(3, entity.getDescription());
    statement.setBigDecimal(4, entity.getPrice());
    statement.setBoolean(5, entity.isActive());
    statement.setString(6, entity.getCode());
  }

  @Override
  protected void setSelectParam(PreparedStatement statement, String value) throws SQLException {
    statement.setString(1, value);
  }
}
