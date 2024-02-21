package org.example.exercises.m30_project3.dao;

import org.example.exercises.m30_project3.dao.factory.ProductAmountFactory;
import org.example.exercises.m30_project3.dao.factory.SaleFactory;
import org.example.exercises.m30_project3.dao.generic.GenericDAO;
import org.example.exercises.m30_project3.domain.ProductAmount;
import org.example.exercises.m30_project3.domain.Sale;
import org.example.exercises.m30_project3.domain.Sale.Status;
import org.example.exercises.m30_project3.exceptions.DAOException;
import org.example.exercises.m30_project3.exceptions.DuplicatedEntryException;
import org.example.exercises.m30_project3.exceptions.KeyNotFoundException;
import org.example.exercises.m30_project3.exceptions.TableException;

import java.sql.*;
import java.util.*;

public class SalesDAO extends GenericDAO<Sale, String> implements ISalesDAO {

  @Override
  public Class<Sale> getClassType() {
    return Sale.class;
  }

  @Override
  public void updateData(final Sale newEntity, final Sale entity) {
    entity.setCode(newEntity.getCode());
    entity.setStatus(newEntity.getStatus());
  }

  @Override
  protected String getInsertQuery() {
    return " INSERT INTO sale(code, customer_id, total_price, created_at, status) "
         + " VALUES (?, ?, ?, ?, ?) ";
  }

  @Override
  protected String getDeleteQuery() {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  protected String getUpdateQuery() {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  protected void setInsertParam(final PreparedStatement statement, final Sale entity) throws SQLException {
    statement.setString(1, entity.getCode());
    statement.setLong(2, entity.getCustomer().getId());
    statement.setBigDecimal(3, entity.getTotalPrice());
    statement.setTimestamp(4, Timestamp.from(entity.getCreatedAt()));
    statement.setString(5, entity.getStatus().name());
  }

  @Override
  protected void setDeleteParam(final PreparedStatement statement, final String value) throws SQLException {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  protected void setUpdateParam(final PreparedStatement statement, final Sale entity) throws SQLException {
    throw new UnsupportedOperationException("Not allowed");
  }

  @Override
  protected void setSelectParam(final PreparedStatement statement, final String value) throws SQLException {
    statement.setString(1, value);
  }

  @Override
  public void finishSale(Sale sale) throws DAOException {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      String sql = " UPDATE sale SET status = ? WHERE id = ? ";
      connection = getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, Status.FINISHED.name());
      statement.setLong(2, sale.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was an error updating object", e);
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public void cancelSale(Sale sale) throws DAOException {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      String sql = " UPDATE sale SET status = ? WHERE id = ? ";
      connection = getConnection();
      statement = connection.prepareStatement(sql);
      statement.setString(1, Status.CANCELED.name());
      statement.setLong(2, sale.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException("There was an error updating object", e);
    } finally {
      closeConnection(connection, statement, null);
    }
  }

  @Override
  public Sale retrieve(final String value) throws DuplicatedEntryException, TableException, DAOException {
    String sql = this.sqlBaseSelect() + " WHERE code = ? ";
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
      connection = getConnection();
      statement = connection.prepareStatement(sql);
      this.setSelectParam(statement, value);
      result = statement.executeQuery();

      if (result.next()) {
        final Sale sale = SaleFactory.mapper(result);
        this.populateSaleProducts(connection, sale);
        return sale;
      }
    } catch (SQLException e) {
      throw new DAOException("There was an error retriving object", e);
    } finally {
      closeConnection(connection, statement, result);
    }
    return null;
  }

  @Override
  public Collection<Sale> retrieveAll() throws DAOException {
    List<Sale> list = new ArrayList<>();
    String sql = this.sqlBaseSelect();

    try {
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();

      while (result.next()) {
        Sale sale = SaleFactory.mapper(result);
        populateSaleProducts(connection, sale);
        list.add(sale);
      }
    } catch (SQLException e) {
      throw new DAOException("There was an error retriving object", e);
    }
    return list;
  }

  @Override
  public Boolean save(final Sale entity) throws KeyNotFoundException, DAOException {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = getConnection();
      statement = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS);
      setInsertParam(statement, entity);
      int affectedRows = statement.executeUpdate();

      if (affectedRows > 0) {
        try (ResultSet result = statement.getGeneratedKeys()) {
          if (result.next()) {
            entity.setId(result.getLong(1));
          }
        }

        for (ProductAmount prod : entity.getProducts()) {
          statement = connection.prepareStatement(getInsertProdAmount());
          setInsertProdAmountParam(statement, entity, prod);
          affectedRows = statement.executeUpdate();
        }
        return true;
      }
    } catch (SQLException e) {
      throw new DAOException("There was an error saving product", e);
    } finally {
      closeConnection(connection, statement, null);
    }
    return false;
  }

  private String sqlBaseSelect() {
    return " SELECT s.id AS sale_id, "
         + "        s.code, "
         + "        s.total_price, "
         + "        s.created_at, "
         + "        s.status, "
         + "        c.id AS customer_id, "
         + "        c.name, "
         + "        c.cpf, "
         + "        c.phone, "
         + "        c.address, "
         + "        c.address_number, "
         + "        c.city, "
         + "        c.state, "
         + "        c.active "
         + " FROM sale s "
         + " INNER JOIN customer c ON s.customer_id = c.id ";
  }

  private void populateSaleProducts(final Connection connection, final Sale sale) throws DAOException {
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
      String sql = " SELECT pa.id, "
                 + "        pa.amount, "
                 + "        pa.total_price, "
                 + "        p.id AS product_id, "
                 + "        p.code, "
                 + "        p.name, "
                 + "        p.description, "
                 + "        p.price, "
                 + "        p.active "
                 + " FROM product_amount pa "
                 + " INNER JOIN product p ON p.id = pa.product_id "
                 + " WHERE pa.sale_id = ? ";

      statement = connection.prepareStatement(sql);
      statement.setLong(1, sale.getId());
      result = statement.executeQuery();
      Set<ProductAmount> products = new HashSet<>();

      while (result.next()) {
        ProductAmount prodAmount = ProductAmountFactory.mapper(result);
        products.add(prodAmount);
      }

      sale.setProducts(products);
      sale.recalculateTotalPrice();
    } catch (SQLException e) {
      throw new DAOException("There was an error retrieve object", e);
    } finally {
      closeConnection(connection, statement, result);
    }
  }

  private String getInsertProdAmount() {
    return " INSERT INTO product_amount "
         + "     (product_id, sale_id, amount, total_price) "
         + " VALUES (?, ?, ?, ?) ";
  }

  private void setInsertProdAmountParam(final PreparedStatement statement, final Sale sale, final ProductAmount prod) throws SQLException {
    statement.setLong(1, prod.getProduct().getId());
    statement.setLong(2, sale.getId());
    statement.setInt(3, prod.getAmount());
    statement.setBigDecimal(4, prod.getTotalPrice());
  }
}
