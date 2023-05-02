package com.vitdevelop.todo_app.core.repository;

import com.vitdevelop.todo_app.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
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
    @Query(value = "DELETE FROM user_friends WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)",
                    nativeQuery = true)
    int deleteFriends(Long userId, Long friendId);


    @Query(value = "SELECT u.id, u.username, u.first_name, u.last_name, u.created_on, u.updated_on " +
            "FROM users u JOIN user_friends uf ON u.id = uf.friend_id WHERE uf.user_id = ?",
                    nativeQuery = true)
    List<User> getFriends(Long userId);

    @Modifying
    @Query(value = "INSERT INTO friends_requests (user_id, friend_id) VALUES (?, ?)", nativeQuery = true)
    void insertUserFriend(Long userId, Long friendId);

    @Query(value = "SELECT fr.created_on FROM friends_requests fr WHERE fr.user_id = ? AND fr.friend_id=?", nativeQuery = true)
    Optional<Date> findFriendsRequestCreatedOn(Long userId, Long friendId);
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