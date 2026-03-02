package com.rentyourstuff.appuserservice.controller;

import com.rentyourstuff.appuserservice.Dto.UserRequestDto;
import com.rentyourstuff.appuserservice.Dto.UserResponseDto;
import com.rentyourstuff.appuserservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        } catch (Exception e) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody UserRequestDto userRequestDto) {
//        if (token != null) {
//            return ResponseEntity.ok(token);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto userResponseDto = userService.updateProfile(userRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        } catch (Exception e) {
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto userProfile = userService.getUserProfile(userRequestDto.getUserName());
            return ResponseEntity.status(HttpStatus.OK).body(userProfile);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {

        UserResponseDto userResponseDto = userService.findById(id);
        if(userResponseDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


}
