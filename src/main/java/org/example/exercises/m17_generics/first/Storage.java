package org.example.exercises.m17_generics.first;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class Storage {

  private static Storage storage;
  protected Map<Class, Map<String, ?>> map;

  private Storage() {
    map = new HashMap<>();
  }

  public static Storage getInstance() {
    if (isNull(storage)) {
      storage = new Storage();
    }
    return storage;
  }

  public Map<Class, Map<String, ?>> getMap() {
    return this.map;
  }
}
