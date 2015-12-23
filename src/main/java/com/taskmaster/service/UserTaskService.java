package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.UserTask;

public interface UserTaskService {
	
	UserTask addUserTask(UserTask UserTask);

	void delete(int id);

	UserTask editUserTask(UserTask UserTask);

	List<UserTask> getAll();
	
}
