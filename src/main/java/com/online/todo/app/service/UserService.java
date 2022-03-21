package com.online.todo.app.service;

import com.online.todo.app.model.User;

/**
 *Interface for  User Service 
 * 
 * @author Manoj Pandey
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
