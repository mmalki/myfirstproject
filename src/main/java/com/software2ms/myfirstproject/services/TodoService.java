package com.software2ms.myfirstproject.services;

import com.software2ms.myfirstproject.dto.rest.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    public static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "user1", "Learn Spring MVC", LocalDateTime.now(),
                false));
        todos.add(new Todo(2, "user2", "Learn Struts",  LocalDateTime.now(), false));
        todos.add(new Todo(3, "user3", "Learn Hibernate", LocalDateTime.now(),
                false));
    }

    public Todo retrieveTodos(String user) {

        return todos.stream().filter(todo -> todo.getUser().equals(user)).findFirst().orElse(null);
    }

    public Todo addTodo(String name, String desc, LocalDateTime targetDate,
                        boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
        return todos.get(todos.size()-1);
    }

    public Todo updateTodo(String user, Todo todo){
        todos =  todos.stream().map((Todo todo1) -> {
            if(todo1.getUser().equals(user)){
                return todo;
            }else{
                return todo1;
            }
        }).collect(Collectors.toList());
        return todo;
    }

    public boolean deleteTodo(String user) {
       /* todos = todos.stream().filter(todo -> todo.getId() != id).collect(Collectors.toList()); */
        return todos.removeIf(todo -> todo.getUser().equals(user));
    }

    public boolean isExist(Todo todo){

       return  todos.stream().anyMatch(todo1 -> todo1.equals(todo) );

    }
}
