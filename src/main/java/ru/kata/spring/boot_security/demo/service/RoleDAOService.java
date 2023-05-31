package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;


import java.util.List;

public interface RoleDAOService {
    List<Role> getAllRoles();

    public Role findRoleById(Long id);

    Role getRoleByName(String name);


}
