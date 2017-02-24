package com.example.group;

import com.example.domain.Book;
import com.example.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;


    @Test
    public void shouldBePalindrome() {
        String title = "madam";
        boolean isPalindome = bookService.isPalindrome(title);

        assertTrue(isPalindome);
    }

    @Test
    public void shouldAddBook() {
        Book book = new Book("1", "Harry Potter", "J.K. Rowling");
        List<Book> books = new ArrayList<Book>();
        books.add(book);

        Book expectedBook = bookService.addBook(book);

        assertEquals(book, expectedBook);
    }

    @Test
    public void shouldGetBook() {
        Book book1 = new Book("1", "Harry Potter", "J.K. Rowling");
        Book book2 = new Book("2", "A Song of Ice and Fire", "George R. R. Martin");

        bookService.addBook(book1);
        bookService.addBook(book2);

        Book expectedBook = bookService.getBook(book2.getId(), book2.getTitle());

        assertNotNull(expectedBook);
    }

    @Test
    public void shouldGetAllBooks() {
        List<Book> books = bookService.getLibrary();

        assertTrue(books.isEmpty());
    }

}
