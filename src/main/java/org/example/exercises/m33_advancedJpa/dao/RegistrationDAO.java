package org.example.exercises.m33_advancedJpa.dao;

import org.example.exercises.m33_advancedJpa.domain.Registration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RegistrationDAO implements IRegistrationDAO {
  @Override
  public Registration save(Registration registration) {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(registration);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();

    return registration;
  }
}
