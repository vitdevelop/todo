package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.Todo;
import com.vitdevelop.todo_app.core.domain.data.SharedTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findTodoByUserId(Long userId);

    Optional<Todo> findTodoByIdAndUserId(Long todoId, Long userId);

    @Modifying
    @Query(value = "INSERT INTO todo_to_friends (user_id, friend_id, todo_id, editable) VALUES (?,?,?,?)",
            nativeQuery = true)
    int insertSharedTodo(Long userId, Long friendId, Long todoId, boolean editable);

    @Query(value = "SELECT t.id, t.name AS TodoName, t.description AS Description, t.created_on AS CreatedOn, t.updated_on AS UpdatedOn, t.user_id, ttf.editable AS Editable " +
            "FROM todo t JOIN todo_to_friends ttf ON t.id = ttf.todo_id WHERE ttf.user_id = ?",
            nativeQuery = true)
     List<SharedTodo> getTodos(Long userId);
}
