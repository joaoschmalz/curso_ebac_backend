package org.example.exercises.m17_generics.second.factory;

import org.example.exercises.m17_generics.second.domain.Car;
import org.example.exercises.m17_generics.second.domain.Honda;
import org.example.exercises.m17_generics.second.enums.BreakType;
import org.example.exercises.m17_generics.second.enums.DirectionType;
import org.example.exercises.m17_generics.second.enums.FuelType;

public class HondaFactory implements CarFactory {
  @Override
  public Car buildEntity(final String[] properties) {
    return new Honda(
        Long.parseLong(properties[0]),
        properties[1],
        DirectionType.valueOf(properties[2].toUpperCase()),
        BreakType.valueOf(properties[3].toUpperCase()),
        FuelType.valueOf(properties[4].toUpperCase()),
        Integer.parseInt(properties[5]),
        Integer.parseInt(properties[6]),
        Integer.parseInt(properties[7]),
        Integer.parseInt(properties[8]),
        Boolean.parseBoolean(properties[9]),
        Boolean.parseBoolean(properties[10]));
  }
}
