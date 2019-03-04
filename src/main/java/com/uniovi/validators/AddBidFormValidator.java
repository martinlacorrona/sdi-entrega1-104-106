package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Bid;

@Component
public class AddBidFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Bid.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bid bid = (Bid) target;
		
		//Comprobamos que todos los campos del formulario no estén vacíos.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");
		
		//Condiciones que consideremos oportunas.
		if (bid.getTitle().length() < 6 || bid.getTitle().length() > 20) {
			errors.rejectValue("title", "Error.bid.add.title.length");
		}
		if (bid.getDescription().length() < 6 || bid.getDescription().length() > 100) {
			errors.rejectValue("description", "Error.bid.add.description.length");
		}
		if (bid.getPrice() < 0.01) {
			errors.rejectValue("price", "Error.bid.add.price.negative");
		}
		if (bid.getPrice() > 10000) {
			errors.rejectValue("price", "Error.bid.add.price.max");
		}
	}
}