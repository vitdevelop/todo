package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
