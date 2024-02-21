package org.example.exercises.m17_generics.second.domain;

import org.example.exercises.m17_generics.second.enums.BreakType;
import org.example.exercises.m17_generics.second.enums.DirectionType;
import org.example.exercises.m17_generics.second.enums.FuelType;

public abstract class Car {

  private long code;
  private String model;
  private DirectionType directionType;
  private BreakType breakType;
  private FuelType fuelType;
  private int fabricationYear;
  private int horsePower;
  private int fuelCapacity;
  private int maxSpeed;

  public Car(long code, String model, DirectionType directionType, BreakType breakType, FuelType fuelType, int fabricationYear, int horsePower, int fuelCapacity, int maxSpeed) {
    this.code = code;
    this.model = model;
    this.directionType = directionType;
    this.breakType = breakType;
    this.fuelType = fuelType;
    this.fabricationYear = fabricationYear;
    this.horsePower = horsePower;
    this.fuelCapacity = fuelCapacity;
    this.maxSpeed = maxSpeed;
  }

  public long getCode() {
    return code;
  }

  public void setCode(long code) {
    this.code = code;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public DirectionType getDirectionType() {
    return directionType;
  }

  public void setDirectionType(DirectionType directionType) {
    this.directionType = directionType;
  }

  public BreakType getBreakType() {
    return breakType;
  }

  public void setBreakType(BreakType breakType) {
    this.breakType = breakType;
  }

  public FuelType getFuelType() {
    return fuelType;
  }

  public void setFuelType(FuelType fuelType) {
    this.fuelType = fuelType;
  }

  public int getFabricationYear() {
    return fabricationYear;
  }

  public void setFabricationYear(int fabricationYear) {
    this.fabricationYear = fabricationYear;
  }

  public int getHorsePower() {
    return horsePower;
  }

  public void setHorsePower(int horsePower) {
    this.horsePower = horsePower;
  }

  public int getFuelCapacity() {
    return fuelCapacity;
  }

  public void setFuelCapacity(int fuelCapacity) {
    this.fuelCapacity = fuelCapacity;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(int maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
}
