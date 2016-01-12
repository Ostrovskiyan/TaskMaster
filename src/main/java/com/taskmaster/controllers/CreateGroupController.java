package com.taskmaster.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.Group;
import com.taskmaster.service.GroupService;
import com.taskmaster.service.UserService;

public class CreateGroupController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, Map<String, Object> model) {
		return "CreateGroup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, Map<String, Object> model) {
		String groupName = request.getParameter("name");
		String groupDescription= request.getParameter("description");
		Group group = new Group();
		group.setDescription(groupDescription);
		group.setName(groupName);
		groupService.addGroup(group);
		return "redirect:my_groups";
	}
	
}
