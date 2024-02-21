package org.example.exercises.m29_project3.modules.customer.domain;

public class Customer {

  private Long id;
  private String code;
  private String name;

  public static Customer create (String code, String name) {
    Customer customer = new Customer();

    customer.setCode(code);
    customer.setName(name);

    return customer;
  }

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
}
