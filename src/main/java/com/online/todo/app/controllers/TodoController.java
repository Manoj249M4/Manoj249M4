package com.online.todo.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.online.todo.app.model.Task;
import com.online.todo.app.model.User;
import com.online.todo.app.service.SecurityService;
import com.online.todo.app.service.TodoService;
import com.online.todo.app.service.UserService;

/**
 * Controller to handle all todo requests
 * 
 * @author Manoj Pandey
 */
@Controller
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    
    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private SecurityService securityService;
    
    private static final String TASKS = "tasks";
    private static final String FILTER = "filter";

    /**
     * GET request for /, /todo or /all
     * @return todo.jsp
     */
    @RequestMapping(value = {"/", "/todo", "/all"}, method = RequestMethod.GET)
    public ModelAndView todo() {
    	logger.debug("GET request received for /, /all or /todo");

    	ModelAndView modelAndView = new ModelAndView();

    	User user = userService.findByUsername(securityService.findLoggedInUsername());

    	List<Task> tasks = todoService.getAllTasks(user.getId());
    	modelAndView.addObject(TASKS, tasks);
    	modelAndView.addObject(FILTER, "all");

    	modelAndView.setViewName("todo");

    	return modelAndView;
    }

    /**
     * GET request for /active
     * @return todo.jsp
     */
    @RequestMapping(value = {"/active"}, method = RequestMethod.GET)
    public ModelAndView active() {
    	logger.debug("GET request received for /active");

    	ModelAndView model = new ModelAndView();

    	User user = userService.findByUsername(securityService.findLoggedInUsername());

    	List<Task> tasks = todoService.getActiveTasks(user.getId());
    	model.addObject(TASKS, tasks);
    	model.addObject(FILTER, "active");
    	model.setViewName("todo");

    	return model;
    }

    /**
     * GET request for /completed
     * @return todo.jsp
     */
    @RequestMapping(value = {"/completed"}, method = RequestMethod.GET)
    public ModelAndView completed() {
    	logger.debug("GET request received for /completed");

    	ModelAndView model = new ModelAndView();

    	User user = userService.findByUsername(securityService.findLoggedInUsername());

    	List<Task> tasks = todoService.getCompletedTasks(user.getId());
    	model.addObject(TASKS, tasks);
    	model.addObject(FILTER, "completed");

    	model.setViewName("todo");

    	return model;
    }
    
    /**
     * POST request for /delete
     * @param id: Task ID
     * @param filter: Filter for UI display
     * @return todo.jsp (filter)
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteTask(@RequestParam Long id, @RequestParam String filter) {
    	logger.debug("POST request for /delete");

    	ModelAndView model = new ModelAndView();

    	todoService.deleteTask(id);

    	model.setViewName("redirect:" + filter);
        return model;
    }

    /**
     * POST request for /insert
     * @param desc: Task description
     * @param filter: Filter for UI display
     * @return todo
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insertTask(@RequestParam String desc, @RequestParam String filter) {
    	logger.debug("POST request for /insert");

    	ModelAndView model = new ModelAndView();

    	todoService.addTask(desc);

    	model.setViewName("redirect:" + filter);
        return model;    
    }

	/**
	 * POST request for /update
	 * @param id: Task ID
	 * @param desc: Task description
	 * @param filter: Filter for UI display
	 * @return todo
	 */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateTask(@RequestParam Long id, @RequestParam String desc, @RequestParam String filter) {
    	logger.debug("POST request for /update");

    	ModelAndView model = new ModelAndView();

    	todoService.updateTask(id, desc);

    	model.setViewName("redirect:" + filter);
        return model;
    }

    /**
     * POST request for /toggleStatus
     * @param id: Task ID
     * @param toggle: Toggle for Task Completed
     * @param filter: Filter for UI display
     * @return todo
     */
    @RequestMapping(value = "/toggleCompleted", method = RequestMethod.POST)
    public ModelAndView toggleCompleted(@RequestParam Long id, @RequestParam(required = false) Boolean toggle, @RequestParam String filter) {
    	logger.debug("POST request for /toggleStatus");

    	ModelAndView model = new ModelAndView();

    	todoService.toggleCompleted(id, toggle);

    	model.setViewName("redirect:" + filter);
        return model;    
    }
    
    /**
     * Removes all completed tasks
     * @param filter: Filter for UI display
     * @return todo.jsp (filter)
     */
    @RequestMapping(value = "/clearCompleted", method = RequestMethod.POST)
    public ModelAndView clearCompleted(@RequestParam String filter) {
    	logger.debug("POST request for /clearCompleted");

    	ModelAndView model = new ModelAndView();

    	todoService.removeCompleted();

    	model.setViewName("redirect:" + filter);
        return model; 
    }
}
