package com.vitdevelop.todo_app.core.domain.data;

import java.util.Date;

public interface SharedTodo {
    String getDescription();
    String getTodoName();
    Date getCreatedOn();
    Date getUpdatedOn();
    Boolean getEditable();
}
