package com.taskmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.taskmaster.entity.Task;

@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	@Query(value = "select DAYOFMONTH(b.end) from Task b where b.end like :yearmonth", nativeQuery=true)
	List<Integer> findDaysInMonthWithTask(@Param("yearmonth") String yearmonth);
	
	@Query(value = "select * from Task b where b.end like :yearmonthday", nativeQuery=true)
	List<Task> findAllTaskInDay(@Param("yearmonthday") String yearmonthday);
	
}