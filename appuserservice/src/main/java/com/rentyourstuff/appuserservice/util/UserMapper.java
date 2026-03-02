package com.rentyourstuff.appuserservice.util;

import com.rentyourstuff.appuserservice.Dto.UserRequestDto;
import com.rentyourstuff.appuserservice.Dto.UserResponseDto;
import com.rentyourstuff.appuserservice.entity.AppUser;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserMapper {

    public AppUser toUserEntity(UserRequestDto dto) {
        return AppUser.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public UserResponseDto toUserResponseDto(AppUser user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .version(user.getVersion())
                .build();
    }

    public UserRequestDto toUserRequestDto(AppUser user) {
        return UserRequestDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public AppUser updateAppUserEntityFromDto(UserRequestDto userRequestDto, AppUser userInDb) {

        if(userRequestDto.getUserName() != null && !userRequestDto.getUserName().isBlank()){
            userInDb.setUserName(userRequestDto.getUserName());
        }
        if(userRequestDto.getPassword() != null && !userRequestDto.getPassword().isBlank()){
            userInDb.setPassword(userRequestDto.getPassword());
        }
        if(userRequestDto.getEmail() != null && !userRequestDto.getEmail().isBlank()){
            userInDb.setEmail(userRequestDto.getEmail());
        }
        if(userRequestDto.getName() != null && !userRequestDto.getName().isBlank()){
            userInDb.setName(userRequestDto.getName());
        }
        if(userRequestDto.getPhoneNumber() != null && !userRequestDto.getPhoneNumber().isBlank()){
            userInDb.setPhoneNumber(userRequestDto.getPhoneNumber());
        }

        return userInDb;
    }
}