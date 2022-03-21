package com.online.todo.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Application Web Security
 * @author Manoj Pandey
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/* 
		 * Unauthorized access to login and logout
		 * With authorization,user can access to all Task Controllers.
		 * */
		
		logger.debug("configuring http security for application");
		
		http.authorizeRequests().antMatchers("/todo", "/all", "/active", "/completed", "/registration", "/resources/**").permitAll().anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll().and().logout().permitAll()
			.and().authorizeRequests().antMatchers("/h2/**", "/webjars/**").permitAll().anyRequest().authenticated()
			.and().csrf().ignoringAntMatchers("/h2/**")
            .and().headers().frameOptions().sameOrigin();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * Password encoder is applied to userDetailsService.
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}