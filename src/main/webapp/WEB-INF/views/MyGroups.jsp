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

<title>MyGroups</title>

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
	<h2>My groups:</h2>
	<table class="table table-striped table-bordered table-hover" style="width:80%">
		<c:forEach items='${listOfGroup}' var='group'>
	   		<tr style="cursor:pointer">
				<c:choose>
				  <c:when test="${group.avatar != null}">
				    <spring:url value="${group.avatar}" var="imageGroup" />
				    <td style="width:100pt; "><img class="avatar-create-group" src="${imageGroup}" /></td>
				  </c:when>
				  <c:otherwise>
				    <td style="width:100pt; "><img class="avatar-create-group" src="/taskmaster/resources/grouplogo1.png" /></td>
				  </c:otherwise>
				</c:choose>
				<td>
					${group.name}
					<form action="left_from_group" style="float:right" method="post">
						<input type="hidden" name="group_id" value="${group.id}" />
						<input type="submit" class="btn btn-success" value="Left from group"/>
					</form>
				</td>
			</tr>
	   	</c:forEach>
	</table>
	<br/>
	<h2>Other groups:</h2>
	<form class="form-inline">
		<div class="form-group">
			<input name="group_name" style="width:300pt" type="text" class="form-control" id="exampleInputName2" placeholder="Group name">
		</div>
		<button style="width:150pt; margin-right:100px" class="btn btn-success">Find</button>
		<a href="<c:url value='/create_group' />" style="width:150pt" class="btn btn-success">Create new group</a>
	</form>
	<br/>
	<h2>Find result:</h2>
	<table class="table table-striped table-bordered table-hover" style="width:80%">
		<c:forEach items='${foundGroups}' var='group'>
			<tr style="cursor:pointer">
				<c:choose>
				  <c:when test="${group.avatar != null}">
				    <spring:url value="${group.avatar}" var="imageGroup" />
				    <td style="width:100pt; "><img class="avatar-create-group" src="${imageGroup}" /></td>
				  </c:when>
				  <c:otherwise>
				    <td style="width:100pt; "><img class="avatar-create-group" src="/taskmaster/resources/grouplogo1.png" /></td>
				  </c:otherwise>
				</c:choose>
				<td>
					${group.name}
					<form action="join_to_group" style="float:right" method="post">
						<input type="hidden" name="group_id" value="${group.id}" />
						<input type="submit" class="btn btn-success" value="Join to group"/>
					</form>
					
				</td>
			</tr>
	   	</c:forEach>
	</table>

 </div>

</body>
</html>