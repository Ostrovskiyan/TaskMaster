package com.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.UserGroup;

@Transactional
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer>{
}
