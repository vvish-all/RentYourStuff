package com.rentyourstuff.appuserservice.Dto;

import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private UUID id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
}
