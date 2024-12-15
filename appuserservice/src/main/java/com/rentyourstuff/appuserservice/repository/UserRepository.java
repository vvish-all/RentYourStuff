package com.rentyourstuff.appuserservice.repository;

import com.rentyourstuff.appuserservice.entity.AppUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

	Optional<AppUser> findById(Long id);
}
