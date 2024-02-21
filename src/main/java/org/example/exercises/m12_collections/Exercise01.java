package org.example.exercises.m12_collections;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise01 {

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);

        System.out.println("Entry the list of names");
        String groupedNames = i.nextLine();
        String[] names = groupedNames.split(",");

        Arrays.sort(names);

        System.out.println(Arrays.toString(names));
    }
}
