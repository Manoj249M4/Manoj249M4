package com.online.todo.app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.online.todo.app.config.WebSecurityConfig;

/**
 * Database Access Exception
 * 
 * @author Manoj Pandey
 */
public class DatabaseAccessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseAccessException.class);

	public DatabaseAccessException(String msg) {
		super(msg);
		logger.error(msg);
	}
}