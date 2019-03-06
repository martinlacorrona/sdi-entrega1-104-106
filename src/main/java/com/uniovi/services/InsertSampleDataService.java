package com.uniovi.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Bid;
import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;

	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	@PostConstruct
	public void init() {
		User user1 = new User("pedro@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("lucas@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("maria@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("marta@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("pelayo@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("edward@gmail.com", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		User user7 = new User("admin@email.com", "Administrador", "Administrador");
		user7.setPassword("admin");
		user7.setRole(rolesService.getRoles()[1]);
		
		Bid b1 = new Bid("Oferta B1", "Oferta de prueba B1", 10.2, user2);
		b1.setBuyerUser(user1);
		Bid c1 = new Bid("Oferta C1", "Oferta de prueba C1", 10.2, user3);
		c1.setBuyerUser(user1);
		Bid d1 = new Bid("Oferta D1", "Oferta de prueba D1", 200.2, user4);
		d1.setBuyerUser(user2);

		
		///
		/// # Conversation producto B1 1
		Conversation conversationB1_1 = new Conversation(user2, user1, b1);
		Message conversationB1_1_message1 = new Message(conversationB1_1, user1, new Date(), "Hola, estoy interesado en el producto.");
		Message conversationB1_1_message2 = new Message(conversationB1_1, user2, new Date(), "Si te gusta compralo.");
		Message conversationB1_1_message3 = new Message(conversationB1_1, user1, new Date(), "Vale, lo hare :D");
		Message conversationB1_1_message4 = new Message(conversationB1_1, user1, new Date(), "Gracias.");
		
		Set messagesProductB1_1 = new HashSet<Message>() {
			{
				add(conversationB1_1_message1);
				add(conversationB1_1_message2);
				add(conversationB1_1_message3);
				add(conversationB1_1_message4);
			}
		};
		conversationB1_1.setMessages(messagesProductB1_1);
		
		/// # Conversation producto B1 2
		Conversation conversationB1_2 = new Conversation(user2, user3, b1);
		Message conversationB1_2_message1 = new Message(conversationB1_2, user3, new Date(), "B1 - 2 Hola, estoy interesado en el producto.");
		Message conversationB1_2_message2 = new Message(conversationB1_2, user2, new Date(), "Si te gusta compralo.");
		Message conversationB1_2_message3 = new Message(conversationB1_2, user3, new Date(), "Vale, lo hare :D");
		Message conversationB1_2_message4 = new Message(conversationB1_2, user3, new Date(), "Gracias.");
		
		Set messagesProductB1_2 = new HashSet<Message>() {
			{
				add(conversationB1_2_message1);
				add(conversationB1_2_message2);
				add(conversationB1_2_message3);
				add(conversationB1_2_message4);
			}
		};
		conversationB1_2.setMessages(messagesProductB1_2);
		
		/// # Conversation producto C1 1
		Conversation conversationC1_1 = new Conversation(user3, user1, c1);
		Message conversationC1_2_message1 = new Message(conversationC1_1, user1, new Date(), "C1-1Hola, estoy interesado en el producto.");
		Message conversationC1_2_message2 = new Message(conversationC1_1, user3, new Date(), "Si te gusta compralo.");
		Message conversationC1_2_message3 = new Message(conversationC1_1, user1, new Date(), "Vale, lo hare :D");
		Message conversationC1_2_message4 = new Message(conversationC1_1, user1, new Date(), "Gracias.");
		
		Set messagesProductC1_1 = new HashSet<Message>() {
			{
				add(conversationC1_2_message1);
				add(conversationC1_2_message2);
				add(conversationC1_2_message3);
				add(conversationC1_2_message4);
			}
		};
		conversationC1_1.setMessages(messagesProductC1_1);
		
		/// #Añadir conversaciones a los usuarios
		/// # Usuario 1 comprador
		Set user1ConversationBuyer = new HashSet<Conversation>() {
			{
				add(conversationB1_1);
				add(conversationC1_1);
			}
		};
		/// # Usuario 2 seller
		Set user2ConversationSeller = new HashSet<Conversation>() {
			{
				add(conversationB1_1);
				add(conversationB1_2);
			}
		};
		/// # Usuario 3 seller
		Set user3ConversationSeller = new HashSet<Conversation>() {
			{
				add(conversationC1_1);
			}
		};

		/// # Usuario 3 seller
		Set user3ConversationBuyer = new HashSet<Conversation>() {
			{
				add(conversationB1_2);
			}
		};
		/// #####
		
		user1.setConversationBuyer(user1ConversationBuyer);
		user2.setConversationSeller(user2ConversationSeller);
		user3.setConversationBuyer(user3ConversationBuyer);
		user3.setConversationSeller(user3ConversationSeller);
		///
		
		
		Set user1Bids = new HashSet<Bid>() {
			{
				add(new Bid("Oferta A1", "Oferta de prueba A1", 10.0, user1));
				add(new Bid("Oferta A2", "Oferta de prueba A2", 15.0, user1));
				add(new Bid("Oferta A3", "Oferta de prueba A3", 20.0, user1));
				add(new Bid("Oferta A4", "Oferta de prueba A4", 30.0, user1));
			}
		};
		user1.setBids(user1Bids);
		Set user2Bids = new HashSet<Bid>() {
			{
				add(b1);
				add(new Bid("Oferta B2", "Oferta de prueba B2", 15.2, user2));
				add(new Bid("Oferta B3", "Oferta de prueba B3", 20.2, user2));
				add(new Bid("Oferta B4", "Oferta de prueba B4", 30.2, user2));
			}
		};
		user2.setBids(user2Bids);
		Set user3Bids = new HashSet<Bid>() {
			{
				add(c1);
				add(new Bid("Oferta C2", "Oferta de prueba C2", 15.2, user3));
				add(new Bid("Oferta C3", "Oferta de prueba C3", 20.2, user3));
				add(new Bid("Oferta C4", "Oferta de prueba C4", 30.2, user3));
			}
		};
		user3.setBids(user3Bids);
		Set user4Bids = new HashSet<Bid>() {
			{
				add(d1);
				add(new Bid("Oferta D2", "Oferta de prueba D2", 20.2, user4));
				add(new Bid("Oferta D3", "Oferta de prueba D3", 54.2, user4));
				
				add(new Bid("Super D1", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D2", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D3", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D4", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D5", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D6", "Super de prueba D3", 54.2, user4));
			}
		};
		user4.setBids(user4Bids);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
	}
}