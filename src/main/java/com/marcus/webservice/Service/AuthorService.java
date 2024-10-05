package com.marcus.webservice.Service;

import com.marcus.webservice.Models.Author;
import com.marcus.webservice.Repo.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepo.findById(id);
    }
}
