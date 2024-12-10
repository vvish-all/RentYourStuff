package com.rentyourstuff.chatservice.dto;

import java.util.List;

public class ChatResponse {
    private Long chatId;
    private List<String> messages;
	/**
	 * @return the chatId
	 */
	public Long getChatId() {
		return chatId;
	}
	/**
	 * @param chatId the chatId to set
	 */
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
    

}
