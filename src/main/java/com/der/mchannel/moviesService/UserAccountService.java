package com.der.mchannel.moviesService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.entity.Account;

public interface UserAccountService extends WebMvcConfigurer {

	Account createAccount(Account acc);
	
	//Optional<Account> findAccountByUserName(String name);
	
	List<Account> findAllUser();
	
	public Account findByEmail(String email);
	
	
	
}
