package com.taskmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.UserGroup;

@Transactional
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer>{
	
	@Modifying
	@Query(value = "Delete From usergroup WHERE userid=:userid AND groupid=:groupid", nativeQuery = true)
	void leftFromGroup(@Param("userid") Integer userid, @Param("groupid") Integer groupid);
	
}
