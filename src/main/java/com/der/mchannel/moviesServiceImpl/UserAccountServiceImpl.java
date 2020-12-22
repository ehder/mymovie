package com.der.mchannel.moviesServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.der.mchannel.entity.Account;
import com.der.mchannel.entity.Role;
import com.der.mchannel.moviesService.UserAccountService;
import com.der.mchannel.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

	@Autowired
	private UserAccountRepository repo;
	
	@Autowired
	private RoleServiceImpl roleSerivce;

	@Override
	public Account createAccount(Account acc) {
		return repo.save(acc);
	}
	
	@Override
	public Account findByEmail(String email) {
		return repo.findAccountByuserName(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = repo.findAccountByuserName(username);
		System.out.println("Account" + " : " + account);

		if (account == null) {
			throw new UsernameNotFoundException("User" + username + "was not found in the database");
		}

		List<Role> roleList = account.getRoles();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Role role : roleList) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
			grantList.add(authority);
		}

		boolean enabled = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userDetails = new User(account.getUserName(), account.getEncrytedPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);
		System.out.println(userDetails.getUsername() + " : " + userDetails.getPassword() + " : "
				+ userDetails.getAuthorities() + " : " + userDetails.isEnabled() + " : " + credentialsNonExpired + " : "
				+ userDetails + " : " + " ***");
		return userDetails;
	}
	
	/*public Account createUserAccount(Connection<?> connection) {
		
		ConnectionKey key = connection.getKey();
		// (facebook,12345), (google,123) ...
		
		System.out.println("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
		
		UserProfile userProfile = connection.fetchUserProfile();
		
		String email = userProfile.getEmail();
		Account user = this.findByEmail(email);
		
		if(user != null) {
			return user;
		}
		
		String userName_prefix = userProfile.getFirstName().trim().toLowerCase()
				+ "_" + userProfile.getLastName().trim().toLowerCase();
		
		String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(randomPassword);
        
        Role role;
		
		user = new Account();
		user.setActive(true);
		user.setEncrytedPassword(encrytedPassword);
		user.setUserName(userName);
		user.setEmail(email);
		user.setFirstName(userProfile.getFirstName());
		user.setLastName(userProfile.getLastName());
		
		// Create default Role
        List<String> roleNames = new ArrayList<String>();
        roleNames.add(role.getName());
        this.user.createRoleFor(appUser, roleNames);
		
		return null;
	}*/

	@Override
	public List<Account> findAllUser() {
		return repo.findAll();
	}



	/*@Override
	public Optional<Account> findAccountByUserName(String name) {
		return repo.findAccountByUserName(name);
	}*/

}
