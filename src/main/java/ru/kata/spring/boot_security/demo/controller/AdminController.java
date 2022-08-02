package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import java.util.Collection;
import java.util.HashSet;

@Controller
public class AdminController {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/admin/users")
    public String showAll(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";

    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/admin/new")
    public String create(@RequestParam(value = "roles") String[] roles,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {
        User user = new User();
        Collection<Role> set = new HashSet<>();
        for (String roleName: roles){
            set.add(roleService.findById(Integer.parseInt(roleName)));
        }
        user.setRoles(set);
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
    @GetMapping("/admin/users/{id}")
    public String searchById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "searchById";
    }
    @PatchMapping("/admin/users/{id}")
    public String update(@RequestParam(value = "roles") String[] roles,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @PathVariable("id") int id) {
        User user = new User();
        Collection<Role> set = new HashSet<>();
        for (String roleName: roles){
            set.add(roleService.findById(Integer.parseInt(roleName)));
        }
        user.setRoles(set);
        user.setUsername(username);
        user.setPassword(password);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
}
