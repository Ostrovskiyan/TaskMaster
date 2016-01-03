package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Task;


public interface TaskService {
	Task addTask(Task task);

	void delete(int id);

	Task editTask(Task task);

	List<Task> getAll();
	
	List<Task> getAllTasksInDay(String regular);
	
	List<Integer> getDaysInMonthWithTask(String regular);
}
