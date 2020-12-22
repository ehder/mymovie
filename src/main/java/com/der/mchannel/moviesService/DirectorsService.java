package com.der.mchannel.moviesService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.entity.Directors;

public interface DirectorsService extends WebMvcConfigurer {

	public void addDirectors(Directors g);
	
	public Optional<Directors> findDirectors(Integer id);
	
	public List<Directors> findAllDirector();
	
	public void deleteDirectorById(Integer id);
	

}
