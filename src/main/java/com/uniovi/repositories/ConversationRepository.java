package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {
	
	List<Conversation> findAll();
	
}