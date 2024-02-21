package org.example.exercises.m25_project2.domain;

import org.example.exercises.m25_project2.annotation.KeyType;
import org.example.exercises.m25_project2.dao.Persistable;

public class Customer implements Persistable {
  @KeyType("getCpf")
  private Long cpf;
  private String name;
  private String email;

  public Customer() {
  }

  public Long getCpf() {
    return cpf;
  }

  public void setCpf(Long cpf) {
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
