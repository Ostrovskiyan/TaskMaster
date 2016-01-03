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

import com.taskmaster.entity.Task;
import com.taskmaster.entity.User;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.UserService;
import com.taskmaster.utils.EmailSender;

@Controller
@RequestMapping(value = "/add_task")
public class AddTask {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		model.put("day", request.getParameter("day"));
		return "AddTask";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, Map<String, Object> model) {
		String dateString = request.getParameter("date");
		Date date;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException();
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		
		Task task = new Task();
		task.setDescription(description);
		task.setName(name);
		task.setCreator(user);
		task.setEnd(date);
		
		taskService.addTask(task);
		
		EmailSender.sendNotificationaAboutNewTask(user.getLogin(), name);
		
		return "redirect:my_tasks";
	}
}