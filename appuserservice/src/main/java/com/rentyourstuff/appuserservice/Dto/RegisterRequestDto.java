package com.rentyourstuff.appuserservice.Dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDto {

    @Column(name ="username", unique = true, nullable = false)
    private String userName;
    @Column(name ="password",nullable = false)
    private String password;
    private String email;
    @Column(name ="name",nullable = false)
    private String name;
    private String phoneNumber;
}
