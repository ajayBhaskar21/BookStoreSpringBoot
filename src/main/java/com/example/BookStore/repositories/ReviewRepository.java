package com.example.BookStore.repositories;

import com.example.BookStore.models.Review;
import com.example.BookStore.models.Book;
import com.example.BookStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Get reviews by book
    List<Review> findByBook(Book book);

    // Get reviews written by a specific user
    List<Review> findByUser(User user);
}
