package com.taskmaster.utils;

import java.util.List;

import com.taskmaster.entity.Group;
import com.taskmaster.entity.User;

public class GroupMembers {
	
	private List<User> userList;
	private Group group;
	
	public GroupMembers(){
		super();
	}
	
	public GroupMembers(List<User> userList, Group group) {
		super();
		this.userList = userList;
		this.group = group;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
}
