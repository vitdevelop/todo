package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodoByUserId(Long userId);

    Optional<Todo> findTodoByIdAndUserId(Long todoId, Long userId);
}
