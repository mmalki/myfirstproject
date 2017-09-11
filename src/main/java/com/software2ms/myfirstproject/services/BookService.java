package com.software2ms.myfirstproject.services;

import com.software2ms.myfirstproject.dto.rest.Book;

import java.util.List;


public interface BookService {

    List<Book> getAllBooks();

    Book getBookByAuthor(String author);

    Book  getBookById(Long id);

    Book addBook(Book book);

    Book findById(long id);
}
