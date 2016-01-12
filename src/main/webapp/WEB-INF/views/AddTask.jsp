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
<spring:url value="/resources/core/js/bootstrap.js" var="bootstrapJs" />

<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />



<style type="text/css">
	body {
  		padding-top: 40px;
 		 background-color: #eee;
	}
</style>

<title>Registration</title>
<style type="text/css">
div.inline { float:left; }
.clearBoth { clear:both; }
</style>
<script type="text/javascript">
	function changeResponsible(name){
		document.getElementById("responsible").innerHTML = name;
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


		<div class = "container info" align="left" >

		<form action="add_task" method="POST">
			<h2>Add task</h2>
			<br/>
			<h4><b>Date: </b>
				<input name="date" type="date"/>
			</h4>
			<h4><b>Responsible: </b><span id="responsible">Alik Ostrovskiy<span></h4>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Change responsible </button>
			<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
					
						<div class="dropdown inline">
							<button class="btn btn-primary drop_down" type="button" data-toggle="dropdown" onclick="changeResponsible('Alik Ostrovskiy')">Me</button>
						</div>
						<div class="dropdown inline">
							<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Group2
								<span class="caret"></span></button>
								<ul class="dropdown-menu">
									<li><a href="#" onclick="changeResponsible('Olegov Oleg')">Olegov Oleg</a></li>
									<li><a href="#" onclick="changeResponsible('Nikolaev Nikolay')">Nikolaev Nikolay</a></li>
									<li><a href="#" onclick="changeResponsible('Aleksandrov Aleksandr')">Aleksandrov Aleksandr</a></li>
								</ul>
						</div>
						<div class="dropdown inline">
							<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Group3
								<span class="caret"></span></button>
								<ul class="dropdown-menu">
									<li><a href="#">Leonidov Leonid</a></li>
									<li><a href="#">Evgeniev Evgeniy</a></li>
									<li><a href="#">Borisov Boris</a></li>
								</ul>
						</div>
					
					</div>
				</div>
			</div>
				

			<div class="form-group">
	            <input name="name" type="text" class="form-control input-addtask"  placeholder="Task name" requiered/>
	        </div>
	        <div class="form-group">
			<textarea class="form-control input-addtask" name="description" rows="6" cols="50" placeholder="Task description" requiered></textarea>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary input-signin" value="Add task"/>
			</div>
		</form>
	</div>
</body>
</html>