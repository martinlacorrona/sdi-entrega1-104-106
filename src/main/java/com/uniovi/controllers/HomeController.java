package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Bid> bids = bidsService.getOustandingBid();
		model.addAttribute("bidList", bids);

		if(auth.isAuthenticated()) {
			if(request.getSession().getAttribute("error") != null) {
				model.addAttribute("error", "true");
				request.getSession().setAttribute("error", null); 
			}
			
			if(request.getSession().getAttribute("buyed") != null) {
				model.addAttribute("buyed", "true");
				request.getSession().setAttribute("buyed", null); 
			}
		}
		
		request.getSession().setAttribute("url", "home");
		return "index";
	}

	@RequestMapping("/forbidden")
	public String forbidden() {
		return "forbidden";
	}
}