package org.example.exercises.m33_advancedJpa.dao;

import org.example.exercises.m33_advancedJpa.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentDAO implements IStudentDAO {
  @Override
  public Student save(Student student) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(student);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();

    return student;
  }
}
