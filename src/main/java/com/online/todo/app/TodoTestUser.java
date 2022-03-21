package com.online.todo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.online.todo.app.model.User;
import com.online.todo.app.repository.UserRepository;

@Component
public class TodoTestUser implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TodoTestUser.class);
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("Testuser");
		user.setPassword(bCryptPasswordEncoder.encode("pwd123"));
		userRepository.save(user);
		
		logger.debug(String.format("user name %s is created", user.getUsername()));
	}

}
