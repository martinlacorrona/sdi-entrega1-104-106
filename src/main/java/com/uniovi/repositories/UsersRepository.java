package com.uniovi.repositories;

import com.uniovi.entities.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1))")
	Page<User> findByName(Pageable pageable,String seachtext);

	User findByDni(String dni);
	
	Page<User> findAll(Pageable pageable);
}