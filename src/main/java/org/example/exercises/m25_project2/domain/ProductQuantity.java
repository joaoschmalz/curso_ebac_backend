package org.example.exercises.m25_project2.domain;

import java.math.BigDecimal;

public class ProductQuantity {

  private Product product;
  private Integer quantity;
  private BigDecimal totalPrice;

  public ProductQuantity() {
    this.quantity = 0;
    this.totalPrice = BigDecimal.ZERO;
  }

  public void add(Integer quantity) {
    this.quantity += quantity;
    BigDecimal productTotal = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
    this.totalPrice = this.totalPrice.add(productTotal);
  }

  public void remove(Integer quantity) {
    this.quantity -= quantity;
    this.totalPrice = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }
}
