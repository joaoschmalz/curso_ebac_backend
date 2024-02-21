package org.example.exercises.m17_generics.second.factory;

import org.example.exercises.m17_generics.second.domain.Car;

public interface CarFactory {

  Car buildEntity(String[] properties);
}
