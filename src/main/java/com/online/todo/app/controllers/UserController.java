package com.online.todo.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.online.todo.app.model.User;
import com.online.todo.app.service.SecurityService;
import com.online.todo.app.service.UserService;
import com.online.todo.app.validator.UserValidator;

/**
 * Controller to handle all user requests
 * 
 * @author Manoj Pandey
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    private static final String MESSAGE_FOR_LOGOUT = "You have been successfully logged out";
    private static final String ERROR_LOGIN = "Your password and username are incorrect.";

   
    /**
     * @param userForm: Submit information on form
     * @param bindingResult:Check for validation 
     * @return todo
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    	logger.debug("POST request for /registration");

    	ModelAndView model = new ModelAndView();

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	logger.debug("user form validation failed!");

        	model.setViewName("registration");
        }
        else {
        	logger.debug("user form validated sucessfully");

        	userService.save(userForm);
        	//securityService.autologin(userForm.getUsername(), userForm.getConfirmPassword());
        	model.setViewName("redirect:/todo");
        }

        return model;
    }
    
    /**
     * GET request for /registration
     * @return registration
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
    	logger.debug("GET request for /registration");

        ModelAndView model = new ModelAndView();

        model.addObject("userForm", new User());
        model.setViewName("registration");

        return model;
    }


    /**
     * @param error: Check for validation
     * @param logout: check for logout 
     * @return login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String error, String logout) {
    	logger.debug("GET request for /login");

    	ModelAndView model = new ModelAndView();

        if (error != null)
        	model.addObject("error", ERROR_LOGIN);

        if (logout != null)
        	model.addObject("message", MESSAGE_FOR_LOGOUT);

        model.setViewName("login");

        return model;
    }
}
