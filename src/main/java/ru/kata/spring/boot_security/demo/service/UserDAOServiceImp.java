package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
@Transactional
public class UserDAOServiceImp implements UserDAOService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    public UserDAOServiceImp(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateUser(Long id, User user) {
        String password = user.getPassword();
        if (password.isEmpty()) {
            String passwordOne = userDAO.getUserById(id).getPassword();
            user.setPassword(passwordOne);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.updateUser(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
