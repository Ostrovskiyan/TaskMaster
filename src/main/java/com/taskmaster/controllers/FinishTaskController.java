package com.taskmaster.controllers;

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
@RequestMapping(value = "/finish_task")
public class FinishTaskController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		Integer taskId = Integer.parseInt(request.getParameter("task_id"));
		Task task = taskService.getById(taskId);
		task.setStatus("done");
		taskService.editTask(task);
		EmailSender.sendNotificationaAboutTaskDone(task.getCreator().getLogin(), task.getName(), user.getName());
		return "redirect:my_tasks";
	}
}