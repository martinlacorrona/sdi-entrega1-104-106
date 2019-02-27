package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1))")
	Page<User> findByName(Pageable pageable,String seachtext);

	User findByDni(String dni);
	
	Page<User> findAll(Pageable pageable);
}