package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Task;


public interface TaskService {
	Task addTask(Task task);

	void delete(int id);

	Task editTask(Task task);

	List<Task> getAll();
	
	Task getById(Integer id);
	
	List<Task> getAllTasksInDay(String regular, Integer userid);
	
	List<Task> getAllTasksCreatedInDay(String regular, Integer userid);
	
	List<Integer> getDaysInMonthWithTask(String regular, Integer userid);
}
