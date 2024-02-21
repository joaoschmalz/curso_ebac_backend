package org.example.exercises.m11_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise {

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);

        List<String> male = new ArrayList<>();
        List<String> female = new ArrayList<>();
        List<String> noGender = new ArrayList<>();

        for (int count = 0; count < 10; count++) {
            System.out.println("Please inform your name");
            String name = i.next();

            System.out.println("Please inform the gender");
            System.out.println("M for Male, F for female or any other key for skip question");
            String gender = i.next();

            if ("M".equalsIgnoreCase(gender)) {
                male.add(name);
            } else if ("F".equals(gender)) {
                female.add(name);
            } else {
                noGender.add(name);
            }
        }

        System.out.println("Mens = " + male);
        System.out.println("Female = " + female);
        System.out.println("NoGender = " + noGender);
    }
}
