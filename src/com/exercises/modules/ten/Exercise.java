package com.exercises.modules.ten;

import java.util.Scanner;

public class Exercise {

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);

        System.out.println("Inform the respective grades");

        double first = i.nextDouble();
        double second = i.nextDouble();
        double third = i.nextDouble();
        double fourth = i.nextDouble();

        final double average = calculateAverage(first, second, third, fourth);

        if(average >= 7.0) {
            System.out.println("Student average: " + average);
            System.out.println("APPROVED");
        } else if (average >= 5) {
            System.out.println("Student average: " + average);
            System.out.println("FINAL EXAM");
        } else {
            System.out.println("Student average: " + average);
            System.out.println("REPROVED");
        }
    }

    private static double calculateAverage(double firstGrade, double secondGrade, double thirdGrade, double fourthGrade){
        return (firstGrade + secondGrade + thirdGrade + fourthGrade) / 4;
    }
}
