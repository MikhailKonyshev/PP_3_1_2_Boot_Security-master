package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDAO {

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);
}
