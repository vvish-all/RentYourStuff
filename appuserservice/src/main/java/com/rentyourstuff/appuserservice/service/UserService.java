package com.rentyourstuff.appuserservice.service;

import com.rentyourstuff.appuserservice.Dto.UserRequestDto;
import com.rentyourstuff.appuserservice.Dto.UserResponseDto;
import com.rentyourstuff.appuserservice.entity.AppUser;
import com.rentyourstuff.appuserservice.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import com.rentyourstuff.appuserservice.util.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    public String authenticateUser(String username, String password) {
//        AppUser user = userRepository.findByUsername(username);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return jwtUtil.generateToken(username);
//        }
//        return null;
//    }

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        AppUser user = userMapper.toUserEntity(userRequestDto);
        AppUser saved = userRepository.save(user);

        return userMapper.toUserResponseDto(saved);
    }

    
    public UserResponseDto findById(UUID Id) {
        Optional<AppUser> userInDB = userRepository.findById(Id);
        return userInDB.map(userMapper::toUserResponseDto).orElse(null);
    }
	public UserResponseDto updateProfile(UserRequestDto userRequestDto) {
        Optional<AppUser> userInDb = userRepository.findByUserName(userRequestDto.getUserName());

        if(userInDb.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        AppUser updatedUser = userMapper.updateAppUserEntityFromDto(userRequestDto, userInDb.get());
        AppUser saved = userRepository.save(updatedUser);
        return userMapper.toUserResponseDto(saved);
    }



//    private AppUser toAppUserEntity(UserRequestDto userRequestDto) {
//        AppUser user = new AppUser();
//        user.setUserName(userRequestDto.getUserName());
//        user.setPassword(userRequestDto.getPassword());
//        user.setEmail(userRequestDto.getEmail());
//        user.setPhoneNumber(userRequestDto.getPhoneNumber());
//        user.setName(userRequestDto.getName());
//
//        return user;
//    }


//    private UserResponseDto toResponseDTO(AppUser appUser) {
//        UserResponseDto userResponseDto = new UserResponseDto();
//
//        userResponseDto.setId(appUser.getId());
//        userResponseDto.setUserName(appUser.getUserName());
//        userResponseDto.setEmail(appUser.getEmail());
//        userResponseDto.setName(appUser.getName());
//        userResponseDto.setPhoneNumber(appUser.getPhoneNumber());
//        userResponseDto.setCreatedAt(appUser.getCreatedAt());
//        userResponseDto.setUpdatedAt(appUser.getUpdatedAt());
//        userResponseDto.setVersion(appUser.getVersion());
//        return userResponseDto;
//    }


//    private AppUser updateUser(AppUser userInDb, UserRequestDto userRequestDto) {
//
//        if(userRequestDto.getUserName() != null && !userRequestDto.getUserName().isBlank()){
//            userInDb.setUserName(userRequestDto.getUserName());
//        }
//        if(userRequestDto.getPassword() != null && !userRequestDto.getPassword().isBlank()){
//            userInDb.setPassword(userRequestDto.getPassword());
//        }
//        if(userRequestDto.getEmail() != null && !userRequestDto.getEmail().isBlank()){
//            userInDb.setEmail(userRequestDto.getEmail());
//        }
//        if(userRequestDto.getName() != null && !userRequestDto.getName().isBlank()){
//            userInDb.setName(userRequestDto.getName());
//        }
//        if(userRequestDto.getPhoneNumber() != null && !userRequestDto.getPhoneNumber().isBlank()){
//            userInDb.setPhoneNumber(userRequestDto.getPhoneNumber());
//        }
//        return userInDb;
//    }


    public UserResponseDto getUserProfile(String username) {
        Optional<AppUser> user = userRepository.findByUserName(username);
        if(user.isEmpty()){
            throw new EntityNotFoundException("Username not found");
        }
        return userMapper.toUserResponseDto(user.get());
    }

}
