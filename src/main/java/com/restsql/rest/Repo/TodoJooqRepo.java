package com.restsql.rest.Repo;


import com.example.jooq.tables.pojos.Todos;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import static com.example.jooq.tables.Todos.TODOS;

@Repository
public class TodoJooqRepo {

    private final DSLContext dsl;

    public TodoJooqRepo(DSLContext dsl) {
        this.dsl = dsl;
    }

    // Get all Todo items
    public List<Todos> findAll() {
        return dsl.selectFrom(TODOS)
                .fetchInto(Todos.class);
    }

    // Fetch todo item by ID
    public Optional<Todos> findById(int id) {
        return dsl.selectFrom(TODOS)
                .where(TODOS.ID.eq(id))
                .fetchOptionalInto(Todos.class);
    }

    // Create and Save a new todo
    public void save(Todos todo) {
        dsl.insertInto(TODOS)
                .set(TODOS.DESCRIPTION, todo.getDescription())
                .set(TODOS.IS_DONE, todo.getIsDone())
                .execute();
    }

    // Updates and exisiting todo item
    public void update(int id, Todos todo) {
        dsl.update(TODOS)
                .set(TODOS.DESCRIPTION, todo.getDescription())
                .set(TODOS.IS_DONE, todo.getIsDone())
                .where(TODOS.ID.eq(id))
                .execute();
    }

    // Deletes a todo item by id
    public boolean deleteById(int id) {
        return dsl.deleteFrom(TODOS)
                .where(TODOS.ID.eq(id))
                .execute() > 0;
    }

    public boolean existsById(int id) {
        return dsl.fetchExists(
                dsl.selectOne().from(TODOS).where(TODOS.ID.eq(id))
        );
    }
}
