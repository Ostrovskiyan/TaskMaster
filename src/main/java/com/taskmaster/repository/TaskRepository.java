package com.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Task;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
}