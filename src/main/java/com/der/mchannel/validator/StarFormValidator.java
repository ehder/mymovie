package com.der.mchannel.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.der.mchannel.entity.Stars;
import com.der.mchannel.moviesServiceImpl.CastServiceImpl;

@Component
public class StarFormValidator implements Validator {
	
	@Autowired
	private CastServiceImpl starService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Stars.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stars star = (Stars) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.Stars.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.Stars.name");
		
		
		String name = star.getName();
		
		if (name != null && name.length() > 0) {
			
			if (starService.findStarByName(name) != null) {
				errors.rejectValue("name", "Duplicate.Stars.name");
			}
			
		}
			
		
	}

}
