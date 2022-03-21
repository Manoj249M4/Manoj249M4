package com.online.todo.app.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.online.todo.app.model.User;
import com.online.todo.app.service.UserService;

/**
 * Validator for User for logging in and registration
 * 
 * @author Manoj Pandey
 */
@Component
public class UserValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		logger.debug("validating user");

		User user = (User) o;

		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "username.form.duplicate");
		}

		if (!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "confirmPassword.form.diff");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 4 || user.getUsername().length() > 20) {
			errors.rejectValue("username", "username.form.size");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 6 || user.getPassword().length() > 26) {
			errors.rejectValue("password", "password.form.size");
		}

	}
}
