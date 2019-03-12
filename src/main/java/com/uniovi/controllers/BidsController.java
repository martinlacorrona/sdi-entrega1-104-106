package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

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
	public String setBid(@Validated Bid bid, BindingResult result, HttpServletRequest request, Principal principal) {
		addBidFormValidator.validate(bid, result);
		if (result.hasErrors()) {
			return "bid/add";
		}

		// Sacamos el usuario
		User activeUser = usersService.getUserByEmail(principal.getName());

		bid.setUser(activeUser);
		bid.setDate(new Date());
		bid.setStatus(BidStatus.ACTIVED);
		//bid.setSpecialBid(specialBid);
		bidsService.addBid(bid);
		return "redirect:/bid/mybids";
	}

	@RequestMapping(value = "/bid/mybids", method = RequestMethod.GET)
	public String getListBid(Model model, HttpServletRequest request, Principal principal) {
		User activeUser = usersService.getUserByEmail(principal.getName());

		model.addAttribute("bidList", bidsService.getBidsForUser(activeUser));
		return "bid/mybids";
	}

	@RequestMapping(value = "/bid/mybuyedbids", method = RequestMethod.GET)
	public String getBuyedListBid(Model model, HttpServletRequest request, Principal principal) {
		User activeUser = usersService.getUserByEmail(principal.getName());

		model.addAttribute("bidList", bidsService.getBuyedBids(activeUser));
		return "bid/mybuyedbids";
	}

	@RequestMapping("/bid/mybids/delete/{id}")
	public String delete(@PathVariable Long id, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Bid bid = bidsService.getBid(id);
		if (bid.getUser().getId() == activeUser.getId())
			bidsService.deleteBid(id);
		return "redirect:/bid/mybids";
	}

	@RequestMapping(value = "/bid/list", method = RequestMethod.GET)
	public String getAllBids(Model model, Pageable pageable, HttpServletRequest request, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		User activeUser = usersService.getUserByEmail(principal.getName());
		Page<Bid> bids = new PageImpl<Bid>(new LinkedList<Bid>());
		if (searchText == null)
			searchText = "";
		bids = bidsService.getBidsPaginationSearchTitle(pageable, searchText, activeUser);

		model.addAttribute("bidList", bids.getContent());
		model.addAttribute("page", bids);
		model.addAttribute("urlPath", searchText);

		return "bid/list";
	}

	@RequestMapping(value = "/bid/{id}/buyed", method = RequestMethod.GET)
	public String setBuyedTrue(Model model, HttpServletRequest request, Principal principal, @PathVariable Long id,
			String error) {
		// Datos del usuario
		String email = principal.getName();
		User activeUser = usersService.getUserByEmail(email);
		Long idBuyer = activeUser.getId();
		Bid bid = bidsService.getBid(id);
		double precio = bid.getPrice();
		// Si tiene dinero suficiente lo compra
		if (activeUser.isPurschable(precio)) {
			// Actualizamos dinero del comprador
			bidsService.setUserBuyed(idBuyer, id);
			Double finalMoney = activeUser.getMoney() - precio;
			activeUser.setMoney(finalMoney);
			usersService.updateMoney(finalMoney, email);
			request.getSession().setAttribute("money", activeUser.getMoneyFormatted());

			// Actualizamos dinero del vendedor
			User seller = usersService.getUserByEmail(bid.getUser().getEmail());
			Double finalMoneySeller = seller.getMoney() + precio; // Dinero que tendria ahora.
			seller.setMoney(finalMoneySeller);
			usersService.updateMoney(finalMoneySeller, seller.getEmail());

			// Reiniciamos
			request.getSession().setAttribute("error", null);
			request.getSession().setAttribute("id", id);
		} else {
			request.getSession().setAttribute("error", "Error.buy");
			request.getSession().setAttribute("id", id);
		}

		return "redirect:/bid/list";
	}

}
