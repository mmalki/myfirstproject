package com.software2ms.myfirstproject;

import com.software2ms.myfirstproject.dto.rest.Book;
import com.software2ms.myfirstproject.dto.rest.Greeting;
import com.software2ms.myfirstproject.dto.rest.Todo;
import com.software2ms.myfirstproject.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/api")
public class TodoController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private TodoService todoService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from   ss Spring Boot!";
    }


    @RequestMapping(path = "/app", method = RequestMethod.GET)
    public @ResponseBody
    Greeting sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(1L, "Mastering Spring 5.2", "Ranga Karanam"), new Book(1L, "Mastering Spring 5.2", "Ranga Karanam"));
    }

    @GetMapping("/todo/")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<>(TodoService.todos, HttpStatus.OK);
    }

    @PostMapping("/todo/")
    public ResponseEntity<?> addTodo(@RequestBody Todo todo, UriComponentsBuilder ucBuilder) {
        if (todoService.isExist(todo)) {
            return new ResponseEntity<>("Error element already exist", HttpStatus.CONFLICT);
        }
        todo = todoService.addTodo(todo.getUser(), todo.getDesc(), todo.getTargetDate(), todo.isDone());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/todo/{username}").buildAndExpand(todo.getUser()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @GetMapping("/todo/{username}")
    public ResponseEntity<?> getTodoById(@PathVariable("username") String username) {
        Todo todo = todoService.retrieveTodos(username);

        if (todo == null) {
            return new ResponseEntity<>("Unable to retrieve. user  with username : " + username + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/todo/{username}")
    public ResponseEntity<?> updateTodo(@PathVariable("username") String user, @RequestBody Todo todo) {
        if (!todoService.isExist(todo)) {
            return new ResponseEntity<>("Unable to update. user with username :" + user + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoService.updateTodo(user, todo), HttpStatus.OK);
    }


    @DeleteMapping("/todo/{username}")
    public ResponseEntity<?> deleteTodo(@PathVariable("username") String user) {
        boolean removed = todoService.deleteTodo(user);
        if (!removed) {
            return new ResponseEntity<>("Unable to delete. user with username :" + user + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
