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
public class HelloController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private TodoService todoService;
    
    @RequestMapping("/")
    public String index() {
        return "Greetings eeffrom Spring Boot!";
    }



    @RequestMapping(path ="/app" ,method= RequestMethod.GET)
    public @ResponseBody
    Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Arrays.asList(
                new Book(1l, "Mastering Spring 5.2", "Ranga Karanam"));
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return TodoService.todos;
    }
    @PostMapping("/add/")
    public ResponseEntity<?> addTodo(@RequestBody Todo todo, UriComponentsBuilder ucBuilder){
        if(todoService.isExist(todo)){
            return  new ResponseEntity<String>("Error element already exist", HttpStatus.CONFLICT);
        }
        todo = todoService.addTodo(todo.getUser(),todo.getDesc(),todo.getTargetDate(),todo.isDone());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/todo/{id}").buildAndExpand(todo.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }
    
}
