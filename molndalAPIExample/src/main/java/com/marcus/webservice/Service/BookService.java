package com.marcus.webservice.Service;

import com.marcus.webservice.Models.Books;
import com.marcus.webservice.Repo.BooksRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BooksRepo booksRepo;

    public List<Books> getAllBooks() {
        return booksRepo.findAll();
    }

    public Optional<Books> getOneBook(Long id) {
        return Optional.of(booksRepo.findById(id).orElse(new Books()));
    }

    public Books saveBook(Books book) {
        return booksRepo.save(book);
    }

    public Books patchBook(Books book, Long id) {
        Optional<Books> currentBook = booksRepo.findById(id);

        //TODO Check we get an actually book back

        if (!book.getTitle().equals(currentBook.get().getTitle())) currentBook.get().setTitle(book.getTitle());
        if (!book.getISBN().equals(currentBook.get().getISBN())) currentBook.get().setISBN(book.getISBN());

        return booksRepo.save(currentBook.get());
    }

    public void removeBook(Long id) {
        booksRepo.deleteById(id);
    }

    public void removeBook(Books book) {
        booksRepo.deleteById(book.getId());
    }
}
