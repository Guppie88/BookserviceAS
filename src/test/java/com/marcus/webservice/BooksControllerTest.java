package com.marcus.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcus.webservice.Controller.BooksController;
import com.marcus.webservice.Models.Author;
import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper för JSON-hantering

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
        objectMapper = new ObjectMapper(); // Initiera ObjectMapper
    }

    @Test
    public void testGetAllBooks() throws Exception {
        // Mocka data
        Author author = new Author(1L, "Author 1", 45, null);
        Books book1 = new Books(1L, "Book 1", "123-4567890123", author);
        Books book2 = new Books(2L, "Book 2", "987-6543210987", author);

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        // Testa /books GET endpoint
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].title").value("Book 2"))
                .andExpect(jsonPath("$[0].author.name").value("Author 1"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetOneBook() throws Exception {
        // Mocka data
        Author author = new Author(1L, "Author 1", 45, null);
        Books book = new Books(1L, "Book 1", "123-4567890123", author);

        when(bookService.getOneBook(anyLong())).thenReturn(Optional.of(book));

        // Testa /books/{id} GET endpoint
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.author.name").value("Author 1"));

        verify(bookService, times(1)).getOneBook(1L);
    }

    @Test
    public void testCreateNewBook() throws Exception {
        // Mocka data
        Author author = new Author(1L, "New Author", 45, null);
        Books newBook = new Books(1L, "New Book", "555-4443332221", author);

        when(bookService.saveBook(any(Books.class))).thenReturn(newBook);

        // Konvertera objekt till JSON-sträng
        String jsonContent = objectMapper.writeValueAsString(newBook);

        // Testa /books POST endpoint
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author.name").value("New Author"));

        verify(bookService, times(1)).saveBook(any(Books.class));
    }

    @Test
    public void testUpdateOneBook() throws Exception {
        // Mocka data
        Author author = new Author(1L, "Updated Author", 50, null);
        Books updatedBook = new Books(1L, "Updated Book", "555-4443332221", author);

        when(bookService.patchBook(any(Books.class), anyLong())).thenReturn(updatedBook);

        // Konvertera objekt till JSON-sträng
        String jsonContent = objectMapper.writeValueAsString(updatedBook);

        // Testa /books/{id} PATCH endpoint
        mockMvc.perform(patch("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author.name").value("Updated Author"));

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
