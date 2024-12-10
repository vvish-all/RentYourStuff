package com.rentyourstuff.chatservice.repository;

import com.rentyourstuff.chatservice.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
