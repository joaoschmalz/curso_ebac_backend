package org.example.exercises.m12_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise02 {

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);

        List<String> male = new ArrayList<>();
        List<String> female = new ArrayList<>();
        List<String> noGender = new ArrayList<>();

        System.out.println("Please inform your name followed by your gender");
        System.out.println("Use M for Male, F for female or any other key for skip question");
        System.out.println("Example: John-M");

        for (int count = 0; count < 10; count++) {
            if (count != 0) System.out.println("Entry another:");

            String line = i.nextLine();
            String[] person = line.split("-");

            if ("M".equalsIgnoreCase(person[1])) {
                male.add(person[0]);
            } else if ("F".equals(person[1])) {
                female.add(person[0]);
            } else {
                noGender.add(person[0]);
            }
        }

        System.out.println("Mens = " + male);
        System.out.println("Female = " + female);
        System.out.println("NoGender = " + noGender);
    }
}
