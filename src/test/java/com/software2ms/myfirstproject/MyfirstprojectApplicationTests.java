package com.software2ms.myfirstproject;

import com.software2ms.myfirstproject.dto.rest.Todo;
import com.software2ms.myfirstproject.services.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyfirstprojectApplicationTests {
	@Autowired
	TodoService todoService;

	@Test
	public void contextLoads() {
		//Todo-momo


		System.out.println(TodoService.todos.size());

		List<Todo> filtre = todoService.retrieveTodos("user1");
		filtre.forEach(System.out::println);

		todoService.deleteTodo(2);
		TodoService.todos.forEach(System.out::println);

	}



}
