package com.vitdevelop.todo_app.core.domain.data;

import lombok.Data;

import java.util.Date;

public interface FriendRequest {
    String getUsername();
    Date getCreatedOn();
}
