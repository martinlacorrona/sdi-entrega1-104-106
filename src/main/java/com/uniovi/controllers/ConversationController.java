package com.uniovi.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.User;
import com.uniovi.services.ConversationsService;
import com.uniovi.services.UsersService;

@Controller
public class ConversationController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private ConversationsService conversationService;

	@RequestMapping(value = "/conversation", method = RequestMethod.GET)
	public String getBid(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		List<Conversation> conversations = conversationService.getConversationUser(activeUser);

		model.addAttribute("conversationList", conversations);

		request.getSession().setAttribute("money", activeUser.getMoney());
		request.getSession().setAttribute("email", activeUser.getEmail());

		return "conversation/list";
	}

	@RequestMapping(value = "/conversation", method = RequestMethod.POST)
	public String setBid(HttpServletRequest request, @RequestParam("bid_id") String bid_id,
			@RequestParam("seller_id") String seller_id, @RequestParam("message") String message) {
		if(message.length() == 0) { //Mensaje vacio asi que, actualizame esto
			
		} else { //Nuevo mensaje, metelo dentro
			
		}
		return "redirect:/bid/mybids";
	}
}
