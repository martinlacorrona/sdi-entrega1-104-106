package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class EditUserFormValidator implements Validator {
	@Autowired
	private UsersService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
		if (user.getEmail().length() < 5 || user.getEmail().length() > 40) {
			errors.rejectValue("dni", "Error.edit.user.dni.length");
		}
		if (usersService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("dni", "Error.edit.user.dni.duplicate");
		}
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.edit.user.name.length");
		}
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.edit.user.lastName.length");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.edit.user.password.length");
		}
		
	}
}