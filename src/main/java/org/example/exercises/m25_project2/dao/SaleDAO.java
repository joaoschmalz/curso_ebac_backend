package org.example.exercises.m25_project2.dao;

import org.example.exercises.m25_project2.dao.generic.GenericDAO;
import org.example.exercises.m25_project2.domain.Sale;
import org.example.exercises.m25_project2.exceptions.KeyTypeNotFoundException;

public class SaleDAO extends GenericDAO<Sale, String> implements ISaleDAO {
  @Override
  public Class<Sale> getClassType() {
    return Sale.class;
  }

  @Override
  public void updateData(Sale newEntity, Sale entity) {
    entity.setCode(newEntity.getCode());
    entity.setStatus(newEntity.getStatus());
  }

  @Override
  public void delete(String value) {
    throw new UnsupportedOperationException("Operation not allowed");
  }

  @Override
  public void finishSale(Sale sale) throws KeyTypeNotFoundException {
    sale.setStatus(Sale.Status.FINISHED);
    super.update(sale);
  }
}
