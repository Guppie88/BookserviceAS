package com.marcus.webservice.Repo;

import com.marcus.webservice.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
