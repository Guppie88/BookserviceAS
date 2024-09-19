package com.marcus.webservice.Repo;

import com.marcus.webservice.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Books, Long> {
}
