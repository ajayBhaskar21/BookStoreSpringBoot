package com.example.BookStore.controllers;

import com.example.BookStore.models.Book;
import com.example.BookStore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // get single book by id
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/book/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/books/searchByTitle")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestBody Book book) {
        String keyword = book.getTitle();
        List<Book> books = bookService.searchBooksByTitle(keyword);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/books/getByGenre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestBody Book book) {
        String genre = book.getGenre();
        List<Book> books = bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/getByAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestBody Book book) {
        String author = book.getAuthor();
        List<Book> books = bookService.getBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


}
