package com.online.todo.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.todo.app.model.Role;
import com.online.todo.app.model.User;
import com.online.todo.app.repository.RoleRepository;
import com.online.todo.app.repository.UserRepository;
import com.online.todo.app.service.UserService;

/**
 * User Service Implementation
 * 
 * @author Manoj Pandey
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public User findByUsername(String username) {
    	logger.debug("finding user by username");

        return userRepository.findByUsername(username);
    }
    
    @Override
    public void save(User user) {
    	logger.debug("saving....");
        
        ArrayList<Role> rs = new ArrayList<Role>();
        rs.add(roleRepository.findOneByName("USER"));
        
        user.setRoles(new HashSet<>(rs));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
        	userRepository.save(user);
        }
        catch (ConstraintViolationException ex) {
        	logger.error("username already exists");
        }
    }
}
