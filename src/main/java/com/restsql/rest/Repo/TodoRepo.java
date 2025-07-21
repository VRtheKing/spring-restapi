package com.restsql.rest.Repo;

import com.restsql.rest.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Long> {
}
