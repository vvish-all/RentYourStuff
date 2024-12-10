package com.rentyourstuff.chatservice.service;

import com.rentyourstuff.chatservice.dto.ChatRequest;
import com.rentyourstuff.chatservice.entity.Chat;
import com.rentyourstuff.chatservice.entity.Message;
import com.rentyourstuff.chatservice.repository.ChatRepository;
import com.rentyourstuff.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Chat createChat(ChatRequest chatRequest) {
        Chat chat = new Chat();
        chat.setUserId(chatRequest.getUserId());
        chat.setProductId(chatRequest.getProductId());
        chat.setOwnerId(0L); // Fetch owner ID using product-service later.

        Message initialMessage = new Message();
        initialMessage.setContent(chatRequest.getMessage());
        initialMessage.setSenderId(chatRequest.getUserId());
        initialMessage.setTimestamp(LocalDateTime.now());
        initialMessage.setChat(chat);

        chat.getMessages().add(initialMessage);

        return chatRepository.save(chat);
    }

    public Optional<Chat> getChat(Long chatId) {
        return chatRepository.findById(chatId);
    }
}
