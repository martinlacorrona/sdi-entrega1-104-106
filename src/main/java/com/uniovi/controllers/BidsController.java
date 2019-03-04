package com.uniovi.controllers;

import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Bid;
import com.uniovi.entities.BidStatus;
import com.uniovi.entities.User;
import com.uniovi.services.BidsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddBidFormValidator;

@Controller
public class BidsController {

	@Autowired
	private BidsService bidsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddBidFormValidator addBidFormValidator;

	@RequestMapping(value = "/bid/add", method = RequestMethod.GET)
	public String getBid(Model model) {
		model.addAttribute("bid", new Bid());
		return "bid/add";
	}

	@RequestMapping(value = "/bid/add", method = RequestMethod.POST)
	public String setBid(@Validated Bid bid, BindingResult result) {
		addBidFormValidator.validate(bid, result);
		if (result.hasErrors()) {
			return "bid/add";
		}

		// Sacamos el usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		bid.setUser(activeUser);
		bid.setDate(new Date());
		bid.setStatus(BidStatus.ACTIVED);

		bidsService.addBid(bid);
		return "redirect:/bid/mybids";
	}

	@RequestMapping(value = "/bid/mybids", method = RequestMethod.GET)
	public String getListBid(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		model.addAttribute("bidList", bidsService.getBidsForUser(activeUser));
		return "bid/mybids";
	}

	@RequestMapping(value = "/bid/mybuyedbids", method = RequestMethod.GET)
	public String getBuyedListBid(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		model.addAttribute("bidList", bidsService.getBuyedBids(activeUser));
		return "bid/mybuyedbids";
	}

	@RequestMapping("/bid/mybids/delete/{id}")
	public String delete(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Bid bid = bidsService.getBid(id);
		if (bid.getUser().getId() == activeUser.getId())
			bidsService.deleteBid(id);
		return "redirect:/bid/mybids";
	}

	@RequestMapping(value = "/bid/list", method = RequestMethod.GET)
	public String getAllBids(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText) {
		Page<Bid> bids = new PageImpl<Bid>(new LinkedList<Bid>());
		if(searchText == null) //Si no se busca texto
			bids = bidsService.getBidsPagination(pageable);
		else //Si se busca texto
			bids = bidsService.getBidsPaginationSearchTitle(pageable, searchText);
		
		model.addAttribute("bidList", bids.getContent());
		model.addAttribute("page", bids);
		if(searchText == null) //Para que no falle la paginacion con busqueda
			searchText = "";
		model.addAttribute("urlPath", searchText);
		return "bid/list";
	}
}