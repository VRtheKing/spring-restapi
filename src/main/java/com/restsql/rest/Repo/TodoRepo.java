package com.restsql.rest.Repo;

import com.restsql.rest.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //This makes the class Singleton
public interface TodoRepo extends JpaRepository<Todo, Long> {
}
