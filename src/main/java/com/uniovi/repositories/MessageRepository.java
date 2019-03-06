package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
	
	List<Message> findAll();
	
	@Query("SELECT m FROM Message m WHERE m.conversation = ?1 ORDER BY m.date ASC ")
	List<Message> findByConversationId(Conversation conversation);
	
}