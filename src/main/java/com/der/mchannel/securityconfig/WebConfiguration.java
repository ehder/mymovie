package com.der.mchannel.securityconfig;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc   //if i use @EnableWebMvc I cann't connect to my custom style.css
public class WebConfiguration implements WebMvcConfigurer {
	
	
	
	/*You need to declare a  MessageResource Spring Bean, 
	so that the Spring automatically load the contents of the  validation.properties file to memory.*/
	   @Bean
	    public MessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        // Load file: validation.properties
	        messageSource.setBasename("classpath:validation");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	   
	   @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		   registry.addViewController("/login").setViewName("login");
		   registry.addViewController("/user/register").setViewName("register");
		   registry.addViewController("/").setViewName("/");
	}
	
}
