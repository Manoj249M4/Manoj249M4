package com.online.todo.app.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.todo.app.model.Task;
import com.online.todo.app.model.User;
import com.online.todo.app.repository.TodoRepository;
import com.online.todo.app.service.SecurityService;
import com.online.todo.app.service.TodoService;
import com.online.todo.app.service.UserService;

/**
 * Implementation of Task Service
 * 
 * @author Manoj Pandey
 */
@Service
public class TodoServiceImpl implements TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private TodoRepository taskRepository;

	@Override
	public void addTask(String desc) {
		logger.debug("something went wrong!");

		try {
			User user = userService.findByUsername(securityService.findLoggedInUsername());
	
	        Task task = new Task();
	        task.setDescription(desc);
	        task.setCreationDate(new Date());
	        task.setUpdatedDate(new Date());
	        task.setUser(user);
	        task.setIsCompleted(false);
	
			taskRepository.save(task); 
		}
		catch(Exception exception) {
			logger.debug("error saving task");
		}
	}
	
	@Override
	public void updateTask(Long id, String desc) {
		logger.debug("updating task");

		try {
			Task task = taskRepository.findOneById(id);
	        
			if(task != null) {
	        	task.setDescription(desc);
	        	task.setUpdatedDate(new Date());
	    		taskRepository.save(task);
			}
		}
		catch(Exception exception) {
			logger.error("error updating task");
		}
	}

	@Override
	public void deleteTask(Long id) {
		logger.debug("removing task");

		try {
			Task task = taskRepository.findOneById(id);
			
			if(task != null) {
				taskRepository.delete(id);
			}
		}
		catch(Exception exception) {
			logger.error("error removing task");
		}
	}
	
	@Override
	public List<Task> getActiveTasks(Long id) {
		logger.debug("getting tasks for user id: " + id.toString());

		List<Task> tasks = null;
		
		try {
			tasks = taskRepository.findAllByUserIdAndIsCompleted(id, false);
		}
		catch(Exception exception) {
			logger.error("error getting all tasks for user");
		}

		return tasks;
	}


	@Override
	public List<Task> getAllTasks(Long id) {
		logger.debug("getting tasks for user id: " + id.toString());

		List<Task> tasks = null;
		
		try {
			tasks = taskRepository.findAllByUserId(id);
		}
		catch(Exception exception) {
			logger.error("error getting all tasks for user");
		}

		return tasks;
	}

	@Override
	public List<Task> getCompletedTasks(Long id) {
		logger.debug("retriving tasks for id: " + id.toString());
		
		List<Task> tasks = null;
		
		try {
			tasks = taskRepository.findAllByUserIdAndIsCompleted(id, true);
		}
		catch(Exception exception) {
			logger.error("error getting all tasks for user");
		}

		return tasks;
	}

	@Override
	public void toggleCompleted(Long id, Boolean isCompleted) {
		logger.debug("toggling completed");

		try {
			Task task = taskRepository.findOneById(id);
			
			if(task != null) {
				boolean completed = (isCompleted == null || isCompleted == Boolean.FALSE) ? false : true;
	            task.setIsCompleted(completed);
	            taskRepository.save(task);
			}
		}
		catch(Exception exception) {
			logger.error("something wrong with toggling");
		}
	}
	
	@Override
	public void removeCompleted() {
		logger.debug("removing completed tasks");
		
		try {
			User user = userService.findByUsername(securityService.findLoggedInUsername());

			List<Task> tasks = taskRepository.findAllByUserIdAndIsCompleted(user.getId(), true);
			
	        for(Task task : tasks) {
	            if(task.getIsCompleted()) {
	                taskRepository.delete(task);
	            }
	        }
		}
		catch(Exception exception) {
			logger.error("error occured while retriving tasks");
		}
	}
}
