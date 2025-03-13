package com.example.BookStore.services;

import com.example.BookStore.models.Review;
import com.example.BookStore.models.Book;
import com.example.BookStore.models.User;
import com.example.BookStore.repositories.ReviewRepository;
import com.example.BookStore.repositories.BookRepository;
import com.example.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get reviews by bookId
    public List<Review> getReviewsByBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.map(reviewRepository::findByBook).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // Get reviews by userId
    public List<Review> getReviewsByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(reviewRepository::findByUser).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Add a review
    public Review addReview(Long userId, Long bookId, String reviewText) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);

        if (user.isEmpty() || book.isEmpty()) {
            throw new RuntimeException("User or Book not found");
        }

        Review review = new Review(user.get(), book.get(), reviewText);
        return reviewRepository.save(review);
    }
}
