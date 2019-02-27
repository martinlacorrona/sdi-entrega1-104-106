package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

@Component
public class EditMarkFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Mark mark = (Mark) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "score", "Error.empty");
		if (mark.getDescription().length() < 5 || mark.getDescription().length() > 24) {
			errors.rejectValue("description", "Error.edit.mark.length");
		}
		if (mark.getScore() > 10 || mark.getScore() < 0) {
			errors.rejectValue("score", "Error.edit.puntuation.length");
		}
		
		
	}
}