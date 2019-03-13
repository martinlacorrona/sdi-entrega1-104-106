package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Bid;
import com.uniovi.services.BidsService;

@Controller
public class HomeController {
	
	@Autowired
	BidsService bidsService;

	@RequestMapping("/")
	public String index(Model model,HttpServletRequest request, Principal principal) {
		List<Bid> bids = bidsService.getOustandingBid();
		model.addAttribute("bidList", bids);

		request.getSession().setAttribute("url", "home");
		return "index";
	}

	@RequestMapping("/forbidden")
	public String forbidden() {
		return "forbidden";
	}
}