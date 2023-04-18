package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);

    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query(value = "INSERT INTO friends_requests (user_id, friend_id) VALUES (?, ?)", nativeQuery = true)
    int insertUserFriend(Long userId, Long friendId);

    @Modifying
    @Query(value = "DELETE FROM friends_requests WHERE user_id=? and friend_id=?", nativeQuery = true)
    int deleteUserFriend(Long userId, Long friendId);

    @Query(value="Select u.id, u.username, u.first_name, u.last_name, u.created_on, u.updated_on " +
            "FROM users u JOIN friends_requests fr ON u.id = fr.user_id WHERE fr.friend_id=?",nativeQuery = true)
    List<User> selectProposalUsers(Long userId);


    @Query(value="Select u.id, u.username, u.first_name, u.last_name, u.created_on, u.updated_on " +
            "FROM users u JOIN friends_requests fr ON u.id = fr.friend_id WHERE fr.user_id=?",nativeQuery = true)
    List<User> selectInvitedUsers(Long userId);
}
