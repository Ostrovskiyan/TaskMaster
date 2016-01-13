<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/style.css" var="styleCss" />
<spring:url value="/resources/core/js/bootstrap.js" var="bootstrapJs" />

<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />
<spring:url value="/resources/core/js/jquery-2.1.4.js" var="jquery" />

<title>Create group</title>

<script src="${jquery}" type="text/javascript"></script>
<script type="text/javascript">
function readURL(input) {
	if (input.files && input.files[0]) {
		if(input.files[0].size < 10485760){
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#group_avatar').attr('src', e.target.result)
			};
			reader.readAsDataURL(input.files[0]);
		} else {
			alert("Avatar size must be less then 10 MB");
			input.value = "";
		}
	}
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
			<h2>Create group</h2>
			<br/>
			<form method="POST" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-3">
						<img id="group_avatar" class="avatar-create-group" border="4" src = "/taskmaster/resources/grouplogo1.png" />
						<input id="input_child_avatar" type="file" name="avatar" accept="image/jpeg,image/png" onchange="readURL(this);"/>
					</div>
					<div class="col-md-9">
						<div class="form-group">
							<label>Group name:</label>
			            	<input name="name" type="text" class="form-control input-addtask"  placeholder="Group name" requiered/>
			        	</div>
			        </div>
			    </div>
		        <div class="form-group">
		        	<label>Group description:</label>
					<textarea class="form-control input-addtask" name="description" rows="6" cols="50" placeholder="Group description" requiered></textarea>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary input-signin" value="Create group"/>
				</div>
			</form>
		</div>
</body>
</html>