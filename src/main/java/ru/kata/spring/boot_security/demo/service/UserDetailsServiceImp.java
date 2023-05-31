package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserDAO userDAO;

    public UserDetailsServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDAO.getUserByEmail(email));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user.get();
    }
}
