package com.vitdevelop.todo_app.web.data;

import com.vitdevelop.todo_app.core.domain.User;
import lombok.Data;

@Data
public class UserData {
    private String username;
    private String firstName;
    private String lastName;

    public User getUser() {
        var user = new User();
        user.setUsername(this.username);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);

        return user;
    }
}
