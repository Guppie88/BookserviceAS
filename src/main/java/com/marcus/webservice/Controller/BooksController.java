package com.marcus.webservice.Controller;

import com.marcus.webservice.Models.Author;
import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Service.BookService;
import com.marcus.webservice.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;

    // Get all books
    @GetMapping("")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Get a specific book by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBook(@PathVariable long id) {
        Optional<Books> book = bookService.getOneBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    // Create a new book
    @PostMapping("")
    public ResponseEntity<?> createNewBook(@RequestBody Books newBook) {
        try {
            Author author = newBook.getAuthor();
            // Spara eller skapa en ny författare om det behövs
            if (author != null && author.getId() == 0) {
                author = authorService.saveAuthor(author);
                newBook.setAuthor(author);  // Länka ny författare till boken
            }

            Books savedBook = bookService.saveBook(newBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            e.printStackTrace();  // Logga fel för bättre felsökning
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating book: " + e.getMessage());
        }
    }

    // Update an existing book (partial update)
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOneBook(@PathVariable Long id, @RequestBody Books newBook) {
        try {
            Books patchedBook = bookService.patchBook(newBook, id);
            return ResponseEntity.ok(patchedBook);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating book: " + e.getMessage());
        }
    }

    // Delete a specific book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneBook(@PathVariable long id) {
        try {
            bookService.removeBook(id);
            return ResponseEntity.ok("Book removed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting book: " + e.getMessage());
        }
    }
}
