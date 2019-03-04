
package com.uniovi.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Bid;
import com.uniovi.entities.User;

public interface BidsRepository extends CrudRepository<Bid, Long> {

	List<Bid> findAll();
	
	/**
	 * Busca las ofertas que estan activas, las borradas
	 * no las muestra.
	 * @param pageable
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE r.status = 'ACTIVED' ")
	Page<Bid> findAllActive(Pageable pageable);
	
	/**
	 * Busca todas las bids publicadas por un usuario.
	 * @param user
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Bid> findAllByUser(User user);
	
	/**
	 * Busca todas las bids publicadas por un usuario activas.
	 * @param user
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE r.user = ?1 AND r.status = 'ACTIVED' ORDER BY r.id ASC ")
	List<Bid> findAllByUserActive(User user);
	
	/**
	 * Busca todas las bids publicadas por un usuario.
	 * @param pageable
	 * @param user
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE r.user = ?1 ORDER BY r.id ASC ")
	Page<Bid> findAllByUser(Pageable pageable, User user);
	
	/**
	 * Busca las bids que ha comprado un usuario.
	 * @param pageable
	 * @param user
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE r.buyerUser = ?1 ORDER BY r.id ASC ")
	Page<Bid> findAllBuyedByUser(Pageable pageable, User user);
	
	/**
	 * "Borra" una bid, pero realmente la pone en estado "deleted".
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Bid SET status = 'DELETED' WHERE id = ?1")
	void deleteById(Long id);
	
	/**
	 * Busca una oferta por su titulo.
	 * @param pageable
	 * @param seachtext
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE (LOWER(r.title) LIKE LOWER(?1)) ")
	Page<Bid> searchByTitle(Pageable pageable, String seachtext);
}