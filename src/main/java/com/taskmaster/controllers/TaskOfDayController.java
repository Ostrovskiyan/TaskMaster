package com.taskmaster.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taskmaster.service.TaskService;

@Controller
@RequestMapping(value = "/taskOfDay")
public class TaskOfDayController {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
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
		model.put("date", date);
		model.put("tasks", taskService.getAllTasksInDay(dateString + '%'));
		return "TaskOfDay";
	}
}