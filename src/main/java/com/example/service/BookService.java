package com.example.service;

import com.example.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {

    private List<Book> library = new ArrayList<Book>();

    public boolean isPalindrome(String title) {
        return title.equals(new StringBuilder(title).reverse().toString());
    }

    public Book addBook(Book book) {
        library.add(book);
        return book;
    }

    public Book getBook(String id, String title) {
        Book myBook = null;
        for (Book book : library) {
            if (id.equals(book.getId()) && title.equals(book.getTitle())) {
                myBook = book;
            }
        }
        return myBook;
    }

    public List<Book> getLibrary() {
        return library;
    }
}
