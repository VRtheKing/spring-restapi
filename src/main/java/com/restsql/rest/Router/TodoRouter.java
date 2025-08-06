package com.restsql.rest.Router;

import com.restsql.rest.Controller.TodoController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@Tag(name = "Todo API", description = "API for managing Todos")
public class TodoRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/todos",
                    method = RequestMethod.GET,
                    beanClass = TodoController.class,
                    beanMethod = "getTodos",
                    operation = @Operation(
                            operationId = "getTodos",
                            summary = "Get all todos",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "List of todos")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/todos",
                    method = RequestMethod.POST,
                    beanClass = TodoController.class,
                    beanMethod = "saveTodo",
                    operation = @Operation(
                            operationId = "saveTodo",
                            summary = "Create a new todo",
                            responses = {
                                    @ApiResponse(responseCode = "201", description = "Todo created")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/todos/{id}",
                    method = RequestMethod.PUT,
                    beanClass = TodoController.class,
                    beanMethod = "updateTodo",
                    operation = @Operation(
                            operationId = "updateTodo",
                            summary = "Update an existing todo",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Todo updated")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/todos/{id}",
                    method = RequestMethod.DELETE,
                    beanClass = TodoController.class,
                    beanMethod = "deleteTodo",
                    operation = @Operation(
                            operationId = "deleteTodo",
                            summary = "Delete a todo",
                            responses = {
                                    @ApiResponse(responseCode = "204", description = "Todo deleted")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routes(TodoController handler) {
        System.out.println("Router is Loaded");
        return route(GET("/todos"), handler::getTodos)
                .andRoute(POST("/todos"), handler::saveTodo)
                .andRoute(PUT("/todos/{id}"), handler::updateTodo)
                .andRoute(DELETE("/todos/{id}"), handler::deleteTodo);
    }
}
