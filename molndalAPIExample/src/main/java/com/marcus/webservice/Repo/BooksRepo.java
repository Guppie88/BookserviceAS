package com.marcus.webservice.Repo;

import com.marcus.webservice.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {

}
