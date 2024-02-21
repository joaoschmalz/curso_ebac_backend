package org.example.exercises.m32_jpa.domain;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "registration")
public class Registration {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_registration")
  @SequenceGenerator(name = "sequence_registration", sequenceName = "sq_registration", initialValue = 1, allocationSize = 1)
  private Long id;
  @Column(name = "code", length = 10, nullable = false, unique = true)
  private String code;
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
  @Column(name = "value", nullable = false)
  private Double value;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
