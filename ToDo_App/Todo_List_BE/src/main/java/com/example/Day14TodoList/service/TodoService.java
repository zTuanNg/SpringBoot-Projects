package com.example.Day14TodoList.service;

import com.example.Day14TodoList.exception.BadRequestException;
import com.example.Day14TodoList.exception.NotFoundException;
import com.example.Day14TodoList.entity.Todo;
import com.example.Day14TodoList.repository.TodoRepository;
import com.example.Day14TodoList.request.CreateTodo;
import com.example.Day14TodoList.request.UpdateTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;


    // Lay ds tat ca cong viec
    public List<Todo> getTodos() {
        return repo.findAll();
    }

    // Lay cong viec theo trang thai
    public List<Todo> getTodos(Boolean status){
        return repo.findByStatus(status);
    }

    // Tao cv
    public Todo addTodo(CreateTodo todo){
        if(todo.getTitle().equals("")){
            throw new BadRequestException("Enter title..");
        }
        return repo.save(new Todo(todo.getTitle()));
    }

    // Cap nhat cv
    public Todo updateTodo(Integer id, UpdateTodo request){

        if(request.getTitle().trim().length() == 0){
            throw new BadRequestException("Enter title..");
        }

        Todo todo = repo.findById(id).orElseThrow(() ->{
            throw new NotFoundException("This id does not exist");
        });

        if(todo.getStatus() != request.getStatus()){
            todo.setStatus(request.getStatus());
        }
        if(!todo.getTitle().equals(request.getTitle())){
            todo.setTitle(request.getTitle());
        }

        repo.save(todo);

        return todo;

    }

    // Xoa cv
    public void deleteTodo(Integer id){
        Todo todo = repo.findById(id).orElseThrow(() ->{
            throw new NotFoundException("This id does not exist");
        });

        repo.delete(todo);
    }

}
