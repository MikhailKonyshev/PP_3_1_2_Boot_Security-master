package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleDAOService;
import ru.kata.spring.boot_security.demo.service.UserDAOServiceImp;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserDAOServiceImp userDAOServiceImp;
    private final RoleDAOService roleDAOService;

    public AdminController(UserDAOServiceImp userDAOServiceImp, RoleDAOService roleDAOService) {
        this.userDAOServiceImp = userDAOServiceImp;
        this.roleDAOService = roleDAOService;
    }

    @GetMapping()
    public String adminPage(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("allUsers", userDAOServiceImp.getAllUsers());
        model.addAttribute("allRoles", roleDAOService.getAllRoles());
        return "adminPage";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        HashSet<Role> roles = new HashSet<>();
        Set<Role> selectRoles = user.getRoles();
        if (selectRoles != null && !selectRoles.isEmpty()) {
            for (Role name : selectRoles) {
                roles.add(roleDAOService.getRoleByName(name.toString()));
            }
        }
        user.setRoles(roles);
        userDAOServiceImp.updateUser(id, user);
        return "redirect:/admin";
    }

    @PostMapping()
    public String creatUser(@ModelAttribute("user") User user) {
        HashSet<Role> roles = new HashSet<>();
        Set<Role> selectRoles = user.getRoles();
        if (selectRoles != null && !selectRoles.isEmpty()) {
            for (Role name : selectRoles) {
                roles.add(roleDAOService.getRoleByName(name.toString()));
            }
        }
        user.setRoles(roles);
        userDAOServiceImp.saveUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDAOServiceImp.deleteUser(id);
        return "redirect:/admin";
    }
}
