<<<<<<< HEAD
package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User findByEmail(String email);

    /**
     * Actualiza el dinero de un usuario
     * 
     * @param money dinero que se le quiere poner
     * @param email del usuario (es su identificador)
     */
    @Modifying
    @Transactional
    @Query("UPDATE User SET money = ?1 WHERE (LOWER(email) LIKE LOWER(?2))")
    void updateMoney(Double money, String email);

=======
<<<<<<< HEAD
package com.uniovi.repositories;

import com.uniovi.entities.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1))")
	Page<User> findByName(Pageable pageable,String seachtext);

	User findByDni(String dni);
	
	Page<User> findAll(Pageable pageable);
||||||| merged common ancestors
package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1))")
	Page<User> findByName(Pageable pageable,String seachtext);
	
	Page<User> findAll(Pageable pageable);

	User findByEmail(String email);
=======
package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();

	User findByEmail(String email);
	
	/**
	 * Compra una Bid.
	 */
	@Modifying
	@Transactional
	@Query("UPDATE User SET money = ?1 WHERE (LOWER(email) LIKE LOWER(?2))")
	void updateMoney(Double money, String email);
	
>>>>>>> develop
>>>>>>> refs/remotes/origin/master
}