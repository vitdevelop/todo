package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);

    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO user_friends (user_id, friend_id) VALUES (?, ?),(?, ?);",
            nativeQuery = true)
    int insertFriends(Long userId, Long friendId);

    @Modifying
    @Query(value = "DELETE FROM user_friends WHERE (user_id = :userId AND friend_id = :friendId) OR (user_id = :friendId AND friend_id = :userId)",
                    nativeQuery = true)
    void deleteFriends(@Param("userId")Long userId,@Param("friendId") Long friendId);


    @Query(value = "SELECT u.id, u.username, u.first_name, u.last_name, u.created_on, u.updated_on " +
            "FROM users u JOIN user_friends uf ON u.id = uf.friend_id WHERE uf.user_id = ?",
                    nativeQuery = true)
    List<User> getFriends(Long userId);
}