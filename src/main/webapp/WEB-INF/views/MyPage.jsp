<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/style.css" var="styleCss" />
<spring:url value="/resources/core/css/bootstrap.js" var="bootstrapJs" />
<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />

<title>Registration</title>
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
	<div align="left" class="info avatar_container"><img class="avatar" border="4" src = "/taskmaster/resources/core/images/ava.jpg" /></div>			
	<div class = "container info my_page_info" align="left" >
		<div class="my_name bg-primary">Ostrovskiy Albert</div>
		<br/>
		<div class="panel panel-primary user_desc">
			<div class="panel-heading"> <h3 class="panel-title">Contacts</h3> </div>
			<div class="panel-body"> 
				<div> <b>Email</b> : ostrovskiyan15@gmail.com </div>
				<div> <b>Skype</b> : summerayliko </div>
				<div> <b>Mobile phone</b> : +380909825520 </div>
			</div>
		</div>
		<div class="panel panel-primary user_desc about_me" >
			<div class="panel-heading"> <h3 class="panel-title">About me</h3> </div>
			<div class="panel-body"> 
				<div>Hello,</div>
				<div>My name is Alik. And i think that I am software programmer. =)</div>
			</div>
		</div>
	</div>
</body>
</html>