package com.taskmaster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.UserTask;
import com.taskmaster.repository.UserTaskRepository;
import com.taskmaster.service.UserTaskService;

@Service
public class UserTaskServiceImpl implements UserTaskService {
	@Autowired
	private UserTaskRepository userTaskRepository;
	
	@Transactional(readOnly = false)
	public UserTask addUserTask(UserTask userTask) {
		userTask.setId(-1);
		return userTaskRepository.saveAndFlush(userTask);
	}

	public void delete(int id) {
		userTaskRepository.delete(id);
	}

	public UserTask editUserTask(UserTask userTask) {
		return userTaskRepository.saveAndFlush(userTask);
	}

	public List<UserTask> getAll() {
		return userTaskRepository.findAll();
	}

}
