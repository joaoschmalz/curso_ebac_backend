package org.example.exercises.m15_designPatterns;

public abstract class Car {

    private final int power;
    private final String fuel;
    private final String color;
    private final String modelName;
    private final int modelYear;

    public Car(int power, String fuel, String color, String modelName, int modelYear) {
        this.power = power;
        this.fuel = fuel;
        this.color = color;
        this.modelName = modelName;
        this.modelYear = modelYear;
    }

    public void accelerationPower() {
        if(this.power > 0 && this.power < 500) {
            System.out.println("This car goes from 0 to 100 in 15 seconds");
        } else if (this.power > 500 && this.power < 1000) {
            System.out.println("This car goes from 0 to 100 in 10 seconds");
        } else {
            System.out.println("This car goes from 0 to 100 in 6 seconds");
        }
    }

    public void fuelType() {
        System.out.println("This car uses: " + fuel);
    }

    public void modelInfo() {
        System.out.println("This is a " + modelYear + " - " + color + " - " + modelName);
    }
}
