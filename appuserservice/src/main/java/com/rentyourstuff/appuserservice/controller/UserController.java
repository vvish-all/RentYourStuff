package com.rentyourstuff.appuserservice.controller;

import com.rentyourstuff.appuserservice.entity.AppUser;
import com.rentyourstuff.appuserservice.service.UserService;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        String token = userService.authenticateUser(username, password);
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Invalid credentials");
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
    
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
