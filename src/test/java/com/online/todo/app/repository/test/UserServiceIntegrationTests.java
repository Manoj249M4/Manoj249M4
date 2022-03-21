package com.online.todo.app.repository.test;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.todo.app.MainApplication;
import com.online.todo.app.model.Role;
import com.online.todo.app.model.User;
import com.online.todo.app.service.UserService;

/**
 * Integration Tests for User Service
 * 
 * @author Manoj Pandey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class UserServiceIntegrationTests {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	private User user;

	@Before
	public void setUp() {

		List<Role> roles = new ArrayList<Role>();

		user = new User();

		user.setRoles(new HashSet<>(roles));
		user.setId(1L);
		user.setPassword(bCryptPasswordEncoder.encode("password"));
		user.setConfirmPassword("password");
		user.setUsername("user1");

		userService.save(user);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void  returnNull_IfAnInvalidUserNameFound() {
		User user = userService.findByUsername("doesnt-exist");
		assertNull(user);
	}

	@Test(expected = IncorrectResultSizeDataAccessException.class)
	public void returnBug_IfFindByUserName() {
		User user1= userService.findByUsername(user.getUsername());
	}
}