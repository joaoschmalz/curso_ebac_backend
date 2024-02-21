package org.example.exercises.m30_project3.domain;

import org.example.exercises.m30_project3.annotations.KeyType;
import org.example.exercises.m30_project3.annotations.Table;
import org.example.exercises.m30_project3.annotations.Column;
import org.example.exercises.m30_project3.dao.Persistent;

import java.math.BigDecimal;

@Table("PRODUCT")
public class Product implements Persistent {

  @Column(dbName = "id", setJavaName = "setId")
  private Long id;

  @KeyType("getCode")
  @Column(dbName = "code", setJavaName = "setCode")
  private String code;

  @Column(dbName = "name", setJavaName = "setName")
  private String name;

  @Column(dbName = "description", setJavaName = "setDescription")
  private String description;

  @Column(dbName = "price", setJavaName = "setPrice")
  private BigDecimal price;

  @Column(dbName = "active", setJavaName = "setActive")
  private boolean active;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
