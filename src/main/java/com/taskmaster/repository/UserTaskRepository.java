package com.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.UserTask;

@Transactional
public interface UserTaskRepository extends JpaRepository<UserTask, Integer> {
}
