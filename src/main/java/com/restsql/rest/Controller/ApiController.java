package com.restsql.rest.Controller;

import com.restsql.rest.Models.Todo;
import com.restsql.rest.Repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This makes this class Singleton
@Scope(value = "singleton")
public class ApiController {
    @Autowired
    private TodoRepo todoRepo;

    @GetMapping("/") // Route for the Home Page
    public String getPage(){
        return "Hello";
    }

    // Fetch all record
    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoRepo.findAll();
    }

    // Creates a new Todo entry in the database
    @PostMapping("/todos")
    public String saveTodo(@RequestBody Todo todo){
        todoRepo.save(todo);
        return "Saved todo";
    }

    // Updates an existing todo entry
    @PutMapping("/todos/{id}")
    public String updateTodo(@PathVariable long id, @RequestBody Todo todo){
        Todo updatedTodo = todoRepo.findById(id).get();
        updatedTodo.setDescription(todo.getDescription());
        updatedTodo.setIs_done(todo.getIs_done());
        todoRepo.save(updatedTodo);
        return "Updated the Record with id: " + id;
    }

    // Deletes an existing record
    @DeleteMapping("/todos/{id}")
    public String deleteTodo(@PathVariable long id){
        todoRepo.deleteById(id);
        return "Deleted Record with id: " + id;
    }
}