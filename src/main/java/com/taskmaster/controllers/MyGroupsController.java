package com.taskmaster.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.Group;
import com.taskmaster.entity.User;
import com.taskmaster.service.GroupService;
import com.taskmaster.service.UserService;

@Controller
@RequestMapping(value = "/my_groups")
public class MyGroupsController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		
		List<Group> listOfGroup = groupService.getAllGroupsOfUser(user.getId());
		model.put("listOfGroup", listOfGroup);
		
		String partOfGroupName = request.getParameter("group_name");
		if(partOfGroupName != null){
			model.put("foundGroups", groupService.getSimilarGroups(partOfGroupName, user.getId()));
		}
		
		return "MyGroups";
	}
}