package org.example.exercises.m30_project3.domain;

import org.example.exercises.m30_project3.annotations.Column;

import java.math.BigDecimal;

public class ProductAmount {

  @Column(dbName = "id", setJavaName = "setId")
  private Long id;

  private Product product;

  @Column(dbName = "amount", setJavaName = "setAmount")
  private Integer amount = 0;

  @Column(dbName = "total_price", setJavaName = "setTotalPrice")
  private BigDecimal totalPrice = BigDecimal.ZERO;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public void add(final Integer amount) {
    this.amount += amount;
    final BigDecimal newPrice = this.product.getPrice().multiply(BigDecimal.valueOf(amount));
    this.totalPrice = this.totalPrice.add(newPrice);
  }

  public void remove(final Integer amount) {
    this.amount -= amount;
    final BigDecimal newPrice = this.product.getPrice().multiply(BigDecimal.valueOf(amount));
    this.totalPrice = this.totalPrice.subtract(newPrice);
  }

}
