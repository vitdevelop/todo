package com.vitdevelop.todo_app.web.controller;

import com.vitdevelop.todo_app.core.domain.Todo;
import com.vitdevelop.todo_app.core.domain.User;
import com.vitdevelop.todo_app.core.domain.data.FriendsRequests;
import com.vitdevelop.todo_app.core.service.UserService;
import com.vitdevelop.todo_app.web.data.TodoData;
import com.vitdevelop.todo_app.web.data.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Patch;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/info")
    public ResponseEntity<User> getUserInfoByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserData userData) {
        return ResponseEntity.ok(userService.createUser(userData.getUser()));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId,
                                           @RequestBody UserData userData) {
        return ResponseEntity.ok(userService.updateUser(userId, userData.getUser()));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/todo")
    public ResponseEntity<List<Todo>> getAllUserTodo(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserTodo(userId));
    }

    @GetMapping("/{userId}/todo/{todoId}")
    public ResponseEntity<Todo> getUserTodoById(@PathVariable Long userId,
                                                @PathVariable Long todoId) {
        return ResponseEntity.ok(userService.findUserTodoById(userId, todoId));
    }

    @PostMapping("/{userId}/todo")
    public ResponseEntity<Todo> createUserTodo(@PathVariable Long userId,
                                               @RequestBody TodoData todoData) {
        return ResponseEntity.ok(userService.createUserTodo(userId, todoData.getTodo()));
    }

    @PutMapping("/{userId}/todo/{todoId}")
    public ResponseEntity<Todo> updateUserTodo(@PathVariable Long userId,
                                               @PathVariable Long todoId,
                                               @RequestBody TodoData todoData) {
        return ResponseEntity.ok(userService.updateUserTodo(userId, todoId, todoData.getTodo()));
    }

    @DeleteMapping("/{userId}/todo/{todoId}")
    public ResponseEntity<Void> deleteUserTodo(@PathVariable Long userId,
                                               @PathVariable Long todoId) {
        userService.deleteUserTodoById(userId, todoId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{userId}/friends")
    public ResponseEntity<FriendsRequests> inviteFriend(@PathVariable Long userId,
                                                        @RequestBody UserData userData){
        return ResponseEntity.ok(userService.inviteUserFriend(userId,userData.getUsername()));
    }
    @PatchMapping("{userId}/friends/{friendId}/decline")
    public ResponseEntity<Void> deleteFriendsRequests(@PathVariable Long userId,
                                                      @PathVariable Long friendId){
        userService.deleteFriendsRequests(userId,friendId);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("{userId}/friends/{friendId}/accept")
    public ResponseEntity<Void> acceptFriendsRequests(@PathVariable Long userId,
                                                      @PathVariable Long friendId){
        userService.acceptFriendsRequest(userId,friendId);
        return ResponseEntity.noContent().build();
    }
}
