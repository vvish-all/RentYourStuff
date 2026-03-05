package com.rentyourstuff.appuserservice.Dto;

import com.rentyourstuff.appuserservice.entity.RefreshToken;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    @NotBlank
    private RefreshToken refreshToken;
}
