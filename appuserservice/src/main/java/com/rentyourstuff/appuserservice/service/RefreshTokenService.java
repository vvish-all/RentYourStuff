package com.rentyourstuff.appuserservice.service;

import com.rentyourstuff.appuserservice.entity.AppUser;
import com.rentyourstuff.appuserservice.entity.RefreshToken;
import com.rentyourstuff.appuserservice.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh-expiration:604800000}") //7 days
    private long refreshExpiration;
    private final RefreshTokenRepository refreshTokenRepo;

    public RefreshToken createRefreshToken(AppUser appUser) {
       //delete existing
        refreshTokenRepo.findByUser(appUser)
                .ifPresent(refreshTokenRepo::delete);

        //make new refresh token
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(appUser)
                .expiresAt(Instant.now().plusMillis(refreshExpiration))
                .revoked(false)
                .build();

        return refreshTokenRepo.save(refreshToken);
    }
    //validate token
    public RefreshToken validateRefreshToken(RefreshToken token){
        refreshTokenRepo.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Session Invalid. Please Login")
                );

        if(token.isExpired() || token.isRevoked()){
            throw new RuntimeException("Session Invalid. Please Login");
        }

        return token;
    }
    //token rotation - inavalidate old, return new token
    public RefreshToken rotateRefreshToken(RefreshToken oldToken){
        oldToken.setRevoked(true);
        refreshTokenRepo.save(oldToken);

        return createRefreshToken(oldToken.getUser());
    }
    //logout - revoke token
    public void revokeByUser(AppUser appUser){
        refreshTokenRepo.findByUser(appUser)
                .ifPresent(token ->{
                            token.setRevoked(true);
                            refreshTokenRepo.save(token);
                        }
                );
    }

}
