<<<<<<< HEAD
package com.uniovi.services;

import java.util.GregorianCalendar;
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
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private BidsService bidService;

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
	User user6 = new User("admin@email.com", "Administrador", "Administrador");
	user6.setPassword("admin");
	user6.setRole(rolesService.getRoles()[1]);

	Bid a1 = new Bid("Oferta A1", "Oferta de prueba A1", 10.0, user1);
	Bid a2 = new Bid("Oferta A2", "Oferta de prueba A2", 15.0, user1);
	Bid a3 = new Bid("Oferta A3", "Oferta de prueba A3", 20.0, user1);
	Bid a4 = new Bid("Oferta A4", "Oferta de prueba A4", 30.0, user1);
	Bid a5 = new Bid("Oferta A5", "Oferta de prueba A5", 32.0, user1);
	Bid a6 = new Bid("Oferta A6", "Oferta de prueba A6", 10.0, user1);
	Bid a7 = new Bid("Oferta A7", "Oferta de prueba A7", 15.0, user1);
	Bid a8 = new Bid("Oferta A8", "Oferta de prueba A8", 20.0, user1);
	Bid a9 = new Bid("Oferta A9", "Oferta de prueba A9", 30.0, user1);
	Bid a10 = new Bid("Oferta A10", "Oferta de prueba A10", 30.0, user1);
	Bid b1 = new Bid("Oferta B1", "Oferta de prueba B1", 10.2, user2);
	Bid b2 = new Bid("Oferta B2", "Oferta de prueba B2", 15.2, user2);
	Bid c1 = new Bid("Oferta C1", "Oferta de prueba C1", 10.2, user3);
	Bid c2 = new Bid("Oferta C2", "Oferta de prueba C2", 15.2, user3);
	Bid d2 = new Bid("Oferta D2", "Oferta de prueba D2", 20.2, user4);

	Bid b4 = new Bid("Oferta B4", "Oferta de prueba B4", 30.2, user2);
	Bid c4 = new Bid("Oferta C4", "Oferta de prueba C4", 30.2, user3);

	b4.setSpecialBid(true);
	c4.setSpecialBid(true);

	Set user1Bids = new HashSet<Bid>() {
	    {
		add(a1);
		add(a2);
		add(a3);
		add(a4);
		add(a5);
		add(a6);
		add(a7);
		add(a8);
		add(a9);
		add(a10);
	    }
	};
	user1.setBids(user1Bids);
	Set user2Bids = new HashSet<Bid>() {
	    {
		add(b1);
		add(b2);
		add(new Bid("Oferta B3", "Oferta de prueba B3", 20.2, user2));
		add(b4);
	    }
	};
	user2.setBids(user2Bids);
	Set user3Bids = new HashSet<Bid>() {
	    {
		add(c1);
		add(c2);
		add(new Bid("Oferta C3", "Oferta de prueba C3", 20.2, user3));
		add(c4);
	    }
	};
	user3.setBids(user3Bids);
	Set user4Bids = new HashSet<Bid>() {
	    {
		add(new Bid("Oferta D1", "Oferta de prueba D1", 200.2, user4));
		add(d2);
		add(new Bid("Oferta D3", "Oferta de prueba D3", 54.2, user4));
		add(new Bid("Super D1", "Super de prueba D3", 100.0, user4));
		add(new Bid("Super D2", "Super de prueba D3", 110.0, user4));
		add(new Bid("Super D3", "Super de prueba D3", 54.2, user4));
		add(new Bid("Super D4", "Super de prueba D3", 54.2, user4));
		add(new Bid("Super D5", "Super de prueba D3", 54.2, user4));
		add(new Bid("Super D6", "Super de prueba D3", 54.2, user4));
	    }
	};
	user4.setBids(user4Bids);
	Set user5Bids = new HashSet<Bid>() {
	    {
		add(new Bid("Oferta E1", "Oferta de prueba E1", 25.2, user5));
		add(new Bid("Oferta E2", "Oferta de prueba E2", 45.2, user5));
		add(new Bid("Oferta E3", "Oferta de prueba E3", 23.2, user5));
		add(new Bid("Oferta E4", "Oferta de prueba E4", 16.23, user5));
	    }
	};
	user5.setBids(user5Bids);

	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
	usersService.addUser(user4);
	usersService.addUser(user5);
	usersService.addUser(user6);

	///
	/// # Conversation producto B1 1
	Conversation conversationB1_1 = new Conversation(user1, b1);
	Set messagesProductB1_1 = new HashSet<Message>() {
	    {
		add(new Message(conversationB1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 00, 10).getTime(),
			"B1_1 - Hola, estoy interesado en el producto."));
		add(new Message(conversationB1_1, user2, new GregorianCalendar(2019, 2, 6, 16, 00, 11).getTime(),
			"B1_1 - Si te gusta compralo."));
		add(new Message(conversationB1_1, user2, new GregorianCalendar(2019, 2, 6, 16, 00, 12).getTime(),
			"B1_1 - Esta muy barato."));
		add(new Message(conversationB1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 00, 13).getTime(),
			"B1_1 - Vale, lo hare :D"));
		add(new Message(conversationB1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 00, 15).getTime(),
			"B1_1 - Gracias."));
	    }
	};
	conversationB1_1.setMessages(messagesProductB1_1);

	/// # Conversation producto B1 2
	Conversation conversationB1_2 = new Conversation(user3, b1);
	Set messagesProductB1_2 = new HashSet<Message>() {
	    {
		add(new Message(conversationB1_2, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 10).getTime(),
			"B1_2 - Hola, estoy interesado en el producto."));
		add(new Message(conversationB1_2, user2, new GregorianCalendar(2019, 2, 6, 16, 11, 11).getTime(),
			"B1_2 - Si te gusta compralo."));
		add(new Message(conversationB1_2, user2, new GregorianCalendar(2019, 2, 6, 16, 11, 12).getTime(),
			"B1_2 - Esta muy barato."));
		add(new Message(conversationB1_2, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 13).getTime(),
			"B1_2 - Vale, lo hare :D"));
		add(new Message(conversationB1_2, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 14).getTime(),
			"B1_2 - Gracias."));
	    }
	};
	conversationB1_2.setMessages(messagesProductB1_2);

	/// # Conversation producto C1 1
	Conversation conversationC1_1 = new Conversation(user1, c1);
	Set messagesProductC1_1 = new HashSet<Message>() {
	    {
		add(new Message(conversationC1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 12, 10).getTime(),
			"C1_1 - Hola, estoy interesado en el producto."));
		add(new Message(conversationC1_1, user3, new GregorianCalendar(2019, 2, 6, 16, 12, 11).getTime(),
			"C1_1 - Si te gusta compralo."));
		add(new Message(conversationC1_1, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 12).getTime(),
			"B1_2 - Esta muy barato."));
		add(new Message(conversationC1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 12, 13).getTime(),
			"C1_1 - Vale, lo hare :D"));
		add(new Message(conversationC1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 12, 14).getTime(),
			"C1_1 - Gracias."));
	    }
	};
	conversationC1_1.setMessages(messagesProductC1_1);

	a1.setBuyerUser(user2);
	a2.setBuyerUser(user4);
	a3.setBuyerUser(user5);
	a4.setBuyerUser(user4);
	b1.setBuyerUser(user1);
	b2.setBuyerUser(user3);
	c1.setBuyerUser(user1);
	c2.setBuyerUser(user3);
	d2.setBuyerUser(user2);

	bidService.addBid(a1);
	bidService.addBid(a2);
	bidService.addBid(a3);
	bidService.addBid(a4);
	bidService.addBid(a5);
	bidService.addBid(b1);
	bidService.addBid(b2);
	bidService.addBid(c1);
	bidService.addBid(c2);
	bidService.addBid(d2);

	conversationService.addConversation(conversationB1_1);
	conversationService.addConversation(conversationB1_2);
	conversationService.addConversation(conversationC1_1);
    }
=======
<<<<<<< HEAD
package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	@Autowired
	private MarksService marksService;
	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		User user1 = new User("99999990A", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("99999991B", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("99999992C", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("99999993D", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		User user5 = new User("99999977E", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[2]);
		User user6 = new User("99999988F", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[2]);
		Set user1Marks = new HashSet<Mark>() {
			{
				add(new Mark("Nota A1", 10.0, user1));
				add(new Mark("Nota A2", 9.0, user1));
				add(new Mark("Nota A3", 7.0, user1));
				add(new Mark("Nota A4", 6.5, user1));
			}
		};
		user1.setMarks(user1Marks);
		Set user2Marks = new HashSet<Mark>() {
			{
				add(new Mark("Nota B1", 5.0, user2));
				add(new Mark("Nota B2", 4.3, user2));
				add(new Mark("Nota B3", 8.0, user2));
				add(new Mark("Nota B4", 3.5, user2));
			}
		};
		user2.setMarks(user2Marks);
		Set user3Marks = new HashSet<Mark>() {
			{
				;
				add(new Mark("Nota C1", 5.5, user3));
				add(new Mark("Nota C2", 6.6, user3));
				add(new Mark("Nota C3", 7.0, user3));
			}
		};
		user3.setMarks(user3Marks);
		Set user4Marks = new HashSet<Mark>() {
			{
				add(new Mark("Nota D1", 10.0, user4));
				add(new Mark("Nota D2", 8.0, user4));
				add(new Mark("Nota D3", 9.0, user4));
			}
		};
		user4.setMarks(user4Marks);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
||||||| merged common ancestors
package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Bid;
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
		User user7 = new User("admin@email.com", "Edward", "Núñez");
		user7.setPassword("admin");
		user7.setRole(rolesService.getRoles()[1]);
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
				add(new Bid("Oferta B1", "Oferta de prueba B1", 10.2, user2));
				add(new Bid("Oferta B2", "Oferta de prueba B2", 15.2, user2));
				add(new Bid("Oferta B3", "Oferta de prueba B3", 20.2, user2));
				add(new Bid("Oferta B4", "Oferta de prueba B4", 30.2, user2));
			}
		};
		user2.setBids(user2Bids);
		Set user3Bids = new HashSet<Bid>() {
			{
				add(new Bid("Oferta C1", "Oferta de prueba C1", 10.2, user3));
				add(new Bid("Oferta C2", "Oferta de prueba C2", 15.2, user3));
				add(new Bid("Oferta C3", "Oferta de prueba C3", 20.2, user3));
				add(new Bid("Oferta C4", "Oferta de prueba C4", 30.2, user3));
			}
		};
		user3.setBids(user3Bids);
		Set user4Bids = new HashSet<Bid>() {
			{
				add(new Bid("Oferta D1", "Oferta de prueba D1", 200.2, user4));
				add(new Bid("Oferta D2", "Oferta de prueba D2", 20.2, user4));
				add(new Bid("Oferta D3", "Oferta de prueba D3", 54.2, user4));
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
=======
package com.uniovi.services;

import java.util.GregorianCalendar;
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
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private BidsService bidService;

	@SuppressWarnings({ "serial", "rawtypes", "unchecked"})
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
		User user6 = new User("admin@email.com", "Administrador", "Administrador");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);
		
		Bid a1 = new Bid("Oferta A1", "Oferta de prueba A1", 10.0, user1);
		Bid a2 = new Bid("Oferta A2", "Oferta de prueba A2", 15.0, user1);
		Bid a3 = new Bid("Oferta A3", "Oferta de prueba A3", 20.0, user1);
		Bid a4 = new Bid("Oferta A4", "Oferta de prueba A4", 30.0, user1);
		Bid a5 = new Bid("Oferta A5", "Oferta de prueba A5", 30.0, user1);
		Bid a6 = new Bid("Oferta A6", "Oferta de prueba A6", 10.0, user1);
		Bid a7 = new Bid("Oferta A7", "Oferta de prueba A7", 15.0, user1);
		Bid a8 = new Bid("Oferta A8", "Oferta de prueba A8", 20.0, user1);
		Bid a9 = new Bid("Oferta A9", "Oferta de prueba A9", 30.0, user1);
		Bid a10 = new Bid("Oferta A10", "Oferta de prueba A10", 30.0, user1);
		Bid b1 = new Bid("Oferta B1", "Oferta de prueba B1", 10.2, user2);
		Bid b2 = new Bid("Oferta B2", "Oferta de prueba B2", 15.2, user2);
		Bid c1 = new Bid("Oferta C1", "Oferta de prueba C1", 10.2, user3);
		Bid c2 = new Bid("Oferta C2", "Oferta de prueba C2", 15.2, user3);
		Bid d2 = new Bid("Oferta D2", "Oferta de prueba D2", 20.2, user4);
		
		Set user1Bids = new HashSet<Bid>() {
			{
				add(a1);
				add(a2);
				add(a3);
				add(a4);
				add(a5);
				add(a6);
				add(a7);
				add(a8);
				add(a9);
				add(a10);
			}
		};
		user1.setBids(user1Bids);
		Set user2Bids = new HashSet<Bid>() {
			{
				add(b1);
				add(b2);
				add(new Bid("Oferta B3", "Oferta de prueba B3", 20.2, user2));
				add(new Bid("Oferta B4", "Oferta de prueba B4", 30.2, user2));
			}
		};
		user2.setBids(user2Bids);
		Set user3Bids = new HashSet<Bid>() {
			{
				add(c1);
				add(c2);
				add(new Bid("Oferta C3", "Oferta de prueba C3", 20.2, user3));
				add(new Bid("Oferta C4", "Oferta de prueba C4", 30.2, user3));
			}
		};
		user3.setBids(user3Bids);
		Set user4Bids = new HashSet<Bid>() {
			{
				add(new Bid("Oferta D1", "Oferta de prueba D1", 200.2, user4));
				add(d2);
				add(new Bid("Oferta D3", "Oferta de prueba D3", 54.2, user4));
				add(new Bid("Super D1", "Super de prueba D3", 100.0, user4));
				add(new Bid("Super D2", "Super de prueba D3", 110.0, user4));
				add(new Bid("Super D3", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D4", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D5", "Super de prueba D3", 54.2, user4));
				add(new Bid("Super D6", "Super de prueba D3", 54.2, user4));
			}
		};
		user4.setBids(user4Bids);
		Set user5Bids = new HashSet<Bid>() {
			{
				add(new Bid("Oferta E1", "Oferta de prueba E1", 25.2, user5));
				add(new Bid("Oferta E2", "Oferta de prueba E2", 45.2, user5));
				add(new Bid("Oferta E3", "Oferta de prueba E3", 23.2, user5));
				add(new Bid("Oferta E4", "Oferta de prueba E4", 16.23, user5));
			}
		};
		user5.setBids(user5Bids);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		///
		/// # Conversation producto B1 1
		Conversation conversationB1_1 = new Conversation(user1, b1);
		Set messagesProductB1_1 = new HashSet<Message>() {
			{
				add(new Message(conversationB1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 00, 10).getTime(), "B1_1 - Hola, estoy interesado en el producto."));
				add(new Message(conversationB1_1, user2, new GregorianCalendar(2019, 2, 6, 16, 00, 11).getTime(), "B1_1 - Si te gusta compralo."));
				add(new Message(conversationB1_1, user2, new GregorianCalendar(2019, 2, 6, 16, 00, 12).getTime(), "B1_1 - Esta muy barato."));
				add(new Message(conversationB1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 00, 13).getTime(), "B1_1 - Vale, lo hare :D"));
				add(new Message(conversationB1_1, user1,new GregorianCalendar(2019, 2, 6, 16, 00, 15).getTime(), "B1_1 - Gracias."));
			}
		};
		conversationB1_1.setMessages(messagesProductB1_1);
		
		/// # Conversation producto B1 2
		Conversation conversationB1_2 = new Conversation(user3, b1);
		Set messagesProductB1_2 = new HashSet<Message>() {
			{
				add(new Message(conversationB1_2, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 10).getTime(), "B1_2 - Hola, estoy interesado en el producto."));
				add(new Message(conversationB1_2, user2, new GregorianCalendar(2019, 2, 6, 16, 11, 11).getTime(), "B1_2 - Si te gusta compralo."));
				add(new Message(conversationB1_2, user2, new GregorianCalendar(2019, 2, 6, 16, 11, 12).getTime(), "B1_2 - Esta muy barato."));
				add(new Message(conversationB1_2, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 13).getTime(), "B1_2 - Vale, lo hare :D"));
				add(new Message(conversationB1_2, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 14).getTime(), "B1_2 - Gracias."));
			}
		};
		conversationB1_2.setMessages(messagesProductB1_2);
		
		/// # Conversation producto C1 1
		Conversation conversationC1_1 = new Conversation(user1, c1);
		Set messagesProductC1_1 = new HashSet<Message>() {
			{
				add(new Message(conversationC1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 12, 10).getTime(), "C1_1 - Hola, estoy interesado en el producto."));
				add(new Message(conversationC1_1, user3, new GregorianCalendar(2019, 2, 6, 16, 12, 11).getTime(), "C1_1 - Si te gusta compralo."));
				add(new Message(conversationC1_1, user3, new GregorianCalendar(2019, 2, 6, 16, 11, 12).getTime(), "B1_2 - Esta muy barato."));
				add(new Message(conversationC1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 12, 13).getTime(), "C1_1 - Vale, lo hare :D"));
				add(new Message(conversationC1_1, user1, new GregorianCalendar(2019, 2, 6, 16, 12, 14).getTime(), "C1_1 - Gracias."));
			}
		};
		conversationC1_1.setMessages(messagesProductC1_1);
		
		a1.setBuyerUser(user2);
		a2.setBuyerUser(user4);
		a3.setBuyerUser(user5);
		a4.setBuyerUser(user4);
		a5.setBuyerUser(user5);
		b1.setBuyerUser(user1);
		b2.setBuyerUser(user3);
		c1.setBuyerUser(user1);
		c2.setBuyerUser(user3);
		d2.setBuyerUser(user2);
		
		bidService.addBid(a1);
		bidService.addBid(a2);
		bidService.addBid(a3);
		bidService.addBid(a4);
		bidService.addBid(a5);
		bidService.addBid(b1);
		bidService.addBid(b2);
		bidService.addBid(c1);
		bidService.addBid(c2);
		bidService.addBid(d2);
		
		conversationService.addConversation(conversationB1_1);
		conversationService.addConversation(conversationB1_2);
		conversationService.addConversation(conversationC1_1);
	}
>>>>>>> develop
>>>>>>> refs/remotes/origin/master
}