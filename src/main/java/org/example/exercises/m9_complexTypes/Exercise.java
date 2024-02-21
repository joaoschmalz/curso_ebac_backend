package org.example.exercises.m9_complexTypes;

import java.util.Scanner;

public class Exercise {
    public static void main(String args[]) {

        Scanner i = new Scanner(System.in);

        System.out.println("Enter a number");
        final int primitive = i.nextInt();
        final Integer wrapper = primitive;

        System.out.println("Value informed = " + wrapper);
    }
}
