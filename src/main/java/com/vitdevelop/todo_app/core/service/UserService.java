package com.vitdevelop.todo_app.core.service;

import com.vitdevelop.todo_app.core.domain.User;
import com.vitdevelop.todo_app.core.domain.enums.ServiceErrorCode;
import com.vitdevelop.todo_app.core.exception.ServiceException;
import com.vitdevelop.todo_app.core.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException(ServiceErrorCode.USER_NOT_FOUND));
    }

    public User createUser(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())){
            throw new ServiceException(ServiceErrorCode.USER_ALREADY_EXISTS);
        }

        user.setId(null);
        user.setCreatedOn(null);
        user.setUpdatedOn(null);

        return save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        var existingUser = findUserById(userId);
        existingUser.setUsername(user.getUsername());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        return save(existingUser);
    }

    public void deleteUser(Long userId) {
        findUserById(userId);
        userRepository.deleteById(userId);
    }
}
