package com.rentyourstuff.appuserservice.controller;

import com.rentyourstuff.appuserservice.Dto.LoginRequestDto;
import com.rentyourstuff.appuserservice.Dto.LoginResponseDto;
import com.rentyourstuff.appuserservice.Dto.RefreshTokenRequestDto;
import com.rentyourstuff.appuserservice.Dto.RegisterRequestDto;
import com.rentyourstuff.appuserservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto){
        return ResponseEntity.ok().body(authService.login(requestDto));
    }
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register (@Valid RegisterRequestDto requestDto){
        return ResponseEntity.ok().body(authService.registerUser(requestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh( @Valid @RequestBody RefreshTokenRequestDto request) {
        return ResponseEntity.ok(authService.refresh(request));
    }




}
