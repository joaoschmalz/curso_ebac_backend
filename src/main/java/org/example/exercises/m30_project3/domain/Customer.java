package org.example.exercises.m30_project3.domain;

import org.example.exercises.m30_project3.annotations.KeyType;
import org.example.exercises.m30_project3.annotations.Table;
import org.example.exercises.m30_project3.annotations.Column;
import org.example.exercises.m30_project3.dao.Persistent;

@Table("CUSTOMER")
public class Customer implements Persistent {

  @Column(dbName = "id", setJavaName = "setId")
  private Long id;

  @Column(dbName = "name", setJavaName = "setName")
  private String name;

  @KeyType("getCpf")
  @Column(dbName = "cpf", setJavaName = "setCpf")
  private Long cpf;

  @Column(dbName = "phone", setJavaName = "setPhone")
  private Long phone;

  @Column(dbName = "address", setJavaName = "setAddress")
  private String address;

  @Column(dbName = "address_number", setJavaName = "setAddressNumber")
  private Integer addressNumber;

  @Column(dbName = "city", setJavaName = "setCity")
  private String city;

  @Column(dbName = "state", setJavaName = "setState")
  private String state;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCpf() {
    return cpf;
  }

  public void setCpf(Long cpf) {
    this.cpf = cpf;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getAddressNumber() {
    return addressNumber;
  }

  public void setAddressNumber(Integer addressNumber) {
    this.addressNumber = addressNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
