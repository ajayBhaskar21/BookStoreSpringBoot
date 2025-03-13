package com.example.BookStore.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column
    private String description;

    @Column(nullable = false)
    private int stockAvailability;

    // One Book can have multiple Reviews
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // One Book can have multiple Ratings
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    // Many Books can be in many Orders
    @ManyToMany(mappedBy = "books")
    private List<Order> orders;

    // No-args constructor (Required by JPA)
    public Book() {}

    // Parameterized constructor
    public Book(String title, String author, String genre, double price, String ISBN, String description, int stockAvailability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.ISBN = ISBN;
        this.description = description;
        this.stockAvailability = stockAvailability;
    }

    // Getters and Setters
    public long getBookId() { return bookId; }
    public void setBookId(long bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStockAvailability() { return stockAvailability; }
    public void setStockAvailability(int stockAvailability) { this.stockAvailability = stockAvailability; }
}
