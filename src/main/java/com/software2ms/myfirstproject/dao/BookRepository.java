package com.software2ms.myfirstproject.dao;

import com.software2ms.myfirstproject.dto.rest.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book,Long>{
    Book findByAuthor(String author);
}
