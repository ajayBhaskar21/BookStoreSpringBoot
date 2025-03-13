package com.example.BookStore.models;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")  // Avoids conflict with SQL keyword
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String orderStatus; // "Pending", "Completed"

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    public Order() {}

    public Order(double totalPrice, String orderStatus) {
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public double getTotalPrice() { return totalPrice; }
    public String getOrderStatus() { return orderStatus; }
    public LocalDateTime getOrderDate() { return orderDate; }

    public void setId(Long id) { this.id = id; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
