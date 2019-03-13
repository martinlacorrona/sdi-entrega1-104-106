
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
	 * @param pageable atributo pageable
	 * @return pagina con lista de bids
	 */
	@Query("SELECT r FROM Bid r WHERE r.status = 'ACTIVED' ")
	Page<Bid> findAllActive(Pageable pageable);
	
	/**
	 * Busca todas las bids publicadas por un usuario.
	 * @param user del cual se quieren sacar las ofertas
	 * @return lista de bids de un usuario
	 */
	@Query("SELECT r FROM Bid r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Bid> findAllByUser(User user);
	
	/**
	 * Busca todas las bids publicadas por un usuario activas.
	 * @param user sobre el que se busca
	 * @return todas las ofertas no borradas del usuario
	 */
	@Query("SELECT r FROM Bid r WHERE r.user = ?1 AND r.status != 'DELETED' ORDER BY r.price ASC ")
	List<Bid> findAllByUserNotDeleted(User user);
	
	/**
	 * Busca todas las bids publicadas por un usuario.
	 * @param pageable atributo pageable
	 * @param user sobre el que se busca
	 * @return pagina de bids
	 */
	@Query("SELECT r FROM Bid r WHERE r.user = ?1 ORDER BY r.id ASC ")
	Page<Bid> findAllByUser(Pageable pageable, User user);
	
	/**
	 * Busca todas las bids publicadas por un usuario destacadas.
	 * @param pageable
	 * @param user
	 * @return
	 */
	@Query("SELECT r FROM Bid r WHERE r.specialBid = true and r.status = 'ACTIVED' ORDER BY r.id ASC ")
	List<Bid> findAllOustanding();
	
	
	/**
	 * Busca las bids que ha comprado un usuario.
	 * @param user usario sobre el que se hace
	 * @return lista de bids compradas por el usuario
	 */
	@Query("SELECT r FROM Bid r WHERE r.buyerUser = ?1 ORDER BY r.id ASC ")
	List<Bid> findAllBuyedByUser(User user);
	
	/**
	 * "Borra" una bid, pero realmente la pone en estado "deleted".
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Bid SET status = 'DELETED' WHERE id = ?1")
	void deleteById(Long id);
	
	/**
	 * Busca una oferta por su titulo.
	 * @param pageable atributo pageable
	 * @param seachtext texto el cual se busca
	 * @param userSearched el usuario que busca
	 * @return pagina de bids
	 */
	@Query("SELECT r FROM Bid r WHERE (LOWER(r.title) LIKE LOWER(?1)) AND r.user != ?2 ")
	Page<Bid> searchByTitle(Pageable pageable, String seachtext, User userSearched);
	
	/**
	 * Compra una bid.
	 * @param id_user usuario que la compra
	 * @param id_bid bid que es comprada
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Bid SET buyer_user_id = ?1 WHERE id = ?2 ")
	void buyedById(Long id_user, Long id_bid);
	
	@Modifying
	@Transactional
	@Query("UPDATE Bid SET status = 'BUYED' WHERE id = ?1 ")
	void updateStatusBuyed(Long id_bid);
	
	/**
	 * Destaca una BID poniendo el specialBid a true
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Bid SET specialBid = true WHERE id = ?1 ")
	void updateToTrueSepecialBid(Long id_bid);
}