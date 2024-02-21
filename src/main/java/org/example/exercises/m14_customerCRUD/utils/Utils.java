package org.example.exercises.m14_customerCRUD.utils;

import static java.util.Objects.isNull;

public class Utils {

  public static boolean isNullOrEmpty(final String string) {
    return isNull(string) || string.isEmpty();
  }
}
