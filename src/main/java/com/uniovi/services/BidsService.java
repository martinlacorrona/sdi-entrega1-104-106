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
     * 
     * @param user del cual se quieren buscar las ofertas
     * @return La lista de ofertas de un usuario.
     */
    public List<Bid> getBidsForUser(User user) {
	List<Bid> bids = new ArrayList<Bid>();
	bids = bidsRepository.findAllByUserNotDeleted(user);
	return bids;
    }

    /**
     * Devuelve todas las ofertas disponibles.
     * 
     * @return Todas las ofertas
     */
    public List<Bid> getBids() {
	List<Bid> bids = bidsRepository.findAll();
	return bids;
    }

    /**
     * Devuelve todas las ofertas compradas por un usuario.
     * 
     * @param user Usuario sobre el cual se busca
     * @return Ofertas compradas por un usuarios.
     */
    public List<Bid> getBuyedBids(User user) {
	List<Bid> bids = bidsRepository.findAllBuyedByUser(user);
	return bids;
    }

    /**
     * Devuelve todas las ofertas disponibles en modo paginacion
     * 
     * @param pageable el Pageable correspondiente
     * @return la lista de BIDS en una pagina.
     */
    public Page<Bid> getBidsPagination(Pageable pageable) {
	return bidsRepository.findAllActive(pageable);
    }

    /**
     * Devuelve todas las ofertas disponibles en modo paginacion buscando por el
     * titulo.
     * 
     * @param pageable     objeto Pageable de paginacion
     * @param searchText   texto el cual se busca
     * @param userSearched usuario que se busca
     * @return pagina con las ofertas buscada por un titulo de oferta
     */
    public Page<Bid> getBidsPaginationSearchTitle(Pageable pageable, String searchText, User userSearched) {
	searchText = "%" + searchText + "%";
	return bidsRepository.searchByTitle(pageable, searchText, userSearched);
    }

    /**
     * Devuelve una oferta por su id.
     * 
     * @param id id de la oferta que se busca
     * @return la oferta
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
     * 
     * @param bid la oferta que se quiere añadir.
     */
    public void addBid(Bid bid) {
	bidsRepository.save(bid);
    }

    /**
     * Metodo para borrar la oferta. REALMENTE, no la borra, la pone en estado
     * DELETED.
     * 
     * @param id de la oferta que se quiere borrar
     */
    public void deleteBid(Long id) {
	bidsRepository.deleteById(id);
    }

    /**
     * Pone una oferta un id, haciendo que esté comprada.
     * 
     * @param id_user id del usuario
     * @param id_bid  id de la oferta
     */
    public void setUserBuyed(Long id_user, Long id_bid) {
	bidsRepository.buyedById(id_user, id_bid);
	bidsRepository.updateStatusBuyed(id_bid);
    }

    /**
     * Metodo para destacar una oferta.
     */
    public void updateToTrueSpecialBid(Long id) {
	bidsRepository.updateToTrueSepecialBid(id);
    }

    /**
     * Metodo para obtener las ofertas destacadas.
     * 
     * @return
     */
    public List<Bid> getOustandingBid() {
	return bidsRepository.findAllOustanding();
    }
}
