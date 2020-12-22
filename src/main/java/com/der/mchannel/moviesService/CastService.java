package com.der.mchannel.moviesService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.entity.Stars;

public interface CastService extends WebMvcConfigurer {

	public void addStar(Stars s);
	
	public Optional<Stars> findStars(Integer id);
	
	public List<Stars> findAllStar();
	
	public void deleteCast(Integer code);
	
	public Stars findStarByName(String name);

}
