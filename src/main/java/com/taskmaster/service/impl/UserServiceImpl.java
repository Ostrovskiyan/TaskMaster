package com.taskmaster.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Group;
import com.taskmaster.entity.User;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.secure.Sha512Encoder;
import com.taskmaster.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = false)
	public User addUser(User user) {
		user.setPassword(new Sha512Encoder().encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

	public User getByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	public User editUser(User User) {
		return userRepository.saveAndFlush(User);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> getAllUserOfGroup(Group group) {
		return userRepository.findAllUsersInGroup(group.getId());
	}

}
