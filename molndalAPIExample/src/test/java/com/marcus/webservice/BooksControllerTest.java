package com.marcus.webservice;

import com.marcus.webservice.Controller.BooksController;
import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BooksControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        // Mocka data
        Books book1 = new Books();
        book1.setTitle("Book 1");
        book1.setISBN("123-4567890123");

        Books book2 = new Books();
        book2.setTitle("Book 2");
        book2.setISBN("987-6543210987");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        // Testa /books GET endpoint
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].title").value("Book 2"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetOneBook() throws Exception {
        // Mocka data
        Books book = new Books();
        book.setTitle("Book 1");
        book.setISBN("123-4567890123");

        when(bookService.getOneBook(anyLong())).thenReturn(Optional.of(book));

        // Testa /books/{id} GET endpoint
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"));

        verify(bookService, times(1)).getOneBook(1L);
    }

    @Test
    public void testCreateNewBook() throws Exception {
        // Mocka data
        Books newBook = new Books();
        newBook.setTitle("New Book");
        newBook.setISBN("555-4443332221");

        when(bookService.saveBook(any(Books.class))).thenReturn(newBook);

        // Testa /books POST endpoint
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Book\", \"isbn\": \"555-4443332221\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"));

        verify(bookService, times(1)).saveBook(any(Books.class));
    }

    @Test
    public void testUpdateOneBook() throws Exception {
        // Mocka data
        Books updatedBook = new Books();
        updatedBook.setTitle("Updated Book");
        updatedBook.setISBN("555-4443332221");

        when(bookService.patchBook(any(Books.class), anyLong())).thenReturn(updatedBook);

        // Testa /books/{id} PATCH endpoint
        mockMvc.perform(patch("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Book\", \"isbn\": \"555-4443332221\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));

        verify(bookService, times(1)).patchBook(any(Books.class), eq(1L));
    }

    @Test
    public void testDeleteOneBook() throws Exception {
        doNothing().when(bookService).removeBook(anyLong());

        // Testa /books/{id} DELETE endpoint
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book removed successfully!"));

        verify(bookService, times(1)).removeBook(1L);
    }
}
