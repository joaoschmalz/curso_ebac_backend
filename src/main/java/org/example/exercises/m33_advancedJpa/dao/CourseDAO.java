package org.example.exercises.m33_advancedJpa.dao;

import org.example.exercises.m33_advancedJpa.domain.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CourseDAO implements ICourseDAO {

  @Override
  public Course save(Course course) {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(course);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();

    return course;
  }
}
