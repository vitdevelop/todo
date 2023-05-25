package com.vitdevelop.todo_app.core.domain.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FriendRequestImpl implements FriendRequest {
    private String username;
    private Date createdOn;
}
