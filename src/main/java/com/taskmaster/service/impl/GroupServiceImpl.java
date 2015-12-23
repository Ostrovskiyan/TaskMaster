package com.taskmaster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Group;
import com.taskmaster.repository.GroupRepository;
import com.taskmaster.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupRepository groupRepository;
	
	@Transactional(readOnly = false)
	public Group addGroup(Group group) {
		group.setId(-1);
		return groupRepository.saveAndFlush(group);
	}

	public void delete(int id) {
		groupRepository.delete(id);
	}

	public Group editGroup(Group group) {
		return groupRepository.saveAndFlush(group);
	}

	public List<Group> getAll() {
		return groupRepository.findAll();
	}

}
