package com.taskmaster.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.User;
import com.taskmaster.entity.UserGroup;
import com.taskmaster.service.GroupService;
import com.taskmaster.service.UserGroupService;
import com.taskmaster.service.UserService;

@Controller
@RequestMapping(value = "/join_to_group")
public class JoinToGroupController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserGroupService userGroupService;
	
	@Autowired
	GroupService groupService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		Integer groupId = Integer.parseInt(request.getParameter("group_id"));
		UserGroup userGroup = new UserGroup();
		userGroup.setUser(user);
		userGroup.setGroup(groupService.getById(groupId));
		userGroupService.addUserGroup(userGroup);
		return "redirect:my_groups";
	}
}