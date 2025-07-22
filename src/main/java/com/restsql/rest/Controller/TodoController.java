package com.restsql.rest.Controller;

import com.example.jooq.tables.pojos.Todos;
import com.restsql.rest.Repo.TodoJooqRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoJooqRepo repo;

    public TodoController(TodoJooqRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @GetMapping
    public List<Todos> getTodos() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<String> saveTodo(@RequestBody Todos todo) {
        repo.save(todo);
        return ResponseEntity.ok("Saved todo");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable int id, @RequestBody Todos todo) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.update(id, todo);
        return ResponseEntity.ok("Updated the record with id: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.ok("Deleted record with id: " + id);
    }
}
