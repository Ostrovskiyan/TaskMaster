package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Task;


public interface TaskService {
	Task addTask(Task Task);

	void delete(int id);

	Task editTask(Task Task);

	List<Task> getAll();
}
