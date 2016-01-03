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

<link rel="stylesheet" href="/taskmaster/resources/core/css/bootstrap-responsive.css">
<link rel="stylesheet" href="/taskmaster/resources/core/css/calendar.css">

<title>My tasks</title>
<script type="text/javascript">
	function toMonth(year, month){
		if(year == 0 && month == 0){
			date = "";
		} else {
			//alert(month);
			month+=2;
			year += 1900;
			if(month == 13){
				month = 1;
				year++;
			} else if(month == 0){
				month = 12;
				year--;
			}
			if(month < 10){
				month = "0" + month;
			}
			date = year + "-" + month + "-00"; 
		}
		document.location = "http://localhost:8080/taskmaster/my_tasks?date=" + date;
	}
	function taskOfDay(year, month, day){
		month+=1;
		year += 1900;
		if(month < 10){
			month = "0" + month;
		}
		if(day < 10){
			day = "0" + day;
		}
		date = year + "-" + month + "-" + day;
		document.location = "http://localhost:8080/taskmaster/taskOfDay?date=" + date;
	}
</script>


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
<div class="container info">
	<div>
		${now.year + 1900}:${now.month + 1}
	</div>
	<div class="btn-group">
		<button type="button" class="btn btn-primary" onClick="toMonth(${now.year},${now.month-1})">Prev.</button>
		<button type="button" class="btn btn-primary" onClick="toMonth(0,0)">Today</button>
		<button type="button" class="btn btn-primary" onClick="toMonth(${now.year},${now.month+1})">Next</button>
	</div>	
	<table class='table table-bordered table-striped my-table'>
		<tr>
			<td>Mon</td>
			<td>Tue</td>
			<td>Wed</td>
			<td>Thu</td>
			<td>Fri</td>
			<td>Sat</td>
			<td>Sun</td>
		</tr>
		<c:set var="k" value="${1}"/>

		<c:set var="nownow" value="<%=new java.util.Date()%>" />
		<c:forEach var="i" begin="1" end="5">
	   		<tr>
	   		<c:forEach var="i" begin="1" end="7">
	   			<c:choose>
	   				<c:when test="${dayOfWeek==1 && k <= days}">
	   					<td class="day 
	   						<c:if test="${k==nownow.date && now.year==nownow.year && now.month==nownow.month}">
	   							today
	   						</c:if>
	   						<c:forEach items='${daysWithTask}' var='day'>
	   							<c:if test="${k==day}">
	   								consist_task
	   							</c:if>
	   						</c:forEach>
	   						"
	   					onClick="taskOfDay(${now.year}, ${now.month}, ${k})">${k}</td>
	   					<c:set var="k" value="${k + 1}" />
  					</c:when>
  					<c:when test="${dayOfWeek > 1}">
  						<td></td>
  						<c:set var="dayOfWeek" value="${dayOfWeek-1}" />
  					</c:when>
  					<c:otherwise>
						<td></td>
					</c:otherwise>
	   			</c:choose>
	   		</c:forEach>
	   		</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>