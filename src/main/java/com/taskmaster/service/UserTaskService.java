package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.UserTask;

public interface UserTaskService {
	
	UserTask addUserTask(UserTask userTask);

	void delete(int id);

	UserTask editUserTask(UserTask userTask);

	List<UserTask> getAll();
	
}
