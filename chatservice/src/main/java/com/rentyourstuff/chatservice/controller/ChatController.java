package com.rentyourstuff.chatservice.controller;

import com.rentyourstuff.chatservice.dto.ChatRequest;
import com.rentyourstuff.chatservice.entity.Chat;
import com.rentyourstuff.chatservice.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody ChatRequest chatRequest) {
        Chat chat = chatService.createChat(chatRequest);
        return ResponseEntity.ok(chat);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> getChat(@PathVariable Long chatId) {
        return chatService.getChat(chatId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
