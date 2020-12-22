package com.der.mchannel.moviesService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.entity.Generes;

public interface GeneresService extends WebMvcConfigurer {

	public void addGeneres(Generes g);
	
	public Optional<Generes> findGeneres(Integer id);
	
	public List<Generes> findAllGeneres();
	
	public void deleteGeneresById(Integer id);
	

}
