package com.taskmaster.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.User;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.UserService;

@Controller
@RequestMapping(value = "/taskOfDay")
public class TaskOfDayController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		
		String dateString = request.getParameter("date");
		Date date;
		if(dateString == null || dateString.equals("")){
			date = new Date();
		} else {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			try {
				date = format.parse(dateString);
			} catch (ParseException e) {
				throw new RuntimeException();
			}
		}
		model.put("date", date);
		model.put("tasks", taskService.getAllTasksInDay(dateString + '%', user.getId()));
		model.put("createdTasks", taskService.getAllTasksCreatedInDay(dateString + '%', user.getId()));
		return "TaskOfDay";
	}
}