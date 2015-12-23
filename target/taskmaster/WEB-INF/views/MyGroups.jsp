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
	<h1>Groups</h1>
	<br/>

	<form class="form-inline">
		<div class="form-group">
			<input style="width:400pt; position:absolute; left:11%" type="text" class="form-control" id="exampleInputName2" placeholder="Group name">
		</div>
		<button style="position:absolute; left:72%; top:15%; width:150pt" type="submit" class="btn btn-success">Create new group</button>
	</form>
	<br/>
	<br/>
	<br/>
	<h2>My groups:</h2>
	<table class="table table-striped table-bordered table-hover" style="width:80%">
		<tr style="cursor:pointer">
			<td style="width:100pt; "><img src="/taskmaster/resources/grouplogo1.png" /></td>
			<td>Group1</td>
		</tr>
		<tr style="cursor:pointer">
			<td><img src="/taskmaster/resources/grouplogo1.png" /></td>
			<td>Group2</td>
		</tr>
		<tr style="cursor:pointer">
			<td><img src="/taskmaster/resources/grouplogo1.png" /></td>
			<td>Group3</td>
		</tr>
	</table>
	<br/>
	<h2>Other groups:</h2>
	<table class="table table-striped table-bordered table-hover" style="width:80%">
		<tr style="cursor:pointer">
			<td><img src="/taskmaster/resources/grouplogo1.png" /></td>
			<td>Group2</td>
			<td><a href="#" class="btn btn-success" role="button" style="width:100%;height:100%"/>Join to group</a></td>
		</tr>
		<tr style="cursor:pointer">
			<td><img src="/taskmaster/resources/grouplogo1.png" /></td>
			<td>Group3</td>
			<td><a href="#" class="btn btn-success" role="button" style="width:100%;height:100%"/>Send request</a></td>
		</tr>
	</table>

 </div>

</body>
</html>