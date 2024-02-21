package org.example.exercises.m17_generics.second.dao;

import org.example.exercises.m17_generics.second.Storage;
import org.example.exercises.m17_generics.second.dao.generic.GenericDAO;
import org.example.exercises.m17_generics.second.domain.Peugeout;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class PeugeoutDAO extends GenericDAO<Peugeout> implements IPeugeoutDAO {

  public PeugeoutDAO() {
    super();
    Map<Long, Peugeout> entityMap = (Map<Long, Peugeout>) Storage.getInstance().getMap().get(Peugeout.class);
    if (isNull(entityMap)) {
      entityMap = new HashMap<>();
      Storage.getInstance().getMap(). put(Peugeout.class, entityMap);
    }
  }

  @Override
  public Class<Peugeout> getClassType() {
    return Peugeout.class;
  }
}
