package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.UserGroup;

public interface UserGroupService {
	UserGroup addUserGroup(UserGroup UserGroup);

	void delete(int id);

	UserGroup editUserGroup(UserGroup UserGroup);

	List<UserGroup> getAll();
}
