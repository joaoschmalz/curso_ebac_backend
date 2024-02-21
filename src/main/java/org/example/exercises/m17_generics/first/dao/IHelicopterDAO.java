package org.example.exercises.m17_generics.first.dao;

import org.example.exercises.m17_generics.first.dao.generic.IGenericDAO;
import org.example.exercises.m17_generics.first.domain.Helicopter;

public interface IHelicopterDAO extends IGenericDAO<Helicopter> {
}
