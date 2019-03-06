package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Bid;
import com.uniovi.entities.Conversation;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;

@Service
public class ConversationsService {
	@Autowired
	private ConversationRepository conversationRepository;
	@Autowired
	private BidsService bidsService;
	
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
	
	public List<Conversation> getConversationUser(User user) {
		List<Conversation> conversations = new ArrayList<Conversation>(user.getConversationBuyer());
		
		for(Bid bid : bidsService.getBidsForUser(user))
			for(Conversation conversation : bid.getConversations())
				conversations.add(conversation);
		
		return conversations;
	}
}