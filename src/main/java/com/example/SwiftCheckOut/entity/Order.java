package com.example.SwiftCheckOut.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order{  
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;

@Column(nullable = false)
String productId;

@Column(nullable = false)
Integer quantity;

@ManyToOne
@JoinColumn(name = "customer_id",nullable = false)
Customer customer;

@Column(nullable = false)
String status; 


public Order() {
}

public Order(Long id, String productId, Integer quantity, Customer customer, String status) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
    this.customer = customer;
    this.status = status;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getProductId() {
    return productId;
}

public void setProductId(String productId) {
    this.productId = productId;
}

public Integer getQuantity() {
    return quantity;
}

public void setQuantity(Integer quantity) {
    this.quantity = quantity;
}

public Customer getCustomer() {
    return customer;
}

public void setCustomer(Customer customer) {
    this.customer = customer;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}


}
