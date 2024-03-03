package org.example.exercises.m32_jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;

//@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_product")
  @SequenceGenerator(name = "sequence_product", sequenceName = "sq_product", initialValue = 1, allocationSize = 1)
  private Long id;
  @Column(name = "code", length = 10, nullable = false, unique = true)
  private String code;
  @Column(name = "name", length = 255, nullable = false)
  private String name;
  @Column(name = "description", length = 400, nullable = false)
  private String description;
  @Column(name = "price", nullable = false)
  private BigDecimal price;
  @Column(name = "active", nullable = false)
  private boolean active;

  public Long getId() {
    return id;
  }

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
