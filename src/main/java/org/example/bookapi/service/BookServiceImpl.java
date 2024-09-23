package org.example.bookapi.service;

import org.example.bookapi.entity.Book;
import org.example.bookapi.repository.BookRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public Page<Book> getBooks(String title, String author, Integer year, Double rating, Pageable pageable) {
        Specification<Book> spec = (root, query, criteriaBuilder) -> {
            Predicate p = criteriaBuilder.conjunction();
            if (title != null && !title.isEmpty()) {
                p = criteriaBuilder.and(p, criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (author != null && !author.isEmpty()) {
                p = criteriaBuilder.and(p, criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }
            if (year != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("year"), year));
            }
            if (rating != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating));
            }
            return p;
        };
        return bookRepository.findAll(spec, pageable);
    }
    
    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    @Override
    public Book createBook(Book book) {
        if (book.getRating() == null) {
            book.setRating(0.0);
        }
        return bookRepository.save(book);
    }
    
    @Override
    public Book updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setYear(bookDetails.getYear());
            
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }
    
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    public Book rateBook(Long id, Integer rating) {
        return bookRepository.findById(id).map(book -> {
            if (book.getRating() == null) {
                book.setRating(rating.doubleValue());
            } else {
                book.setRating((book.getRating() + rating) / 2);
            }
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }
}
