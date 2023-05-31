package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Transactional
@Service
public class RoleDAOServiceImp implements RoleDAOService {

    private final RoleDAO roleDAO;

    public RoleDAOServiceImp(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getRoleByName(name);
    }
}
