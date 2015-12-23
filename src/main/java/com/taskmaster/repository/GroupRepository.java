package com.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Group;

@Transactional
public interface GroupRepository extends JpaRepository<Group, Integer> {	
}
