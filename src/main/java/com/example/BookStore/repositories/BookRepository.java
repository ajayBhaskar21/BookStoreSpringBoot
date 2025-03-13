package com.example.BookStore.repositories;

import com.example.BookStore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
    // if user enters a title and partially matched then books should be returned
    List<Book> findByTitleContaining(String keyword);
}
