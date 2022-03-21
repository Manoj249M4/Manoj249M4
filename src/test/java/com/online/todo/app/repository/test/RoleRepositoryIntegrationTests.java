package com.online.todo.app.repository.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.todo.app.MainApplication;
import com.online.todo.app.model.Role;
import com.online.todo.app.repository.RoleRepository;

/**
 * Integration Tests for Role Repository
 * 
 * @author Manoj Pandey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class RoleRepositoryIntegrationTests {

	@Autowired
	private RoleRepository roleRepository;

	@Before
	public void setUp() {
	}

	@Test
	public void whenIdentifyAnUserByName_thenReturnUserRole() {

		Role role1 = roleRepository.findOneByName("USER");
		assertEquals(role1.getName(), "USER");

		Role role2 = roleRepository.findOneByName("ADMIN");
		assertEquals(role2.getName(), "ADMIN");

	}
}