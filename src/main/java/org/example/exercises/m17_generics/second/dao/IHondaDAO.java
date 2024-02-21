package org.example.exercises.m17_generics.second.dao;

import org.example.exercises.m17_generics.second.dao.generic.IGenericDAO;
import org.example.exercises.m17_generics.second.domain.Honda;

public interface IHondaDAO extends IGenericDAO<Honda> {
}
