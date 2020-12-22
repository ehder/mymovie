package com.der.mchannel;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class DMoviesChannelApplication {
	
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(DMoviesChannelApplication.class, args);
	}
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		// See: application.properties
		datasource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		datasource.setUrl(env.getProperty("spring.datasource.url"));
		datasource.setUsername(env.getProperty("spring.datasource.username"));
		datasource.setPassword(env.getProperty("spring.datasource.password"));

		System.out.println("## getDataSource: " + datasource);

		return datasource;
	}

}
