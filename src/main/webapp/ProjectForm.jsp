<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Employee Management</title>
</head>
<body>
	<c:if test='${formAction.equals("createProject")}'>
		<header>
			<h1>Create Project</h1>
		</header>
	</c:if>
	<c:if test='${formAction.equals("updateProject")}'>
		<header>
			<h1>Update Project</h1>
		</header>
	</c:if>
	<spring:form action="${formAction}" method="post"
		modelAttribute="project">
		<fieldset class="form-border">
			<div class="form-container">
				<spring:hidden path="id" />

				<div class="row">
					<div class="col-25">
						<spring:label path="name">Project Name</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="name" type="text" required="required"
							pattern="^[\s]*([a-zA-Z]{3,60})[\s]*$|^[\s]*([a-zA-Z]{3,30} [a-zA-Z]{2,30}){1,5}[\s]*$" />
					</div>
				</div>

				<div class="row">
					<div class="col-25">
						<spring:label path="description"> Description</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="description" type="text" required="required"
							pattern="^(.{0,145}([A-Za-z] ?){10}.{0,145})$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="manager">Manager Name</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="manager" type="text"
							pattern="^(\s*[a-zA-Z]{3,20}\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20})\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\s*)$"
							required="required" />
					</div>
				</div>

				<div class="row">
					<div class="col-25">
						<spring:label path="status">Status</spring:label>
					</div>
					<div class="col-75, radio-btn">
						<spring:radiobutton path="status" value="D" required="required"
							checked="${(project.status == 'DEVELOPMENT') ? 'checked': ''}" />
						<spring:label path="status">Development</spring:label>
						<spring:radiobutton path="status" value="T" required="required"
							checked="${(project.status == 'TESTING') ? 'checked': ''}" />
						<spring:label path="status">Testing</spring:label>
						<spring:radiobutton path="status" value="L" required="required"
							checked="${(project.status == 'LIVE') ? 'checked': ''}" />
						<spring:label path="status">Live</spring:label>
					</div>
				</div>
				<div class="center">
					<input type="submit" id="submit" value="Submit">
				</div>
			</div>
		</fieldset>
	</spring:form>
	<br>
	<a href="index.jsp"><button class="cancel-btn">Cancel</button></a>

</body>
</html>