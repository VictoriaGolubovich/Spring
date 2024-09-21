package ru.gb.hw4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.hw4.model.User;
import ru.gb.hw4.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String listUsers(Model model){
        model.addAttribute("users", service.getAllUsers());
        return "users.html";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model){
        if(service.getUserById(id)==null){
            return "not-found.html";
        }
        model.addAttribute("user", service.getUserById(id));
        return "userProfile.html";
    }
}
