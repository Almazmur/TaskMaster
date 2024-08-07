package org.example.taskmanagement.controller;

import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole("USER");
        return ResponseEntity.ok(userService.save(user));
    }
}