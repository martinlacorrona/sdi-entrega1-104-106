package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Bid;
import com.uniovi.entities.User;
import com.uniovi.repositories.BidsRepository;

@Service
public class BidsService {
	
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private BidsRepository bidsRepository;
	
	/**
	 * Devuelve las ofertas por usuario.
	 * Si eres ADMIN te las devolvera todas.
	 * @param pageable
	 * @param user
	 * @return
	 */
	public List<Bid> getBidsForUser(User user) {
		List<Bid> bids = new ArrayList<Bid>();
		if (user.getRole().equals("ROLE_USER")) {
			bids = bidsRepository.findAllByUserActive(user);
		}
		if (user.getRole().equals("ROLE_ADMIN")) {
			bids = getBids();
		}
		return bids;
	}
	
	/**
	 * Devuelve todas las ofertas disponibles.
	 * @param pageable
	 * @return
	 */
	public List<Bid> getBids() {
		List<Bid> bids = bidsRepository.findAll();
		return bids;
	}
	
	/**
	 * Devuelve todas las ofertas disponibles en modo paginacion
	 * @param pageable
	 * @return
	 */
	public Page<Bid> getBidsPagination(Pageable pageable) {
		return bidsRepository.findAllActive(pageable);
	}
	
	/**
	 * Devuelve todas las ofertas disponibles en modo paginacion buscando por el titulo.
	 * @param pageable
	 * @return
	 */
	public Page<Bid> getBidsPaginationSearchTitle(Pageable pageable, String searchText) {
		searchText = "%" + searchText + "%";
		return bidsRepository.searchByTitle(pageable, searchText);
	}
	
	/**
	 * Devuelve una oferta por su id.
	 * @param id
	 * @return
	 */
	public Bid getBid(Long id) {
		@SuppressWarnings("unchecked")
		Set<Bid> consultedListBid = (Set<Bid>) httpSession.getAttribute("consultedListBid");
		if (consultedListBid == null) {
			consultedListBid = new HashSet<Bid>();
		}
		Bid bidObtained = bidsRepository.findById(id).get();
		consultedListBid.add(bidObtained);
		httpSession.setAttribute("consultedListBid", consultedListBid);
		return bidObtained;
	}
	
	/**
	 * Metodo para añadir una nueva oferta.
	 * @param bid
	 */
	public void addBid(Bid bid) {
		bidsRepository.save(bid);
	}

	/**
	 * Metodo para borrar la oferta.
	 * REALMENTE, no la borra, la pone en estado DELETED.
	 * @param id
	 */
	public void deleteBid(Long id) {
		bidsRepository.deleteById(id);
	}
	
}
