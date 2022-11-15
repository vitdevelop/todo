package com.vitdevelop.todo_app.web.controller;

import com.vitdevelop.todo_app.core.domain.Todo;
import com.vitdevelop.todo_app.core.service.TodoService;
import com.vitdevelop.todo_app.web.data.TodoData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.ok(todoService.findAllTodo());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findTodoById(todoId));
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoData todoData) {
        return ResponseEntity.ok(todoService.createTodo(todoData.getTodo()));
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId,
                                           @RequestBody TodoData todoData) {
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoData.getTodo()));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(@PathVariable Long todoId) {
        todoService.deleteTodoById(todoId);
        return ResponseEntity.noContent().build();
    }
}
