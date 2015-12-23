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
<div class = "container info" align="center">
	<h1>Settings of TaskMaster</h1>
	<br/>
	<div class = "desc">
		<p class = "text-centered">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?.</p>
		</div>
</div>
</body>
</html>