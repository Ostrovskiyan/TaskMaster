package com.taskmaster.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.User;
import com.taskmaster.secure.PasswordValidator;
import com.taskmaster.secure.Sha512Encoder;
import com.taskmaster.service.UserService;

@Controller
@RequestMapping(value = "/restore_password_change_password")
public class RestorePasswordChangePassword {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String viewRestorePasswordChangePassword(HttpServletRequest request, Map<String, Object> model) {
		if (request.getSession().getAttribute("confirmedCode") == null) {
			return "Login";
		}
		return "RestorePassword/RestorePasswordChangePassword";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String confirmNewPassword(HttpServletRequest request, Map<String, Object> model) {
		String newPassword = request.getParameter("new_password");
		String confirmPassword = request.getParameter("confirm_password");
		if (!checkPasswords(newPassword, confirmPassword)) {
			return "RestorePassword/RestorePasswordChangePassword";
		}
		User user = (User) request.getSession().getAttribute("user");
		user.setPassword(new Sha512Encoder().encode(newPassword));
		userService.editUser(user);
		request.getSession().invalidate();
		return "redirect:login?restore_password_successful";
	}

	private boolean checkPasswords(String newPassword, String confirmPassword) {
		if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)
				|| !new PasswordValidator().validate(newPassword)) {
			return false;
		}
		return true;
	}
}
