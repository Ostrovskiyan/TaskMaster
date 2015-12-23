package com.taskmaster.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.User;
import com.taskmaster.secure.PasswordValidator;
import com.taskmaster.service.UserService;
import com.taskmaster.utils.EmailSender;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		User userForm = new User();
		model.put("userForm", userForm);
		return "Registration";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(HttpServletRequest request, @ModelAttribute("userForm") User user,
	Map<String, Object> model) {
		boolean valid = EmailValidator.getInstance().isValid(user.getLogin());
		if (!valid) {
			request.setAttribute("error_message", "Email is invalid");
			return "Registration";
		}
		if (userService.getByLogin(user.getLogin()) != null) {
			request.setAttribute("error_message", "This login is already used");
			return "Registration";
		}
		if (user.getName().length() > 16) {
			request.setAttribute("error_message", "Name is too long");
			return "Registration";
		}
		if (user.getPassword().length() < 8 || !new PasswordValidator().validate(user.getPassword())) {
			request.setAttribute("error_message", "Password is too simple");
			return "Registration";
		}
		
		String hash = RandomStringUtils.randomAlphanumeric(10);
		user.setActivated(false);
		user.setHash(hash);
		userService.addUser(user);
		EmailSender.sendRegistrationConfirmEmail(user, getAccountActivatedAddress(request), hash);
		
		model.put("message", "To complete your registration, follow the link which we sent to your email.");
		return "RegistrationSuccess";
	}
	
	private String getAccountActivatedAddress(HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme()).append("://").append(request.getServerName()).append(':').append(request.getServerPort()).append(request.getRequestURI()).append("/activation");
		return sb.toString();
	}
}