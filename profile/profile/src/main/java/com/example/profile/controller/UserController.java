package com.example.profile.controller;

import com.example.profile.model.User;
import com.example.profile.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin // Cho phép gọi API từ frontend (HTML/JS)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser() {
        return userService.getUser();
    }

    @PostMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
}
