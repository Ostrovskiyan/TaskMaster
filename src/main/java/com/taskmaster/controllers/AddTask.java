package com.taskmaster.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;
import com.taskmaster.entity.Group;
import com.taskmaster.entity.Task;
import com.taskmaster.entity.User;
import com.taskmaster.entity.UserTask;
import com.taskmaster.service.GroupService;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.UserService;
import com.taskmaster.service.UserTaskService;
import com.taskmaster.utils.EmailSender;
import com.taskmaster.utils.GroupMembers;

@Controller
@RequestMapping(value = "/add_task")
public class AddTask {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserTaskService userTaskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		List<Group> groupList = groupService.getAllGroupsOfUser(user.getId());
		List<GroupMembers> groupMembersList = new ArrayList<GroupMembers>();
		for(Group group:groupList){
			List<User> userList = userService.getAllUserOfGroup(group);
			userList.removeIf(new java.util.function.Predicate<User>() {
				@Override
				public boolean test(User t) {
					return t.getId().equals(user.getId());
				};
			});
			groupMembersList.add(new GroupMembers(userList, group));
		}
		model.put("groupMembersList", groupMembersList);
		model.put("user", user);
		return "AddTask";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, Map<String, Object> model) {
		String dateString = request.getParameter("date");
		dateString = dateString.substring(0, 10) + ' ' + dateString.substring(11,13) + ':' + dateString.substring(14);
		Date date;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException();
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String responsibleLogin = request.getParameter("responsibleLogin");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		User responsibleUser = userService.getByLogin(responsibleLogin);
		
		Task task = new Task();
		task.setDescription(description);
		task.setName(name);
		task.setCreator(user);
		task.setEnd(date);
		task.setStatus("progress");
		
		Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
		task.setId(rnd.nextInt(Integer.MAX_VALUE));
		
		UserTask userTask = new UserTask();
		userTask.setTask(task);
		userTask.setUser(responsibleUser);
		userTaskService.addUserTask(userTask);
		
		EmailSender.sendNotificationaAboutNewTask(responsibleUser.getLogin(), name);

		return "redirect:my_tasks";
	}
}