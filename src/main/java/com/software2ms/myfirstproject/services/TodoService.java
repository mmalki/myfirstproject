package com.software2ms.myfirstproject.services;

import com.software2ms.myfirstproject.dto.rest.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoService {
    public static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "user1", "Learn Spring MVC", LocalDate.now(),
                false));
        todos.add(new Todo(2, "user2", "Learn Struts", LocalDate.now(), false));
        todos.add(new Todo(3, "user3", "Learn Hibernate", LocalDate.now(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {

        return todos.stream().filter(todo -> todo.getUser().equals(user)).collect(Collectors.toList());
    }

    public Todo addTodo(String name, String desc, LocalDate targetDate,
                        boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
        return todos.get(todos.size()-1);
    }

    public void deleteTodo(int id) {
        todos = todos.stream().filter(todo -> todo.getId() != id).collect(Collectors.toList());
    }

    public boolean isExist(Todo todo){

       return  todos.stream().anyMatch(todo1 -> todo1.equals(todo) );

    }
}
