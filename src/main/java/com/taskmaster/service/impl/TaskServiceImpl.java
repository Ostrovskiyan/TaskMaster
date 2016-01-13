package com.taskmaster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Task;
import com.taskmaster.repository.TaskRepository;
import com.taskmaster.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Transactional(readOnly = false)
	public Task addTask(Task task) {
		task.setId(-1);
		return taskRepository.saveAndFlush(task);
	}

	public void delete(int id) {
		taskRepository.delete(id);
	}

	public Task editTask(Task task) {
		return taskRepository.saveAndFlush(task);
	}

	public List<Task> getAll() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> getAllTasksInDay(String regular, Integer userid) {
		return taskRepository.findAllTaskInDay(regular, userid);
	}

	@Override
	public List<Integer> getDaysInMonthWithTask(String regular, Integer userid) {
		return taskRepository.findDaysInMonthWithTask(regular, userid);
	}

	@Override
	public List<Task> getAllTasksCreatedInDay(String regular, Integer userid) {
		return taskRepository.findAllTaskCreatedInDay(regular, userid);
	}

	@Override
	public Task getById(Integer id) {
		return taskRepository.findById(id);
	}

}
