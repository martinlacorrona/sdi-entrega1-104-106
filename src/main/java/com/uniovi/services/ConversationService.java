package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Bid;
import com.uniovi.entities.Conversation;
import com.uniovi.entities.User;
import com.uniovi.repositories.BidsRepository;
import com.uniovi.repositories.ConversationRepository;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private BidsRepository bidRepository;

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
	// Todas las conversaciones en las que participa como comprador.
	List<Conversation> conversations = conversationRepository.findConversationsByBuyer(user);

	// Todas las conversaciones en las que participa como vendedor.
	// Y que no se repitan
	for (Bid bid : bidRepository.findAllByUser(user))
	    for (Conversation conversation : bid.getConversations())
		if (!conversations.contains(conversation))
		    conversations.add(conversation);

	return conversations;
    }

    public Conversation getConversationByBidAndInterested(Bid bid, User interestedUser) {
	return conversationRepository.findConversationByBidAndUser(bid, interestedUser);
    }
}