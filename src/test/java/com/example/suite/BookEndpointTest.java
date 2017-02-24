package com.example.suite;

import com.example.controller.BookEndpoint;
import com.example.domain.Book;
import com.example.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
                .andReturn();
    }

    @Test
    public void shouldAddBook() throws Exception {
        Book book = new Book("1", "Harry Potter", "J.K. Rowling");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/book")
                .content(json(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void shouldRetrieveBookByIdAndTitle() throws Exception {
        String id = "1";
        String title = "A Song of Ice and Fire";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/book/" + id + "/" + title)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void shouldBePalindrome() throws Exception {
        String title = "madam";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/book/palindrome/" + title)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
