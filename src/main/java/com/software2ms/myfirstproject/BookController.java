package com.software2ms.myfirstproject;

import com.software2ms.myfirstproject.dto.rest.Book;
import com.software2ms.myfirstproject.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.invoke.MethodHandles;
import java.util.List;


@RestController
@RequestMapping(value = "/book")
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{author}")
    public ResponseEntity<?> getBook(@PathVariable("author") String author){

        Book book = bookService.getBookByAuthor(author);
        if(book == null){
            return new ResponseEntity<>("Unable get Book, book with author name: " + author +" not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book, UriComponentsBuilder ucBuilder){

        LOGGER.info("Book-------- :"+book);
        Book bookRet =  new Book(book.getId(),book.getName(),book.getAuthor());
        bookRet = bookService.addBook(bookRet);

        if(bookRet == null){

            return new ResponseEntity<>("Error for save book",HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(bookRet.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book){
        LOGGER.info("Book-------- :"+book);

        Book currentBook = bookService.getBookById(id);

        if( currentBook == null){
            return new ResponseEntity<>("Unable to update. book with id : "+ id +" not found",HttpStatus.NOT_FOUND);
        }

        currentBook.setAuthor(book.getAuthor());
        currentBook.setName(book.getName());
        currentBook = bookService.addBook(currentBook);
        return new ResponseEntity<> (currentBook , HttpStatus.OK);
    }
 }
