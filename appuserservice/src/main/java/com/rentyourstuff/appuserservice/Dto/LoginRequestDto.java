package com.rentyourstuff.appuserservice.Dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
