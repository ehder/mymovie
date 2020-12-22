package com.der.mchannel.validator;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.der.mchannel.entity.Movies;
import com.der.mchannel.entity.Stars;
import com.der.mchannel.form.MoviesForm;
import com.der.mchannel.moviesServiceImpl.CastServiceImpl;
import com.der.mchannel.moviesServiceImpl.MoviesServiceImpl;

@Component
public class MovieFormValidator implements Validator {

	@Autowired
	private MoviesServiceImpl movieService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == MoviesForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		MoviesForm movieForm = (MoviesForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code",      "NotEmpty.MoviesForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",     "NotEmpty.MoviesForm.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year",      "NotEmpty.MoviesForm.year");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "runtime",   "NotBlank.MoviesForm.runtime");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imdbRating","NotEmpty.MoviesForm.imdbRating");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rottenTomatoes", "NotEmpty.MoviesForm.rottenTomatoes");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",    "NotEmpty.MoviesForm.description");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "movieLink",      "NotEmpty.MoviesForm.movieLink");
		
		/*String code = movieForm.getCode();
		if (code != null && code.length() > 0) {
			if (movieForm.isNewMovie()) {
				Movies movie = movieService.findMovieByCode(code);
				if (movie != null) {
					errors.rejectValue("code", "Duplicate.MoviesForm.code");
				}
			}
		}*/
		
		/*String code = movieForm.getCode();
		if (code != String.valueOf(movieService.findMovieByCode(code))) {
			errors.rejectValue("code", "Duplicate.MoviesForm.code");
		}*/

	}

}
