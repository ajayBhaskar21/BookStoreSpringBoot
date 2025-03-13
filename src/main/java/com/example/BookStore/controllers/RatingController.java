package com.example.BookStore.controllers;

import com.example.BookStore.models.Rating;
import com.example.BookStore.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // GET all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // GET ratings for a specific book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Rating>> getRatingsByBook(@PathVariable Long bookId) {
        List<Rating> ratings = ratingService.getRatingsByBook(bookId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // GET ratings given by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable Long userId) {
        List<Rating> ratings = ratingService.getRatingsByUser(userId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    // POST a new rating
    @PostMapping
    public ResponseEntity<Rating> addRating(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam int ratingValue,
            @RequestParam(required = false) String review) {

        Rating savedRating = ratingService.addRating(userId, bookId, ratingValue, review);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }
}
