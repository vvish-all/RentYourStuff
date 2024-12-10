package com.rentyourstuff.chatservice.repository;

import com.rentyourstuff.chatservice.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
