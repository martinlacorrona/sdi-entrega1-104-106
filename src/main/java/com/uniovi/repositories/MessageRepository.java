package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
	
	List<Message> findAll();
	
}