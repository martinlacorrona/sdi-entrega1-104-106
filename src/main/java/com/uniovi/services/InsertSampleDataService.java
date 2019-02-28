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
		User user7 = new User("admin@email.com", "Administrador", "Administrador");
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
}