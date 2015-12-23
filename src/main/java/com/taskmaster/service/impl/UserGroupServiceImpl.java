package com.taskmaster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.UserGroup;
import com.taskmaster.repository.UserGroupRepository;
import com.taskmaster.service.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Transactional(readOnly = false)
	public UserGroup addUserGroup(UserGroup userGroup) {
		userGroup.setId(-1);
		return userGroupRepository.saveAndFlush(userGroup);
	}

	public void delete(int id) {
		userGroupRepository.delete(id);

	}

	public UserGroup editUserGroup(UserGroup userGroup) {
		return userGroupRepository.saveAndFlush(userGroup);
	}

	public List<UserGroup> getAll() {
		return userGroupRepository.findAll();
	}


}
