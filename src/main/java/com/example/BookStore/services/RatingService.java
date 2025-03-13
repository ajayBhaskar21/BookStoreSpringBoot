package com.example.BookStore.services;

import com.example.BookStore.models.Rating;
import com.example.BookStore.models.Book;
import com.example.BookStore.models.User;
import com.example.BookStore.repositories.RatingRepository;
import com.example.BookStore.repositories.BookRepository;
import com.example.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all ratings
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    // Get ratings by bookId
    public List<Rating> getRatingsByBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.map(ratingRepository::findByBook).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // Get ratings by userId
    public List<Rating> getRatingsByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(ratingRepository::findByUser).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Add a rating
    public Rating addRating(Long userId, Long bookId, int ratingValue, String review) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);

        if (user.isEmpty() || book.isEmpty()) {
            throw new RuntimeException("User or Book not found");
        }

        if (ratingValue < 1 || ratingValue > 5) {
            throw new IllegalArgumentException("Rating value must be between 1 and 5");
        }

        Rating rating = new Rating();
        rating.setUser(user.get());
        rating.setBook(book.get());
        rating.setRatingValue(ratingValue);
        rating.setReview(review);

        return ratingRepository.save(rating);
    }
}
