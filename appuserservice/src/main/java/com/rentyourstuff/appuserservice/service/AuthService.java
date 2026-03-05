package com.rentyourstuff.appuserservice.service;

import com.rentyourstuff.appuserservice.Dto.LoginRequestDto;
import com.rentyourstuff.appuserservice.Dto.LoginResponseDto;
import com.rentyourstuff.appuserservice.Dto.RefreshTokenRequestDto;
import com.rentyourstuff.appuserservice.Dto.RegisterRequestDto;
import com.rentyourstuff.appuserservice.entity.AppUser;
import com.rentyourstuff.appuserservice.entity.RefreshToken;
import com.rentyourstuff.appuserservice.enums.UserRoles;
import com.rentyourstuff.appuserservice.repository.UserRepository;
import com.rentyourstuff.appuserservice.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.expiration:900000}")         // 15 min
    private long accessExpiration;

    @Value("${jwt.refresh-expiration:604800000}") // 7 days
    private long refreshExpiration;

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public LoginResponseDto login(@Valid LoginRequestDto requestDto) {
        //check username exists
        AppUser user = userRepo.findByUserName(requestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
        //correct password?
        if(!encoder.matches(requestDto.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Crentials");
        }
        //creating access token & refresh token
        String accessToken = jwtUtil.generatetoken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return buildAuthResponse(accessToken, refreshToken.getToken(), user);

    }



    public LoginResponseDto registerUser(@Valid RegisterRequestDto requestDto) {

        if(userRepo.findByEmail(requestDto.getEmail()).isPresent()){
            throw new EntityNotFoundException("Email already exists. Please login");
        }
        if(userRepo.findByUserName(requestDto.getUserName()).isPresent()){
            throw new EntityNotFoundException("Username already exists.");
        }
        AppUser appUser = buildAppUser(requestDto);
        AppUser saved = userRepo.save(appUser);

        String accessToken = jwtUtil.generatetoken(saved);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(saved);

        return buildAuthResponse(accessToken, refreshToken.getToken(), saved);

    }

    private AppUser buildAppUser(@Valid RegisterRequestDto requestDto) {
        return AppUser.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .userName(requestDto.getUserName())
                .phoneNumber(requestDto.getPhoneNumber())
                .name(requestDto.getName())
                .roles(Collections.singletonList(UserRoles.USER))
                .build();
    }

    private LoginResponseDto buildAuthResponse(String accessToken, String refreshToken, AppUser user) {
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .accessTokenExpiresIn(accessExpiration)
                .refreshTokenExpiresIn(refreshExpiration)
                .userId(user.getId())
                .username(user.getUserName())
                .build();
    }

    public LoginResponseDto refresh(@Valid RefreshTokenRequestDto request) {

        RefreshToken oldRefreshToken = refreshTokenService.validateRefreshToken(request.getRefreshToken());

        RefreshToken newRefreshToken = refreshTokenService.rotateRefreshToken(request.getRefreshToken());

        String accessToken = jwtUtil.generatetoken(oldRefreshToken.getUser());

        return buildAuthResponse(accessToken,newRefreshToken.getToken(),oldRefreshToken.getUser());
    }
}
