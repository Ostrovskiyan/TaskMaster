package com.taskmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Group;

@Transactional
public interface GroupRepository extends JpaRepository<Group, Integer> {	
	
	@Query(value = "select b.* from task_group b, usergroup ug where ug.groupid=b.id AND ug.userid=:userid", nativeQuery=true)
	List<Group> findAllTasksOfUser(@Param("userid") Integer userid);
	
	@Query(value = "select b.* from task_group b where b.name like :partOfGroupName AND b.id NOT IN (Select groupid from usergroup where userid = :userid)", nativeQuery=true)
	List<Group> findSimilarGroups(@Param("partOfGroupName") String partOfGroupName, @Param("userid") Integer userid);
	
	@Query(value = "select b.* from task_group b where b.id=:id", nativeQuery=true)
	Group findById(@Param("id") Integer id);
}