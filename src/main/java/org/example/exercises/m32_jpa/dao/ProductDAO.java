package org.example.exercises.m32_jpa.dao;

import org.example.exercises.m32_jpa.domain.Product;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProductDAO implements IProductDAO {

  @Override
  public Product retrieveSingle(String name) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = factory.createEntityManager();

    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
    Root<Product> from = criteria.from(Product.class);

    criteria.select(from);
    criteria.where(builder.equal(from.get("name"), name));
    TypedQuery<Product> typed = entityManager.createQuery(criteria);

    try {
      return typed.getSingleResult();
    } catch (final NoResultException noResultException) {
      return null;
    }
  }

  @Override
  public Product save(Product product) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = factory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(product);
    entityManager.getTransaction().commit();

    entityManager.close();
    factory.close();

    return product;
  }

  @Override
  public void delete(Long id) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExampleJPA");
    EntityManager entityManager = factory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.remove(this.retrieveBy(id, entityManager));
    entityManager.getTransaction().commit();

    entityManager.close();
    factory.close();
  }

  private Product retrieveBy(Long id, EntityManager entityManager) {
    String jpql = "SELECT p FROM Product p WHERE p.id = :id";
    TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
    query.setParameter("id", id);

    return query.getSingleResult();
  }
}
