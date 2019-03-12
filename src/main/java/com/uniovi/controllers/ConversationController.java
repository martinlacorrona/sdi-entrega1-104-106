package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Bid;
import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.User;
import com.uniovi.services.BidsService;
import com.uniovi.services.ConversationService;
import com.uniovi.services.MessageService;
import com.uniovi.services.UsersService;

@Controller
public class ConversationController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private BidsService bidService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/conversation", method = RequestMethod.GET)
	public String getConversations(Model model, HttpServletRequest request, Principal principal) {
		User activeUser = usersService.getUserByEmail(principal.getName());

		List<Conversation> conversations = conversationService.getConversationUser(activeUser);

		model.addAttribute("conversationList", conversations);

		return "conversation/list";
	}

	@RequestMapping(value = "/conversation", method = RequestMethod.POST)
	public String getConversationsPost(HttpServletRequest request, Principal principal,
			@RequestParam(value = "bid_id", required = false) String bid_id,
			@RequestParam(value = "conversation_id", required = false) String conversation_id) {
		// Si ya existe la conversacion que vaya directamente a la conversacion
		if (conversation_id != null && conversation_id.length() > 0)
			return "redirect:/conversation/" + conversation_id;
		User activeUser = usersService.getUserByEmail(principal.getName());

		Long conversationID;
		Bid bid = bidService.getBid(Long.parseLong(bid_id));

		// Si el creador de la oferta se quiere mandar un mensaje a si mismo no podra.
		if (bid.getUser().getId() == activeUser.getId())
			return "redirect:/conversation";

		// Buscamos la conversacion por si existe, si no existe crearemos una nueva.
		Conversation c = conversationService.getConversationByBidAndInterested(bid, activeUser);
		if (c == null) { // Como no existe la conversacion creamos una nueva.
			c = new Conversation(activeUser, bidService.getBid(Long.parseLong(bid_id))); // La creamos
			conversationService.addConversation(c); // Y la añadimos
		}
		conversationID = c.getId();

		return "redirect:/conversation/" + conversationID;
	}

	@RequestMapping(value = "/conversation/{id}", method = RequestMethod.GET)
	public String getConversationId(Model model, HttpServletRequest request, Principal principal,
			@PathVariable Long id) {
		Conversation c = conversationService.getConversation(id);
		User activeUser = usersService.getUserByEmail(principal.getName());
		// Chequeo, comprobar si es suya la conversacion, si no sera expulsado a la
		// vista de conversaciones.
		// Comprobamos si es el que envia o el que lo vende.
		if (c.getBid().getUser().getId() != activeUser.getId() && c.getInterestedUser().getId() != activeUser.getId())
			return "redirect:/conversation";

		// Mostrar mensajes actuales
		List<Message> messages = messageService.getMessagesFromConversation(c);

		model.addAttribute("bidName", c.getBid().getTitle());
		model.addAttribute("messageList", messages);
		model.addAttribute("conversationId", c.getId());

		return "conversation/chat";
	}

	@RequestMapping(value = "/conversation/{id}", method = RequestMethod.POST)
	public String getConversationIdPost(Model model, HttpServletRequest request, Principal principal,
			@RequestParam("message") String message, @PathVariable Long id) {
		Conversation c = conversationService.getConversation(id);
		User activeUser = usersService.getUserByEmail(principal.getName());
		// Chequeo, comprobar si es suya la conversacion, si no sera expulsado a la
		// vista de conversaciones.
		// Comprobamos si es el que envia o el que lo vende.
		if (c.getBid().getUser().getId() != activeUser.getId() && c.getInterestedUser().getId() != activeUser.getId())
			return "redirect:/conversation";

		// Añadir el nuevo mensaje
		// Si el mensaje tiene algo se añade, si no no.
		if (message.length() > 0) {
			Message newMessage = new Message(c, activeUser, new Date(), message);
			messageService.addMessage(newMessage);
		}

		// Mostrar los nuevos mensajes
		List<Message> messages = messageService.getMessagesFromConversation(c);

		model.addAttribute("bidName", c.getBid().getTitle());
		model.addAttribute("messageList", messages);
		model.addAttribute("conversationId", c.getId());

		return "conversation/chat";
	}

	@RequestMapping(value = "/conversation/delete", method = RequestMethod.POST)
	public String getConversationIdPost(Model model, HttpServletRequest request, Principal principal,
			@RequestParam("conversation_id") Long conversation_id) {
		Conversation c = conversationService.getConversation(conversation_id);
		if (c == null) // Si no existe no hay nada que borrar
			return "redirect:/conversation/";
		User activeUser = usersService.getUserByEmail(principal.getName());
		// Chequeo, comprobar si es suya la conversacion, si no sera expulsado a la
		// vista de conversaciones.
		// Comprobamos si es el que envia o el que lo vende.
		if (c.getBid().getUser().getId() != activeUser.getId() && c.getInterestedUser().getId() != activeUser.getId())
			return "redirect:/conversation/";

		// Borrar el chat
		conversationService.deleteConversation(conversation_id);

		return "redirect:/conversation";
	}

	@RequestMapping("/conversation/{id}/update")
	public String updateList(Model model, HttpServletRequest request, Principal principal, @PathVariable Long id) {
		Conversation c = conversationService.getConversation(id);
		User activeUser = usersService.getUserByEmail(principal.getName());
		// Chequeo, comprobar si es suya la conversacion, si no sera expulsado a la
		// vista de conversaciones.
		// Comprobamos si es el que envia o el que lo vende.
		if (c.getBid().getUser().getId() != activeUser.getId() && c.getInterestedUser().getId() != activeUser.getId())
			return "redirect:/conversation";

		// Mostrar mensajes actuales
		List<Message> messages = messageService.getMessagesFromConversation(c);

		model.addAttribute("messageList", messages);

		return "conversation/chat :: tableChat";
	}
}
