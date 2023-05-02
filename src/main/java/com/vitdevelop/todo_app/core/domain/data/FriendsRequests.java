package com.vitdevelop.todo_app.core.domain.data;

import lombok.Data;

import java.util.Date;

public record FriendsRequests(String username, Date createdOn) {
}
