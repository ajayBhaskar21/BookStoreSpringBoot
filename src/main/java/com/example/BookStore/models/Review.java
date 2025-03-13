package com.example.BookStore.models;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false, length = 1000)
    private String reviewText;

    public Review() {}

    public Review(User user, Book book, String reviewText) {
        this.user = user;
        this.book = book;
        this.reviewText = reviewText;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Book getBook() { return book; }
    public String getReviewText() { return reviewText; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setBook(Book book) { this.book = book; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
}
