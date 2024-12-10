package com.rentyourstuff.appuserservice.controller;

import com.rentyourstuff.appuserservice.entity.AppUser;
import com.rentyourstuff.appuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public AppUser registerUser(@RequestBody AppUser user) {
        return userService.registerUser(user);
    }

    // Endpoint to login (simple authentication with username)
    @PostMapping("/login")
    public AppUser loginUser(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    // Endpoint to update user profile
    @PutMapping("/update/{id}")
    public AppUser updateUserProfile(@PathVariable Long id, @RequestBody AppUser updatedUser) {
        return userService.updateProfile(id, updatedUser);
    }

    // Endpoint to get user profile
    @GetMapping("/profile/{id}")
    public AppUser getUserProfile(@PathVariable Long id) {
        return userService.findByUsername(id.toString());  // Alternatively, use a specific query to get by ID
    }
}
