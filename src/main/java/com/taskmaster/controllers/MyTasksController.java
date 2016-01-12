package com.taskmaster.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.entity.User;
import com.taskmaster.service.TaskService;
import com.taskmaster.service.UserService;

@Controller
@RequestMapping(value = "/my_tasks")
public class MyTasksController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		
		String dateString = request.getParameter("date");
		Date date;
		if(dateString == null || dateString.equals("")){
			date = new Date();
		} else {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			try {
				date = format.parse(dateString);
			} catch (ParseException e) {
				throw new RuntimeException();
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		temp.set(Calendar.DAY_OF_MONTH, 1);
		int dayOfWeek = temp.get(Calendar.DAY_OF_WEEK);
		dayOfWeek = (dayOfWeek!=1)?dayOfWeek - 1:7;
		
		model.put("dayOfWeek", dayOfWeek);
		model.put("days", days);
		model.put("now", calendar.getTime());
		model.put("daysWithTask", getDaysWithTask(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, user));
		return "MyTasks";
	}
	
	private List<Integer> getDaysWithTask(Integer year, Integer month, User user){
		if(month > 9){
			return taskService.getDaysInMonthWithTask(year.toString() + "-" + month.toString() + "%", user.getId());
		} else {
			return taskService.getDaysInMonthWithTask(year.toString() + "-0" + month.toString() + "%", user.getId());
		}
	}
}