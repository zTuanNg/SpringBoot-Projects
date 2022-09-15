package com.example.Day14TodoList;

import com.example.Day14TodoList.entity.Todo;
import com.example.Day14TodoList.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AppTests {

	@Autowired
	private TodoRepository repo;

	@Test
	void saveTodo() {
		List<Todo>todos = new ArrayList<>();
		todos.add(new Todo("Di choi", true));
		todos.add(new Todo("Lam bai tap Java", false));
		todos.add(new Todo("Da bong"));
		todos.add(new Todo("Choi game", true));

		repo.saveAll(todos);
	}

}
