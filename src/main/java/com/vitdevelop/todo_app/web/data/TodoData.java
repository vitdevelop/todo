package com.vitdevelop.todo_app.web.data;

import com.vitdevelop.todo_app.core.domain.Todo;
import lombok.Data;

@Data
public class TodoData {
    private String name;
    private String description;

    public Todo getTodo() {
        var todo = new Todo();
        todo.setName(this.getName());
        todo.setDescription(this.getDescription());

        return todo;
    }
}
