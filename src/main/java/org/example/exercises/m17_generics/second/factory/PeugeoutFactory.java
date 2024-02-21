package org.example.exercises.m17_generics.second.factory;

import org.example.exercises.m17_generics.second.domain.Car;
import org.example.exercises.m17_generics.second.domain.Peugeout;
import org.example.exercises.m17_generics.second.enums.BreakType;
import org.example.exercises.m17_generics.second.enums.DirectionType;
import org.example.exercises.m17_generics.second.enums.FuelType;

public class PeugeoutFactory implements CarFactory {
  @Override
  public Car buildEntity(final String[] properties) {
    return new Peugeout(
        Long.parseLong(properties[0]),
        properties[1],
        DirectionType.valueOf(properties[2]),
        BreakType.valueOf(properties[3]),
        FuelType.valueOf(properties[4]),
        Integer.parseInt(properties[5]),
        Integer.parseInt(properties[6]),
        Integer.parseInt(properties[7]),
        Integer.parseInt(properties[8]),
        Boolean.parseBoolean(properties[9]));
  }
}
