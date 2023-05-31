package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDAOService {
    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);
}
