package com.uniovi.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UsersService usersService;

    /**
     * Usamos este metodo para guardar y que persistan
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	    Authentication authentication) throws IOException, ServletException {
	HttpSession session = httpServletRequest.getSession();
	String email = SecurityContextHolder.getContext().getAuthentication().getName();
	User activeUser = usersService.getUserByEmail(email);

	// Ponemos los atributos del money y el email que esten siempre.
	session.setAttribute("money", activeUser.getMoneyFormatted());
	session.setAttribute("email", activeUser.getEmail());

	// Redirigimos a home
	httpServletResponse.sendRedirect("/");
    }
}