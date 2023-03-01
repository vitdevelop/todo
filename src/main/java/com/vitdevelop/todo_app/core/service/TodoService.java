package com.vitdevelop.todo_app.core.service;

import com.vitdevelop.todo_app.core.domain.Todo;
import com.vitdevelop.todo_app.core.domain.enums.ServiceErrorCode;
import com.vitdevelop.todo_app.core.exception.ServiceException;
import com.vitdevelop.todo_app.core.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
@Validated
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public Todo findTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ServiceException(ServiceErrorCode.TODO_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Todo> findAllTodo() {
        return todoRepository.findAll();
    }

    public Todo createTodo(@Valid Todo newTodo) {
        newTodo.setId(null);
        newTodo.setCreatedOn(null);
        newTodo.setUpdatedOn(null);

        return save(newTodo);
    }

    public Todo updateTodo(Long id, @Valid Todo newTodo) {
        var existingTodo = findTodoById(id);
        existingTodo.setName(newTodo.getName());
        existingTodo.setDescription(newTodo.getDescription());

        return save(existingTodo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.delete(findTodoById(id));
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findTodoByUserId(Long userId) {
        return todoRepository.findTodoByUserId(userId);
    }

    public Todo findTodoByIdAndUserId(Long todoId, Long userId) {
        return todoRepository.findTodoByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new ServiceException(ServiceErrorCode.TODO_NOT_FOUND));
    }
}
