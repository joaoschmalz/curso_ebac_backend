package org.example.exercises.m17_generics.first.dao;

import org.example.exercises.m17_generics.first.Storage;
import org.example.exercises.m17_generics.first.dao.generic.GenericDAO;
import org.example.exercises.m17_generics.first.domain.Bus;
import org.example.exercises.m17_generics.first.domain.Helicopter;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class HelicopterDAO extends GenericDAO<Helicopter> implements IHelicopterDAO {

  public HelicopterDAO() {
    super();
    Map<String, Bus> entityMap = (Map<String, Bus>) Storage.getInstance().getMap().get(getClassType());
    if (isNull(entityMap)) {
      entityMap = new HashMap<>();
      Storage.getInstance().getMap().put(getClassType(), entityMap);
    }
  }

  @Override
  public Class<Helicopter> getClassType() {
    return Helicopter.class;
  }

  @Override
  public void updateData(Helicopter newEntity, Helicopter entity) {
    entity.setBrandName(newEntity.getBrandName());
    entity.setFabricationDate(newEntity.getFabricationDate());
    entity.setModelName(newEntity.getModelName());
    entity.setUseType(newEntity.getUseType());
    entity.setFuelCapacityInLiters(newEntity.getFuelCapacityInLiters());
    entity.setPassengersCapacity(newEntity.getPassengersCapacity());
  }
}
