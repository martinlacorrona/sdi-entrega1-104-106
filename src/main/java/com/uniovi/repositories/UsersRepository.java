package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();

	User findByEmail(String email);
	
	/**
	 * Compra una Bid.
	 */
	@Modifying
	@Transactional
	@Query("UPDATE User SET money = ?1 WHERE (LOWER(email) LIKE LOWER(?2))")
	void updateMoney(Double money, String email);
	
}