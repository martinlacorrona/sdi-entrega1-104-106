package com.uniovi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		//Sacamos el usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		
		bid.setUser(activeUser);
		bid.setDate(new Date());
		bid.setStatus(BidStatus.ACTIVED);
		
		bidsService.addBid(bid);
		return "bid/add";
	}
}