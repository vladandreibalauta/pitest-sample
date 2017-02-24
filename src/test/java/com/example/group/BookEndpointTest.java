package com.example.group;

import com.example.controller.BookEndpoint;
import com.example.domain.Book;
import com.example.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BookEndpoint.class)
public class BookEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

    @Test
    public void shouldGetListOfBooks() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(0, mvcResult.getResponse().getContentLength());
    }

    @Test
    public void shouldAddBook() throws Exception {
        Book book = new Book("1", "Harry Potter", "J.K. Rowling");

        when(bookService.addBook(Matchers.any())).thenReturn(book);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/book")
                .content(json(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(json(book), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        Book book1 = new Book("1", "Harry Potter", "J.K. Rowling");
        Book book2 = new Book("2", "A Song of Ice and Fire", "George R. R. Martin");
        List<Book> library = new ArrayList<Book>();
        library.add(book1);
        library.add(book2);

        when(bookService.getLibrary()).thenReturn(library);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
        assertEquals(json(library), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldRetrieveBookByIdAndTitle() throws Exception {
        Book book = new Book("1", "A Song of Ice and Fire", "George R. R. Martin");

        when(bookService.getBook(book.getId(), book.getTitle())).thenReturn(book);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/book/" + book.getId() + "/" + book.getTitle())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
        assertEquals(json(book), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldBePalindrome() throws Exception {
        String title = "madam";

        when(bookService.isPalindrome(title)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/book/palindrome/" + title)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(Boolean.parseBoolean(mvcResult.getResponse().getContentAsString()));
        assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
