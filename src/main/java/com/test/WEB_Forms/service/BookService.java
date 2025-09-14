package com.test.WEB_Forms.service;

import com.test.WEB_Forms.model.Book;
import com.test.WEB_Forms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void addBook(String title, String author, int year, String content) {
        bookRepository.insertBook(title, author, year, content);
    }

    public void updateBook(Long id, String title, String author, int year, String content) {
        bookRepository.updateBook(id, title, author, year, content);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }
}
