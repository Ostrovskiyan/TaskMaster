package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Group;
import com.taskmaster.entity.User;

public interface UserService {
	
	User addUser(User user);

	void delete(int id);

	User getByLogin(String login);
	
	User editUser(User user);

	List<User> getAll();
	
	List<User> getAllUserOfGroup(Group group);
}
