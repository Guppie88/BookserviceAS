package com.marcus.webservice;

import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Repo.BooksRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class BooksRepositoryTest {

    @Autowired
    private BooksRepo booksRepo;

    @Test
    public void testFindAllBooks() {
        // Hämta alla böcker från databasen
        List<Books> books = booksRepo.findAll();

        // Kontrollera att listan med böcker inte är tom
        assertFalse(books.isEmpty(), "Books list should not be empty");
    }
}
