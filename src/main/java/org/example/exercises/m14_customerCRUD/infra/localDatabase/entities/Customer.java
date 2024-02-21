package org.example.exercises.m14_customerCRUD.infra.localDatabase.entities;

public class Customer {
  private String name;
  private String cpf;
  private String phone;
  private String address;
  private String city;
  private String state;

  public Customer() {  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  @Override
  public String toString() {
    return "Customer {" +
            "name='" + name + '\'' +
            ", cpf='" + cpf + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
