<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/style.css" var="styleCss" />
<spring:url value="/resources/core/js/bootstrap.js" var="bootstrapJs" />

<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />

<title>Create group</title>
</head>
<body>
		<sec:authorize access="isAuthenticated()">
		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container">
		        <ul class="nav navbar-nav">
			        <li><a style="padding:0" href="<c:url value='/' />"><img class = "cover" src = "/taskmaster/resources/core/images/logo.png" /></a></li>
			        <li><a href="<c:url value='/my_page' />" role="button">My Page</a></li>
			        <li><a href="<c:url value='/my_tasks' />" role="button">My Tasks</a></li>
			        <li><a href="<c:url value='/my_groups' />" role="button">My Groups</a></li>
			        <li><a href="<c:url value='/settings' />">Settings</a></li>
		        </ul>
			<div class = "navbar-right">
			<p>
				<sec:authentication property="principal.username" />
			</p>
				<a href="<c:url value="/logout" />" role="button">Sign out</a>
			</div>
		  </div>
		</nav>
		</sec:authorize>
		
		<form method="POST">
			<div class="form-group">
	            <input name="name" type="text" class="form-control input-addtask"  placeholder="Group name" requiered/>
	        </div>
	        <div class="form-group">
			<textarea class="form-control input-addtask" name="description" rows="6" cols="50" placeholder="Group description" requiered></textarea>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary input-signin" value="Create group"/>
			</div>
		</form>
</body>
</html>