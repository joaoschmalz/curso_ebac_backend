package org.example.exercises.m17_generics.second.domain;

import org.example.exercises.m17_generics.second.enums.BreakType;
import org.example.exercises.m17_generics.second.enums.DirectionType;
import org.example.exercises.m17_generics.second.enums.FuelType;

public class Honda extends Car {

  private boolean vTec;
  private boolean magicSeat;

  public Honda(long code, String model, DirectionType directionType, BreakType breakType, FuelType fuelType, int fabricationYear, int horsePower, int fuelCapacity, int maxSpeed, boolean vTec, boolean magicSeat) {
    super(code, model, directionType, breakType, fuelType, fabricationYear, horsePower, fuelCapacity, maxSpeed);
    this.vTec = vTec;
    this.magicSeat = magicSeat;
  }

  public boolean isvTec() {
    return vTec;
  }

  public void setvTec(boolean vTec) {
    this.vTec = vTec;
  }

  public boolean isMagicSeat() {
    return magicSeat;
  }

  public void setMagicSeat(boolean magicSeat) {
    this.magicSeat = magicSeat;
  }
}
