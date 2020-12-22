package com.der.mchannel.moviesService;

import java.util.List;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.entity.Movies;
import com.der.mchannel.form.MoviesForm;

public interface MoviesService extends WebMvcConfigurer {

	public void save(MoviesForm moviesForm);

	public void saveMovie(Movies movie);

	// find with query
	public Movies findMovieByCode(String code);

	public List<Movies> search(String name);

	List<Movies> findAllMovies();

	List<Movies> findAllMovies(int pageSize, int pageOffset);

	int getMoviesCount();

	public void deleteMovieById(Integer id);
	
	public Movies findMovie(String code);
	
	public void deleteMovie(Movies movie);

}
