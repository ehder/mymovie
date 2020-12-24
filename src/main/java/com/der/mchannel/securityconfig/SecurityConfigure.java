package com.der.mchannel.securityconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.der.mchannel.moviesServiceImpl.UserAccountServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	/*
	 * @Autowired private DataSource dataSource;
	 */

	@Autowired
	private UserAccountServiceImpl uDetailsServiceImpl;

	// For password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	

/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select id,user_name encryted_password,active from account where user_name = ?")
				.authoritiesByUsernameQuery("select user_name,name from account, role where user_name = ?")
				.passwordEncoder(passwordEncoder());

	}*/

	@Autowired
	public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(uDetailsServiceImpl).passwordEncoder(passwordEncoder());

	}

	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider(){
	 * DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	 * auth.setUserDetailsService(uDetailsServiceImpl);
	 * auth.setPasswordEncoder(passwordEncoder()); return auth; }
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.authenticationProvider(authenticationProvider()); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();// We don't need CSRF for this example

		// .anyRequest().authenticated()
		// all request requires a logged in user and we don't need it for now

		// Requires login with role ROLE_EMPLOYEE or ROLE_MANAGER.
		// If not, it will redirect to /admin/login.
		http.authorizeRequests().antMatchers("/").permitAll();
		
		http.authorizeRequests().antMatchers("/user/accountInfo").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");

		http.authorizeRequests().antMatchers("/admin/**")
				.access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");

		// Page Only For Manager
		// http.authorizeRequests().antMatchers("/admin/movie").access("hasRole('ROLE_MANAGER')");

		// When user login, role XX.
		// But access to the page requires the YY role,
		// An AccessDeniedException will be thrown.
		http.authorizeRequests().and()
				// default response if the client wants to get a resource unauthorized
				.exceptionHandling().accessDeniedPage("/403");

		// Configuration for Login Form.
		http.authorizeRequests().and().formLogin()

				// the URL on which the clients should post the login information
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/user/login").defaultSuccessUrl("/").failureUrl("/user/login?error=true")

				// the username parameter in the queryString, default is 'username'
				//should be the same on your entity, your user name and password
				.usernameParameter("userName")
				// the password parameter in the queryString, default is 'password'
				.passwordParameter("encrytedPassword")

				// Configuration for the Logout page.
				// (After logout, go to home page)
				.and().logout()
				.logoutUrl("/logout");
		
		http.oauth2Login();
	}

	/*
	 * @Bean public PersistentTokenRepository persistentTokenRepository() {
	 * JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
	 * db.setDataSource(dataSource); return db; }
	 */

}
