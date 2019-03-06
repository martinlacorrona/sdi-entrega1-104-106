package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.repositories.ConversationRepository;

@Service
public class ConversationsService {
	@Autowired
	private ConversationRepository conversationRepository;
	
	public List<Conversation> getConversations() {
		List<Conversation> conversations = conversationRepository.findAll();
		return conversations;
	}

	public Conversation getConversation(Long id) {
		return conversationRepository.findById(id).get();
	}

	public void addConversation(Conversation conversation) {
		conversationRepository.save(conversation);
	}

	public void deleteConversation(Long id) {
		conversationRepository.deleteById(id);
	}
}