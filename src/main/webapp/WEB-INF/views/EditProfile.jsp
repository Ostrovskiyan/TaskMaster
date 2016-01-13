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
<spring:url value="/resources/core/js/jquery-2.1.4.js" var="jquery" />
<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<link href="${styleCss}" rel="stylesheet" type="text/css" />

<title>Edit profile</title>

<script src="${jquery}" type="text/javascript"></script>
<script type="text/javascript">
function readURL(input) {
	if (input.files && input.files[0]) {
		if(input.files[0].size < 10485760){
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#user_avatar').attr('src', e.target.result)
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
<div class = "container info">
	<form method="POST" enctype="multipart/form-data">
		<div class="col-md-4">
			<c:choose>
			  <c:when test="${user.avatar != null}">
			    <spring:url value="${user.avatar}" var="imageUser" />
			    <img id="user_avatar" class="avatar-edit-profile" border="4" src = "${imageUser}" />
			  </c:when>
			  <c:otherwise>
			    <img id="user_avatar" class="avatar-edit-profile" border="4" src = "/taskmaster/resources/core/images/ava.jpg" />
			  </c:otherwise>
			</c:choose>
			<br />
			<br />	
           	<input id="input_child_avatar" type="file" name="avatar" accept="image/jpeg,image/png" onchange="readURL(this);"/>
		</div>
		<div class="col-md-8">
			<div class="form-group">
				<label>My name:</label>
	            <input name="name" type="text" class="form-control input-addtask" value="${user.name}"  placeholder="My name" requiered/>
	        </div>
	        <div class="form-group">
	        	<label>Skype:</label>
	            <input name="skype" type="text" class="form-control input-addtask" value="${user.skype}" placeholder="Skype" requiered/>
	        </div>
	        <div class="form-group">
	        	<label>Phone:</label>
	            <input name="phone" type="text" class="form-control input-addtask" value="${user.phone}" placeholder="Mobile Phone" requiered/>
	        </div>
	        <div class="form-group">
	        	<label>About me:</label>
				<textarea class="form-control input-addtask" name="about_me" rows="6" cols="50" placeholder="About me" requiered>${user.aboutMe}</textarea>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary input-signin" value="Save changes"/>
			</div>
		</div>
	</form>
</div>
</body>
</html>