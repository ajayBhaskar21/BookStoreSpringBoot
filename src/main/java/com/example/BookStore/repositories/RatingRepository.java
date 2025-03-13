package com.example.BookStore.repositories;

import com.example.BookStore.models.Rating;
import com.example.BookStore.models.Book;
import com.example.BookStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Get ratings by book
    List<Rating> findByBook(Book book);

    // Get ratings given by a specific user
    List<Rating> findByUser(User user);
}
