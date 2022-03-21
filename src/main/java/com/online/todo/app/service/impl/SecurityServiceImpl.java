package com.online.todo.app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.online.todo.app.service.SecurityService;

/**
 * Implementation of Security Service
 * 
 * @author Manoj Pandey
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
    @Override
    public String findLoggedInUsername() {
    	logger.debug("finding user name of logged in user");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (username != null) {
        	logger.debug(String.format("user name %s is logged in", username));
            return username;
        }

        return null;
    }
    
}
