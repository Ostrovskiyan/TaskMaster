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
<spring:url value="/resources/core/js/jquery-2.1.4.js" var="jquery" />

<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />

<script src="${jquery}" type="text/javascript"></script>

<script src="${bootstrapJs}"></script>

<style type="text/css">
	body {
  		padding-top: 40px;
 		 background-color: #eee;
	}
</style>

<title>Add task</title>
<style type="text/css">
div.inline { float:left; }
.clearBoth { clear:both; }
</style>
<script type="text/javascript">
	var lastSelectedSelect;
	var userLogin;
	var userName;
	
	function changeResponsible(name){
		document.getElementById("responsible").innerHTML = name;
	}
	function groupChanged(){
		var groupsSelect = document.getElementById("groups");
		lastSelectedSelect.style.display = "none";
		lastSelectedSelect = document.getElementById(groupsSelect.options[groupsSelect.selectedIndex].value + "Select");
		
		lastSelectedSelect.style.display = "block";
	}
	function responsibleChanged(){
		document.getElementById("responsible").innerHTML = lastSelectedSelect.options[lastSelectedSelect.selectedIndex].innerHTML;
		document.getElementById("responsibleLogin").value = lastSelectedSelect.options[lastSelectedSelect.selectedIndex].value;
	}
	function makeMeResponsible(){
		document.getElementById("responsible").innerHTML = userName;
		document.getElementById("responsibleLogin").value = userLogin;
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
				<input name="date" type="datetime-local"/>
			</h4>
			<h4><b>Responsible: </b><span id="responsible">${user.name}<span></h4>
			<input id="responsibleLogin" name="responsibleLogin" type="hidden" value=${user.login}/>
			<script type="text/javascript">userLogin = ${user.login}; userName = ${user.name};</script>
			
			  <!-- Trigger the modal with a button -->
			  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Change responsible</button>
			
			  <!-- Modal -->
			  <div class="modal fade" id="myModal" role="dialog">
			    <div class="modal-dialog modal-lg">
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">Select responsible</h4>
			        </div>
			        <div class="modal-body">
			          <p>
			          	<div class="form-group">
			          		<label>Your groups:</label>
				          	<select id="groups" class = "form-control" id="dateSelect" onchange = "groupChanged()">
				          		<c:forEach items="${groupMembersList}" var="groupMembers">
				          			<option value="${groupMembers.group.name}" >${groupMembers.group.name}</option>
				          		</c:forEach>
				          	</select>
				        </div>
			          	<c:set var="flag" value="false"></c:set>
			          	<div class="form-group">
			          		<label>Members of group:</label>
				          	<c:forEach items="${groupMembersList}" var="groupMembers">
					          		<select id='${groupMembers.group.name}Select' class = "form-control" id="dateSelect" onchange = "responsibleChanged()" 
					          			<c:if test="${flag}">
					          				style="display:none"
					          			</c:if>
					          			>
						          		<c:forEach items="${groupMembers.userList}" var="userInGroup">
						          			<option value="${userInGroup.login}">${userInGroup.name}</option>
						          		</c:forEach>
						          		<c:if test="${!flag}">
						          			<script type="text/javascript">
						          				lastSelectedSelect = document.getElementById('${groupMembers.group.name}Select');
											</script>
										</c:if>
					          		</select>
					          		<c:set var="flag" value="true"></c:set>
				          	</c:forEach>
			          	</div>
			          </p>
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