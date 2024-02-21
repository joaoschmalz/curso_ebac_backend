package org.example.exercises.m17_generics.first.domain;

import java.util.Date;

public class Bus implements Persistent {

  private String modelName;
  private Date fabricationDate;
  private int passengersCapacity;
  private int fuelCapacityInLiters;
  private int horsePower;
  private int numberWheels;

  public Bus(String modelName, Date fabricationDate, int passengersCapacity, int fuelCapacityInLiters, int horsePower, int numberWheels) {
    this.modelName = modelName;
    this.fabricationDate = fabricationDate;
    this.passengersCapacity = passengersCapacity;
    this.fuelCapacityInLiters = fuelCapacityInLiters;
    this.horsePower = horsePower;
    this.numberWheels = numberWheels;
  }

  @Override
  public String getIdentifier() {
    return this.getModelName();
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public Date getFabricationDate() {
    return fabricationDate;
  }

  public void setFabricationDate(Date fabricationDate) {
    this.fabricationDate = fabricationDate;
  }

  public int getPassengersCapacity() {
    return passengersCapacity;
  }

  public void setPassengersCapacity(int passengersCapacity) {
    this.passengersCapacity = passengersCapacity;
  }

  public int getFuelCapacityInLiters() {
    return fuelCapacityInLiters;
  }

  public void setFuelCapacityInLiters(int fuelCapacityInLiters) {
    this.fuelCapacityInLiters = fuelCapacityInLiters;
  }

  public int getHorsePower() {
    return horsePower;
  }

  public void setHorsePower(int horsePower) {
    this.horsePower = horsePower;
  }

  public int getNumberWheels() {
    return numberWheels;
  }

  public void setNumberWheels(int numberWheels) {
    this.numberWheels = numberWheels;
  }
}
