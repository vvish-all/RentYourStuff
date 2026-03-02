package com.rentyourstuff.appuserservice.Dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private UUID id;
    private String userName;
    private String email;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;    // from BaseEntity
    private LocalDateTime updatedAt;    // from BaseEntity
    private Long version;
}
