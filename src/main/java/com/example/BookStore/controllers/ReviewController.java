package com.example.BookStore.controllers;

import com.example.BookStore.models.Review;
import com.example.BookStore.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // GET all reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // GET reviews for a specific book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBook(@PathVariable Long bookId) {
        List<Review> reviews = reviewService.getReviewsByBook(bookId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // GET reviews written by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // POST a new review
    @PostMapping
    public ResponseEntity<Review> addReview(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam String reviewText) {

        Review savedReview = reviewService.addReview(userId, bookId, reviewText);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }
}
