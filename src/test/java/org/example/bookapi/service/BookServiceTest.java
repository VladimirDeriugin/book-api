package org.example.bookapi.service;

import org.example.bookapi.entity.Book;
import org.example.bookapi.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;
    
    @InjectMocks
    private BookServiceImpl bookService;
    
    private Book book1;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book1 = Book.builder()
                .id(1L)
                .title("Test Book")
                .author("Author")
                .year(2020)
                .rating(4.0)
                .build();
    }
    
    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        
        Optional<Book> found = bookService.getBookById(1L);
        assertTrue(found.isPresent());
        assertEquals("Test Book", found.get().getTitle());
    }
    
    @Test
    void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book1);
        
        Book created = bookService.createBook(book1);
        assertNotNull(created);
        assertEquals("Test Book", created.getTitle());
    }
    
    // Add more unit tests for other service methods
}
