package com.example.controller;

import com.example.domain.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookEndpoint {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookService.getLibrary();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/{id}/{title}", method = RequestMethod.GET)
    public Book getBooksByIdAndTitle(@PathVariable String id, @PathVariable String title) {
        return bookService.getBook(id, title);
    }

    @RequestMapping(value = "/palindrome/{title}", method = RequestMethod.GET)
    public boolean isPalindrome(@PathVariable String title) {
        return bookService.isPalindrome(title);
    }

}
