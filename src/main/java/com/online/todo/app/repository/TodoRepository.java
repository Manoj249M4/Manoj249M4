package com.online.todo.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.todo.app.model.Task;

/**
 * Task Repository
 * 
 * @author Manoj Pandey
 */
@Repository
public interface TodoRepository extends JpaRepository<Task, Long> {
	List<Task> findAllByUserIdAndIsCompleted(Long userId, Boolean isCompleted);
	List<Task> findAllByUserId(Long userId);
	Task findOneById(Long taskId);
}
