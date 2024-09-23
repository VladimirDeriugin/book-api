package org.example.bookapi.service;

import org.example.bookapi.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {
    Page<Book> getBooks(String title, String author, Integer year, Double rating, Pageable pageable);
    Optional<Book> getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book rateBook(Long id, Integer rating);
}
