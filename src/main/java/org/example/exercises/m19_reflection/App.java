package org.example.exercises.m19_reflection;

import java.lang.reflect.Field;

public class App {

    public static void main(String[] args) {

        final DBTargetTables target = new DBTargetTables();
        final Field[] fields =  target.getClass().getDeclaredFields();

        for(Field f : fields) {
            System.out.println(f.getAnnotation(Table.class).value());;
        }
    }
}
