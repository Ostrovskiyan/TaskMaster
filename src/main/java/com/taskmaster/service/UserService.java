package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.User;

public interface UserService {
	User addUser(User User);

	void delete(int id);

	User getByLogin(String login);
	
	User editUser(User User);

	List<User> getAll();
}
