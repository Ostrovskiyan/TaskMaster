package com.taskmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Task;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	@Query(value = "select DAYOFMONTH(b.end) from Task b, UserTask ut where b.end like :yearmonth AND (ut.userid = :userid AND ut.taskid=b.id OR b.creator=:userid)", nativeQuery=true)
	List<Integer> findDaysInMonthWithTask(@Param("yearmonth") String yearmonth, @Param("userid") Integer userid);
	
	@Query(value = "select b.* from Task b, UserTask ut where b.end like :yearmonthday AND ut.userid = :userid AND ut.taskid=b.id", nativeQuery=true)
	List<Task> findAllTaskInDay(@Param("yearmonthday") String yearmonthday, @Param("userid") Integer userid);
	
	@Query(value = "select b.* from Task b where b.end like :yearmonthday AND b.creator = :userid", nativeQuery=true)
	List<Task> findAllTaskCreatedInDay(@Param("yearmonthday") String yearmonthday, @Param("userid") Integer userid);
	
	@Query(value = "select b.* from Task b where b.id=:id", nativeQuery=true)
	Task findById(@Param("id") Integer id);
}