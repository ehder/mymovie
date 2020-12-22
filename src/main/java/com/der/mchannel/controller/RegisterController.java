package com.der.mchannel.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.der.mchannel.entity.Account;
import com.der.mchannel.entity.Role;
import com.der.mchannel.moviesServiceImpl.RoleServiceImpl;
import com.der.mchannel.moviesServiceImpl.UserAccountServiceImpl;

@Controller
public class RegisterController {

	@Autowired
	private UserAccountServiceImpl service;
	
	@Autowired
	private RoleServiceImpl roleSerivce;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Bean
	BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@RequestMapping(value = "/user/role", method = RequestMethod.GET)
	public String Role(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		return "/role";
	}

	@RequestMapping(value = "/user/role", method = RequestMethod.POST)
	public String Role(@ModelAttribute(value = "role") @Valid Role role) {
		roleSerivce.save(role);
		return "role";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String register(Model model) {
		Account account = new Account();
		
		//List<Role> roles= roleSerivce.findAll();
		//model.addAttribute("roles",roles);
		
		List<Role> roles = Arrays.asList(roleSerivce.findByName("ROLE_USER"));
		System.out.println(roles);
		
		account.setRoles(Arrays.asList(roleSerivce.findByName("ROLE_USER")));
		model.addAttribute("account", account);
		
		return "register";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String register(@ModelAttribute(value = "account") @Valid Account account, BindingResult result,
			Model model) {
		
		
		if (result.hasErrors()) {
			return "register";
		}
		
		account.setActive(true);
		account.setEncrytedPassword(encoder.encode(account.getEncrytedPassword()));
		service.createAccount(account);
		return "redirect:/user/register";
	}

}
