package com.marcus.webservice.Controller;

import com.marcus.webservice.Models.Author;
import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Service.BookService;
import com.marcus.webservice.Service.AuthorService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Optional<Books>> getOneBook(@PathVariable long id) {
        Optional<Books> book = bookService.getOneBook(id);
        return ResponseEntity.ok(book);
    }

    // Create a new book
    @PostMapping("")
    public ResponseEntity<Books> createNewBook(@RequestBody Books newBook) {
        Author author = newBook.getAuthor();

        // Spara eller skapa en ny författare om det behövs
        if (author != null && author.getId() == 0) {
            author = authorService.saveAuthor(author);
            newBook.setAuthor(author);  // Länka ny författare till boken
        }

        Books savedBook = bookService.saveBook(newBook);
        return ResponseEntity.ok(savedBook);
    }

    // Update an existing book (partial update)
    @PatchMapping("/{id}")
    public ResponseEntity<Books> updateOneBook(@PathVariable Long id,
                                               @RequestBody Books newBook) {
        Books patchedBook = bookService.patchBook(newBook, id);
        return ResponseEntity.ok(patchedBook);
    }

    // Delete a specific book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneBook(@PathVariable long id) {
        bookService.removeBook(id);
        return ResponseEntity.ok("Book removed successfully!");
    }
}
