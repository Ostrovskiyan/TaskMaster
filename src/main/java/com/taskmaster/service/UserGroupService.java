package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Group;
import com.taskmaster.entity.User;
import com.taskmaster.entity.UserGroup;

public interface UserGroupService {
	UserGroup addUserGroup(UserGroup userGroup);

	void delete(int id);

	UserGroup editUserGroup(UserGroup userGroup);

	List<UserGroup> getAll();
	
	void userLeftGroup(User user, Group group);
}
