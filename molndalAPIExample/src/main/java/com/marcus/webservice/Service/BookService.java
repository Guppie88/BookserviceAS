package com.marcus.webservice.Service;

import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Repo.BooksRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BooksRepo booksRepo;

    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }

    public Optional<Books> getOneBook(long id) {
        return booksRepo.findById(id);
    }

    public Books saveBook(Books book) {
        return booksRepo.save(book);
    }

    public Books patchBook(Books newBook, Long id) {
        Optional<Books> optionalBook = booksRepo.findById(id);
        if (optionalBook.isPresent()) {
            Books book = optionalBook.get();
            book.setTitle(newBook.getTitle());
            book.setISBN(newBook.getISBN());
            book.setAuthor(newBook.getAuthor());
            return booksRepo.save(book);
        }
        return null;  // Hantera fel om boken inte finns
    }

    public void removeBook(long id) {
        booksRepo.deleteById(id);
    }
}
