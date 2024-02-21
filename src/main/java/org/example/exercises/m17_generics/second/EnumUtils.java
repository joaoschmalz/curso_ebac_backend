package org.example.exercises.m17_generics.second;

import static java.util.Objects.isNull;

public class EnumUtils {

  public static <T extends Enum<T>> boolean isValidEnum(final Class<T> enumClass, final String value) {
    if (isNull(value)) {
      return false;
    }
    try {
      Enum.valueOf(enumClass, value.toUpperCase());
      return true;
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }
}
