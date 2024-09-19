package com.marcus.webservice.Controller;

import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Service.BookService;
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

    //Get All Endpoint
    @GetMapping("")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Books>> getOneBook(@PathVariable long id) {
        Optional<Books> book = bookService.getOneBook(id);

        return ResponseEntity.ok(book);
    }

    @PostMapping("")
    public ResponseEntity<Books> createNewBook(@RequestBody Books newBook) {
        Books book = bookService.saveBook(newBook);

        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Books> updateOneBook(@PathVariable Long id,
                                               @RequestBody Books newBook) {
        Books patchedBook = bookService.patchBook(newBook, id);

        return ResponseEntity.ok(patchedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneBook(@PathVariable long id) {
        bookService.removeBook(id);

        return ResponseEntity.ok("Removed Successfully!");
    }

}
