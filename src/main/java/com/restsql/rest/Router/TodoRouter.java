package com.restsql.rest.Router;

import com.restsql.rest.Controller.TodoController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TodoRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(TodoController handler) {
        System.out.println("Router is Loaded");
        return route(GET("/todos"), handler::getTodos)
                .andRoute(POST("/todos"), handler::saveTodo)
                .andRoute(PUT("/todos/{id}"), handler::updateTodo)
                .andRoute(DELETE("/todos/{id}"), handler::deleteTodo);
    }
}
