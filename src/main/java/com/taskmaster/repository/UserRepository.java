package com.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select b from User b where b.login=:login")
	User findByLogin(@Param("login") String login);
}
