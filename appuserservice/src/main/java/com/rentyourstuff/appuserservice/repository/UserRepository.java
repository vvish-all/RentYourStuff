package com.rentyourstuff.appuserservice.repository;

import com.rentyourstuff.appuserservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
