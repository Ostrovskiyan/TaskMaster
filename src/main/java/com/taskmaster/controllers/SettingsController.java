package com.taskmaster.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/settings")
public class SettingsController {
	@RequestMapping(method = RequestMethod.GET)
	public String viewLogin(HttpServletRequest request, Map<String, Object> model) {
		return "Settings";
	}
}