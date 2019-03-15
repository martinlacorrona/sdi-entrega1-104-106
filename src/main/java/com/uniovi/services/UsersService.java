<<<<<<< HEAD
package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
	List<User> users = usersRepository.findAll();
	return users;
    }

    public User getUser(Long id) {
	return usersRepository.findById(id).get();
    }

    public void addUser(User user) {
	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	usersRepository.save(user);
    }

    public User getUserByEmail(String email) {
	User user = usersRepository.findByEmail(email);
	return user;
    }

    public void deleteUser(Long id) {
	usersRepository.deleteById(id);
    }

    /**
     * Actualiza el dinero de un usuario
     * 
     * @param money que se quiere actualizar
     * @param email del usuario
     */
    public void updateMoney(Double money, String email) {
	usersRepository.updateMoney(money, email);
    }
=======
<<<<<<< HEAD
package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
		return users;
	}
	


	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	public User getUserByDni(String dni) {
		User user = usersRepository.findByDni(dni);
		return user;
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public Page<User>searchUserByName(String searchText, Pageable pageable) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.findByName(pageable,searchText);
		return users;
	}
	
	

||||||| merged common ancestors
package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Page<User> getUsers(Pageable pageable) {
		Page<User> users = usersRepository.findAll(pageable);
		return users;
	}
	


	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	public User getUserByEmail(String email) {
		User user = usersRepository.findByEmail(email);
		return user;
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	public Page<User>searchUserByName(String searchText, Pageable pageable) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.findByName(pageable,searchText);
		return users;
	}
	
	

=======
package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> getUsers() {
		List<User> users = usersRepository.findAll();
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	public User getUserByEmail(String email) {
		User user = usersRepository.findByEmail(email);
		return user;
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}
	
	/**
	 * Pone una oferta un id, haciendo que esté comprada.
	 * @param Double dinero
	 * @param String email
	 * @return
	 */
	public void updateMoney(Double money , String email) {
		usersRepository.updateMoney(money,email);
	}
>>>>>>> develop
>>>>>>> refs/remotes/origin/master
}