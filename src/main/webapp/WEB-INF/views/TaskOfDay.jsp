<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<title>My task on day</title>
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
	<div>
		<h1><fmt:formatDate type="date" value="${date}" /></h1>
	</div>
	<div>
		<h2>Tasks assigned to me:</h2>
		<c:forEach items='${tasks}' var='task'>
	   		<div
		   		<c:choose>
				  <c:when test="${task.status=='done'}">
				  	class="panel panel-success user_desc"
				  </c:when>
				  <c:when test="${task.status=='cancel'}">
				  	class="panel panel-danger user_desc"
				  </c:when>
				  <c:otherwise>
				    class="panel panel-primary user_desc"
				  </c:otherwise>
				</c:choose> 
	   		>
				<div class="panel-heading" align="left"> 
					<h3 class="panel-title">${task.name}</h3>
				</div>
				<div class="panel-body" align="left"> 
					<div style="float:left"> ${task.description}</div>
					<c:if test="${task.status=='progress'}">					     
						<form action="finish_task" style="float:right" method="post">
							<input type="hidden" name="task_id" value="${task.id}" />
							<input type="submit" class="btn btn-success" value="Task is done"/>
						</form>
						<form action="cancel_task" style="margin-left:100%;float:right" method="post">
							<input type="hidden" name="task_id" value="${task.id}" />
							<input type="submit" class="btn btn-danger" value="Cancel task"/>
						</form>
					</c:if>
				</div>
			</div>
	   	</c:forEach>
	</div>
	<div>
		<h2>Tasks created by me:</h2>
		<c:forEach items='${createdTasks}' var='task'>
	   		<div <c:choose>
				  <c:when test="${task.status=='done'}">
				  	class="panel panel-success user_desc"
				  </c:when>
				  <c:when test="${task.status=='cancel'}">
				  	class="panel panel-danger user_desc"
				  </c:when>
				  <c:otherwise>
				    class="panel panel-primary user_desc"
				  </c:otherwise>
				</c:choose>
			>
				<div class="panel-heading" align="left"> <h3 class="panel-title">${task.name}</h3> </div>
				<div class="panel-body" align="left"> 
					<div> ${task.description}</div>
				</div>
			</div>
	   	</c:forEach>
	</div>
	<div>
		<form action="add_task">
			<input type="submit" class="btn btn-success" value="Add task"/>
		</form>
	</div>
</div>
</body>
</html>