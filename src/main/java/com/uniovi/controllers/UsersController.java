package com.uniovi.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    private RolesService rolesService;

    @RequestMapping("/user/list")
    public String getListado(Model model) {
	model.addAttribute("usersList", usersService.getUsers());
	return "user/list";
    }

    @RequestMapping(value = "/user/add")
    public String getUser(Model model) {
	model.addAttribute("rolesList", rolesService.getRoles());
	return "user/add";
    }

    @RequestMapping("/user/delete/{ids}")
    public String delete(Principal principal, @PathVariable String[] ids) {
	// Vamos a sacar la ID del usuario logueado para que no lo borre
	User activeUser = usersService.getUserByEmail(principal.getName());
	Long idLoguedUser = activeUser.getId();

	for (String id : ids)
	    if (Long.parseLong(id) != idLoguedUser) // No borrarse a si mismo
		usersService.deleteUser(Long.parseLong(id));
	return "redirect:/user/list";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
	model.addAttribute("user", new User());
	return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result, HttpSession session) {
	signUpFormValidator.validate(user, result);
	if (result.hasErrors()) {
	    return "signup";
	}
	user.setRole(rolesService.getRoles()[0]);
	user.setMoney(100.0);
	usersService.addUser(user);
	securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());

	// Agrega estos dos atributos ya que al hacer autologin no pasa por
	// el handler de authenticationsucceshandler.
	session.setAttribute("money", user.getMoneyFormatted());
	session.setAttribute("email", user.getEmail());

	return "redirect:login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
	return "login";
    }
}
