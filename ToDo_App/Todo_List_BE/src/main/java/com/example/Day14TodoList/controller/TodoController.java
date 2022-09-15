package com.example.Day14TodoList.controller;

import com.example.Day14TodoList.entity.Todo;
import com.example.Day14TodoList.request.CreateTodo;
import com.example.Day14TodoList.request.UpdateTodo;
import com.example.Day14TodoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService service;


    // Get
    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam Optional<Boolean> status){
        if(status.isPresent()){
            return service.getTodos(status.get());
        }
        return service.getTodos();
    }

    // Add
    @PostMapping("/todos")
    public Todo addTodo( @RequestBody CreateTodo todo){
        return service.addTodo(todo);
    }

    // Delete
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Integer id){
        service.deleteTodo(id);
    }

    // update
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody UpdateTodo todo){
        return service.updateTodo(id, todo);
    }
}
