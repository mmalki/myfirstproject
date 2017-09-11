package com.software2ms.myfirstproject.services.impl;

import com.software2ms.myfirstproject.dao.BookRepository;
import com.software2ms.myfirstproject.dto.rest.Book;
import com.software2ms.myfirstproject.services.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findOne(id);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
