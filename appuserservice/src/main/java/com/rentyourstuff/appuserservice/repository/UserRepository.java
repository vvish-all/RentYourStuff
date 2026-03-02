package com.rentyourstuff.appuserservice.repository;

import com.rentyourstuff.appuserservice.entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
    AppUser findByUserName(String userName);
}
