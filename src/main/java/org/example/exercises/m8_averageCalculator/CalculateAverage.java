package org.example.exercises.m8_averageCalculator;

public class CalculateAverage {

  public static void main(String args[]) {
    calculateAverage();
  }

  private static void calculateAverage() {
    float num1 = 9.5f;
    float num2 = 8.2f;
    float num3 = 6.9f;
    float num4 = 8.9f;

    float average = (num1 + num2 + num3 + num4) / 4;

    System.out.println("The average between the given numbers is: " + average);
  }
}
