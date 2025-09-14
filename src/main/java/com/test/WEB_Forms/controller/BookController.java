package com.test.WEB_Forms.controller;

import com.test.WEB_Forms.model.Book;
import com.test.WEB_Forms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book-list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        if (id == 0) {
            model.addAttribute("book", new Book());
        } else {
            model.addAttribute("book", bookService.getBookById(id));
        }
        return "book-edit";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam(required = false) Long id, @RequestParam String title,
                           @RequestParam String author, @RequestParam int year,
                           @RequestParam String content) {
        if (id != null && id > 0) {
            bookService.updateBook(id, title, author, year, content);
        } else {
            bookService.addBook(title, author, year, content);
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
