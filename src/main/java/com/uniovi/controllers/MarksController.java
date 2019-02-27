package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.EditMarkFormValidator;

@Controller
public class MarksController {

	@SuppressWarnings("unused")
	@Autowired
	private HttpSession httpSession;

	@Autowired // Inyectar el servicio
	private MarksService marksService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private EditMarkFormValidator editMarkValidator;

	@RequestMapping("/mark/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String dni = principal.getName(); // DNI es el name de la autenticación
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());
		if (searchText != null && !searchText.isEmpty()) {
			marks = marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, user);
		} else {
			marks = marksService.getMarksForUser(pageable, user);
		}
		model.addAttribute("markList", marks.getContent());
		model.addAttribute("page", marks);
		return "mark/list";
	}

	@RequestMapping("/mark/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String dni = principal.getName(); // DNI es el name de la autenticación
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = marksService.getMarksForUser(pageable, user);
		model.addAttribute("markList", marks.getContent());
		return "mark/list :: tableMarks";
	}

	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		marksService.addMark(mark);
		return "redirect:/mark/list";
	}

	@RequestMapping("/mark/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		return "mark/details";
	}

	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		marksService.deleteMark(id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/add")
	public String getMark(Model model,Pageable pageable) {
		model.addAttribute("usersList", usersService.getUsers(pageable));
		return "mark/add";
	}

	// Model = objeto
	@RequestMapping(value = "/mark/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id,Pageable pageable) {
		model.addAttribute("mark", marksService.getMark(id));
		model.addAttribute("usersList", usersService.getUsers(pageable));
		return "mark/edit";
	}

	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
	public String setEdit(@PathVariable Long id, @Validated Mark mark, BindingResult result) {
		editMarkValidator.validate(mark, result);
		if (result.hasErrors()) {
			return "mark/edit";
		}
		Mark original = marksService.getMark(id); // modificar solo score y description
		original.setScore(mark.getScore());
		original.setDescription(mark.getDescription());
		marksService.addMark(original);
		return "redirect:/mark/details/" + id;
	}

	@RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id) {
		marksService.setMarkResend(true, id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id) {
		marksService.setMarkResend(false, id);
		return "redirect:/mark/list";
	}

}
