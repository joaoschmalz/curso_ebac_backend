package org.example.exercises.m30_project3.domain;

import org.example.exercises.m30_project3.annotations.Column;
import org.example.exercises.m30_project3.annotations.KeyType;
import org.example.exercises.m30_project3.annotations.Table;
import org.example.exercises.m30_project3.dao.Persistent;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Table("SALE")
public class Sale implements Persistent {

  public enum Status {
    STARTED, FINISHED, CANCELED;

    public static Status from(final String value) {
      for (Status status : Status.values()) {
        if (status.name().equals(value)) {
          return status;
        }
      }
      return null;
    }
  }

  @Column(dbName = "id", setJavaName = "setId")
  private Long id;

  @KeyType("getCode")
  @Column(dbName = "code", setJavaName = "setCode")
  private String code;

  @Column(dbName = "customer_id", setJavaName = "setCustomerId")
  private Customer customer;

  private Set<ProductAmount> products = new HashSet<>();

  @Column(dbName = "total_price", setJavaName = "setTotalPrice")
  private BigDecimal totalPrice;

  @Column(dbName = "created_at", setJavaName = "setCreatedAt")
  private Instant createdAt;

  @Column(dbName = "status", setJavaName = "setStatus")
  private Status status;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
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

  public Set<ProductAmount> getProducts() {
    return products;
  }

  public void setProducts(Set<ProductAmount> products) {
    this.products = products;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void addProduct(final Product product, final Integer amount) {
    checkStatus();

    if (!this.products.isEmpty()) {
      final Optional<ProductAmount> productAmount = this.products.stream().filter(p -> p.getProduct().getCode().equals(product.getCode())).findAny();

      if (productAmount.isPresent()) {
        final ProductAmount prod = new ProductAmount();
        prod.setProduct(product);
        prod.add(amount);
        this.products.add(prod);
      }

      this.recalculateTotalPrice();
    } else {
      final ProductAmount prod = new ProductAmount();
      prod.setProduct(product);
      prod.add(amount);
      this.products.add(prod);

      this.recalculateTotalPrice();
    }
  }

  public void removeProduct(final Product product, final Integer amount) {
    checkStatus();
    final Optional<ProductAmount> productAmount =
        this.products.stream().filter(p -> p.getProduct().getCode().equals(product.getCode())).findAny();

    if (productAmount.isPresent()) {
      final ProductAmount prod = productAmount.get();
      prod.remove(amount);
      this.recalculateTotalPrice();
    } else {
      this.products.remove(productAmount.get());
      this.recalculateTotalPrice();
    }

  }

  public void removeAllProducts() {
    checkStatus();
    this.products.clear();
    this.totalPrice = BigDecimal.ZERO;
  }

  public Integer getTotalProductsAmount() {
    return this.products.stream().reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getAmount(), Integer::sum);
  }

  private void checkStatus() {
    if(this.status == Status.FINISHED) {
      throw new UnsupportedOperationException("Can't update a finished sale");
    }
  }

  public void recalculateTotalPrice() {
    BigDecimal totalPrice = BigDecimal.ZERO;

    for(ProductAmount prod : this.products) {
      totalPrice = totalPrice.add(prod.getTotalPrice());
    }

    this.totalPrice = totalPrice;
  }
}
