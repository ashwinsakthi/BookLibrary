package com.book.library;

import com.library.book.model.Book;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class LibraryApplicationTests extends AbstractTest{

/*    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;*/
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void addBook() throws Exception {
        String uri = "/api/v1/books/";
        Book bookObj = new Book();

        bookObj.setName("Book of Eli");
        bookObj.setPublished("21-5-2020");
        bookObj.setRating(5);

        String inputJson = super.mapToJson(bookObj);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

    }

    @Test
    public void getAllBooksCount() throws Exception {
        String uri = "/api/v1/books/all";


        //String inputJson = super.mapToJson(bookObj);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Book[] bookList = super.mapFromJson(content, Book[].class);
        System.out.println(content);
        System.out.println(bookList.length);
        assertTrue(bookList.length!=0);
    }

    @Test
    public void getBookWithId() throws Exception {
        String uri = "/api/v1/books/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Book book = super.mapFromJson(content, Book.class);
        System.out.println(content);
        System.out.println(book.getId());
        assertEquals(1, book.getId().intValue());
    }

    @Test
    public void deleteBookById() throws Exception {
        String uri = "/api/v1/books/2";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        assertEquals(204, status);

        String uriAll = "/api/v1/books/all";

        MvcResult mvcResultAll = mvc.perform(MockMvcRequestBuilders.get(uriAll)).andReturn();
        String content = mvcResultAll.getResponse().getContentAsString();
        Book[] bookList = super.mapFromJson(content, Book[].class);
        assertTrue(Arrays.asList(bookList).stream().
                filter(book -> book.getId() == 2).collect(Collectors.toList()).size() == 0);
    }

}
