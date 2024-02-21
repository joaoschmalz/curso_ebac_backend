package org.example.exercises.m25_project2.domain;

import org.example.exercises.m25_project2.annotation.KeyType;
import org.example.exercises.m25_project2.dao.Persistable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Sale implements Persistable {

  public enum Status { STARTED, FINISHED, CANCELED }

  @KeyType("getCode")
  private String code;
  private Customer customer;
  private Set<ProductQuantity> products;
  private BigDecimal totalPrice;
  private Instant saleDate;
  private Status status;

  public Sale() {
    this.products = new HashSet<>();
  }

  public void checkStatus() {
    if (this.status == Status.FINISHED) {
      throw new UnsupportedOperationException("Sale already finished, impossible to update");
    }
  }

  public void addProduct(Product product, Integer quantity) {
    this.checkStatus();
    Optional<ProductQuantity> op = products.stream().filter(p -> p.getProduct().getCode().equals(product.getCode())).findAny();
    if(op.isPresent()) {
      ProductQuantity productQuantity = op.get();
      productQuantity.add(quantity);
    } else {
      ProductQuantity productQuantity = new ProductQuantity();
      productQuantity.setProduct(product);
      productQuantity.add(quantity);
      this.products.add(productQuantity);
    }
    this.recalculateTotalSalesValue();
  }

  public void removeProduct(Product product, Integer quantity) {
    this.checkStatus();
    Optional<ProductQuantity> op = this.products.stream().filter(p -> p.getProduct().getCode().equals(product.getCode())).findAny();

    if (op.isPresent()) {
      ProductQuantity productQuantity = op.get();
      if (productQuantity.getQuantity() > quantity) {
        productQuantity.remove(quantity);
        this.recalculateTotalSalesValue();
      } else {
        this.products.remove(op.get());
        this.recalculateTotalSalesValue();
      }
    }
  }

  public void clearSale() {
    this.checkStatus();
    this.products.clear();
    this.totalPrice = BigDecimal.ZERO;
  }

  public Integer getTotalProducts() {
    return this.products.stream().reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
  }

  private void recalculateTotalSalesValue() {
    this.checkStatus();
    BigDecimal totalPrice = BigDecimal.ZERO;
    for (ProductQuantity p : this.products) {
      totalPrice = totalPrice.add(p.getTotalPrice());
    }
    this.totalPrice = totalPrice;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Set<ProductQuantity> getProducts() {
    return products;
  }

  public void setProducts(Set<ProductQuantity> products) {
    this.products = products;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Instant getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(Instant saleDate) {
    this.saleDate = saleDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
