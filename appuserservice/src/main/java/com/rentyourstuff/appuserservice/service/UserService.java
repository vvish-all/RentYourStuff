package com.rentyourstuff.appuserservice.service;

import com.rentyourstuff.appuserservice.entity.AppUser;
import com.rentyourstuff.appuserservice.repository.UserRepository;
import com.rentyourstuff.appuserservice.util.JwtUtil;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public String authenticateUser(String username, String password) {
        AppUser user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return null;
    }

    
    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public AppUser registerUser(AppUser user) {
        // Encrypt password before saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public Optional<AppUser> findById(Long Id) {
        return userRepository.findById(Id);
    }
	public AppUser updateProfile(Long id, AppUser updatedUser) {
		if (userRepository.existsById(id)) {
			updatedUser.setId(id);
            return userRepository.save(updatedUser);
            }
		
		return null;
	}


}
