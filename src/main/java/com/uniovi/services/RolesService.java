<<<<<<< HEAD
package com.uniovi.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
    String[] roles = { "ROLE_USER", "ROLE_ADMIN" };

    public String[] getRoles() {
	return roles;
    }
=======
package com.uniovi.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
	String[] roles = { "ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN" };

	public String[] getRoles() {
		return roles;
	}
>>>>>>> refs/remotes/origin/master
}