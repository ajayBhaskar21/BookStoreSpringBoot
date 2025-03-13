package com.example.BookStore.models;

import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int ratingValue; // Should be between 1-5

    @Column(length = 500)
    private String review; // Optional review text

    public Rating() {}

    public Rating(int ratingValue, String review) {
        this.ratingValue = ratingValue;
        this.review = review;
    }

    public Long getId() { return id; }
    public int getRatingValue() { return ratingValue; }
    public String getReview() { return review; }
    public User getUser() { return user; }
    public Book getBook() { return book; }

    public void setId(Long id) { this.id = id; }
    public void setRatingValue(int ratingValue) { this.ratingValue = ratingValue; }
    public void setReview(String review) { this.review = review; }
    public void setUser(User user) { this.user = user; }
    public void setBook(Book book) { this.book = book; }
}
