package com.online.todo.app.service;

import java.util.List;

import com.online.todo.app.model.Task;

/**
 * Interface for Task Service 
 * 
 * @author Manoj Pandey
 */
public interface TodoService {
	List<Task> getAllTasks(Long id);
	
	void toggleCompleted(Long id, Boolean isCompleted);
	void addTask(String desc);
	List<Task> getCompletedTasks(Long id);
	
	void updateTask(Long id, String desc);
	void deleteTask(Long id);
	
	List<Task> getActiveTasks(Long id);
	void removeCompleted();
	
}
