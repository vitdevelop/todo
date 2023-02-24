package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
}
