package com.taskmaster.controllers;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.taskmaster.entity.Group;
import com.taskmaster.entity.User;
import com.taskmaster.entity.UserGroup;
import com.taskmaster.service.UserGroupService;
import com.taskmaster.service.UserService;

@Controller
@RequestMapping(value = "/create_group")
public class CreateGroupController implements ServletContextAware{
	
	private final String DIRECTORY_CHILD_AVATARS = File.separator + "resources" + File.separator + "core"
			+ File.separator + "images" + File.separator + "group_avatars";

	private ServletContext context;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserGroupService userGroupService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest request, Map<String, Object> model) {
		return "CreateGroup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, Map<String, Object> model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getByLogin(login);
		
		String groupName = null;
		String groupDescription= null;
		FileItem avatar = null;
		Group group = new Group();
		
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (item.isFormField()) {
						switch(item.getFieldName()){
							case "name":
								groupName = item.getString();
								break;
							case "description":
								groupDescription = item.getString();
								break;
						}
					} else if(item.getFieldName().equals("avatar") && item.getSize() > 0) {
						avatar = item;
					}
				}
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}
		}
		
		if (avatar != null) {
			try {
				File directory = new File(
						context.getRealPath("") + DIRECTORY_CHILD_AVATARS + File.separator + groupName);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				String fileExtension = '.' + avatar.getName().split("\\.")[avatar.getName().split("\\.").length - 1];
				String filePath = DIRECTORY_CHILD_AVATARS + File.separator + groupName + File.separator + groupName
						+ fileExtension;
				File oldFile = new File(context.getRealPath("") + filePath);
				oldFile.delete();
				avatar.write(new File(context.getRealPath("") + filePath));
				group.setAvatar(filePath.replace(File.separatorChar, '/'));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		group.setDescription(groupDescription);
		group.setName(groupName);
		
		UserGroup userGroup = new UserGroup();
		userGroup.setGroup(group);
		userGroup.setUser(user);
		userGroupService.addUserGroup(userGroup);
		
		return "redirect:my_groups";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
}
