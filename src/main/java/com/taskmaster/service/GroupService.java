package com.taskmaster.service;

import java.util.List;

import com.taskmaster.entity.Group;


public interface GroupService {
	Group addGroup(Group Group);

	void delete(int id);

	Group editGroup(Group Group);

	List<Group> getAll();
}
