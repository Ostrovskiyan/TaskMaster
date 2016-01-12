package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Group;


public interface GroupService {
	Group addGroup(Group group);

	void delete(int id);

	Group editGroup(Group group);

	List<Group> getAll();
}
