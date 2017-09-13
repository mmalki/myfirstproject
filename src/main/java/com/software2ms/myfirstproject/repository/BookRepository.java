package com.software2ms.myfirstproject.repository;

import com.software2ms.myfirstproject.dto.rest.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long>{
    Book findByAuthor(String author);
}
