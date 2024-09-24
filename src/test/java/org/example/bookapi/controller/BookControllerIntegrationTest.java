package org.example.bookapi.controller;

import org.example.bookapi.entity.Book;
import org.example.bookapi.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private BookRepository bookRepository;
    
    private Book book1;
    
    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        book1 = Book.builder()
                .title("Integration Test Book")
                .author("Tester")
                .year(2021)
                .rating(4.2)
                .build();
        bookRepository.save(book1);
    }
    
    @Test
    void testGetBookById() {
        ResponseEntity<Book> response = restTemplate.getForEntity("/api/books/" + book1.getId(), Book.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Integration Test Book", response.getBody().getTitle());
    }
    
    @Test
    void testCreateBook() {
        Book newBook = Book.builder()
                .title("New Book")
                .author("New Author")
                .year(2022)
                .rating(4.5)
                .build();
        
        ResponseEntity<Book> response = restTemplate.postForEntity("/api/books", newBook, Book.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Book", response.getBody().getTitle());
    }
}
