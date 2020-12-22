package com.der.mchannel.moviesService;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.entity.Comment;

public interface CommentService extends WebMvcConfigurer{
	
	public void addComment(Comment comment);
	
	

}
