package com.taskmaster.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.User;
import com.taskmaster.service.UserService;
import com.taskmaster.utils.EmailSender;

@Controller
@RequestMapping(value = "/restore_password")
public class RestorePasswordController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String restorePassword(HttpServletRequest request, Map<String, Object> model) {
		String login = request.getParameter("email");
		if (login == null) {
			return "RestorePassword";
		}
		User user = userService.getByLogin(login);
		if (user == null) {
			model.put("email_not_exist", "No account found with that email address.");
			return "RestorePassword";
		}

		String confirmingCode = RandomStringUtils.randomAlphanumeric(10);
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme()).append("://").append(request.getServerName()).append(':')
				.append(request.getServerPort())
				.append(request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf('/')));
		EmailSender.sendRestorePasswordConfirmingCode(user.getLogin(), confirmingCode, sb.toString());

		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("confirmingCode", confirmingCode);

		return "redirect:restore_password_confirming";
	}

}
