package com.restsql.rest.Controller;

import com.example.jooq.tables.pojos.Todos;
import com.restsql.rest.Repo.TodoJooqRepo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TodoController  {

    private final TodoJooqRepo repo;

    public TodoController (TodoJooqRepo repo) {
        System.out.println("Handler is loaded");
        this.repo = repo;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello");
    }

    public Mono<ServerResponse> getTodos(ServerRequest request) {
        List<Todos> todos = repo.findAll();
        return ServerResponse.ok().bodyValue(todos);
    }

    public Mono<ServerResponse> saveTodo(ServerRequest request) {
        return request.bodyToMono(Todos.class)
                .doOnNext(repo::save)
                .then(ServerResponse.ok().bodyValue("Saved todo"));
    }

    public Mono<ServerResponse> updateTodo(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return request.bodyToMono(Todos.class)
                .flatMap(todo -> {
                    if (!repo.existsById(id)) {
                        return ServerResponse.notFound().build();
                    }
                    repo.update(id, todo);
                    return ServerResponse.ok().bodyValue("Updated the record with id: " + id);
                });
    }

    public Mono<ServerResponse> deleteTodo(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        if (!repo.existsById(id)) {
            return ServerResponse.notFound().build();
        }
        repo.deleteById(id);
        return ServerResponse.ok().bodyValue("Deleted record with id: " + id);
    }
}
