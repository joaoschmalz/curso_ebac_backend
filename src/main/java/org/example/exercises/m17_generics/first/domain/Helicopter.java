package org.example.exercises.m17_generics.first.domain;

import java.util.Date;

public class Helicopter implements Persistent {

  private String modelName;
  private String brandName;
  private Date fabricationDate;
  private String useType;
  private int passengersCapacity;
  private int fuelCapacityInLiters;

  public Helicopter(String modelName, String brandName, Date fabricationDate, String useType, int passengersCapacity, int fuelCapacityInLiters) {
    this.modelName = modelName;
    this.brandName = brandName;
    this.fabricationDate = fabricationDate;
    this.useType = useType;
    this.passengersCapacity = passengersCapacity;
    this.fuelCapacityInLiters = fuelCapacityInLiters;
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

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public Date getFabricationDate() {
    return fabricationDate;
  }

  public void setFabricationDate(Date fabricationDate) {
    this.fabricationDate = fabricationDate;
  }

  public String getUseType() {
    return useType;
  }

  public void setUseType(String useType) {
    this.useType = useType;
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
}
