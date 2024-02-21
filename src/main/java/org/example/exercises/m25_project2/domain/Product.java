package org.example.exercises.m25_project2.domain;

import org.example.exercises.m25_project2.annotation.KeyType;
import org.example.exercises.m25_project2.dao.Persistable;

import java.math.BigDecimal;

public class Product implements Persistable {

  @KeyType("getCode")
  private String code;
  private String name;
  private String description;
  private BigDecimal price;

  public Product() {
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
}
