package org.example.exercises.m15_designPatterns;

public abstract class Factory {

    public Car create(String requested) {
        Car car = retrieveCar(requested);
        carDemonstration(car);
        return car;
    }

    private void carDemonstration(Car car) {
        car.modelInfo();
        car.fuelType();
        car.accelerationPower();
    }

    abstract Car retrieveCar(String requested);
}
