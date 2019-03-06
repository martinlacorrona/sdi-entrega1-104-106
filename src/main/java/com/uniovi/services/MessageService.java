package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> getAllMessages() {
		List<Message> messages = messageRepository.findAll();
		return messages;
	}

	public List<Message> getMessagesFromConversation(Conversation conversation) {
		return messageRepository.findByConversationId(conversation);
	}

	public void addMessage(Message message) {
		messageRepository.save(message);
	}
	
	public void addMessage(Long id) {
		messageRepository.deleteById(id);
	}
}