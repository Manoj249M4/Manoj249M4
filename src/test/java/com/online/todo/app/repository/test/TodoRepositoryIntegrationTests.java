package com.online.todo.app.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.online.todo.app.MainApplication;
import com.online.todo.app.model.Role;
import com.online.todo.app.model.Task;
import com.online.todo.app.model.User;
import com.online.todo.app.repository.TodoRepository;
import com.online.todo.app.service.UserService;

/**
 * Integration Tests for Task Repository
 * 
 * @author Manoj Pandey
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class TodoRepositoryIntegrationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private TodoRepository taskRepository;

	private Task task;
	private User user;

	@Before
	public void setUp() {
		List<Role> roles = new ArrayList<Role>();

		user = new User();

		user.setRoles(new HashSet<>(roles));
		user.setUsername("user1");
		user.setPassword(bCryptPasswordEncoder.encode("password"));
		user.setConfirmPassword("password");

		userService.save(user);

		task = new Task();
		task.setId(1L);
		task.setUser(user);
		task.setDescription("Buy cloths");
		task.setUpdatedDate(new Date());
		task.setCompleted(false);
		task.setCreationDate(new Date());

		taskRepository.save(task);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void returnNullIf_NotFindOneByTaskId() {
		Task t1 = taskRepository.findOneById(10L);
		assertNull(t1);
	}

	@Test
	public void returnTasks_WhenfindAllByUserId() {
		List<Task> ts = taskRepository.findAllByUserId(user.getId());
		assertEquals(ts.get(0).getDescription(), "Buy cloths");
	}

	@Test
	public void returnEmptyList_WhenUserIdNotExist() {
		List<Task> ts = taskRepository.findAllByUserId(10L);
		assertTrue(ts.isEmpty());
	}

	@Test
	public void returnTaskWhen_FindAllByUserIdCompleted() {
	List<Task> ts = taskRepository.findAllByUserIdAndIsCompleted(user.getId(), false);
		assertEquals(ts.get(0).getDescription(), "Buy cloths");
	}

	@Test
	public void returnTaskWhenFindOnerByTaskId() {
		Task t1 = taskRepository.findOneById(task.getId());
		assertEquals(t1.getDescription(), "Buy cloths");
	}

	@Test
	public void returnEmptyListWhenFindAllByUserIdAndCompleted() {
		List<Task> ts = taskRepository.findAllByUserIdAndIsCompleted(user.getId(), true);
		assertTrue(ts.isEmpty());
	}
}