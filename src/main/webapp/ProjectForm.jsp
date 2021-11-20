<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management</title>
</head>
<body>
	<c:if test="${project == null }">
		<header>
			<h1>Create Project</h1>
		</header>
	</c:if>
	<c:if test="${project != null}">
		<header>
			<h1>Update Project</h1>
		</header>
	</c:if>
	<form action="${formAction}" method="post">
		<fieldset>
			<input name="id" type="hidden" value="${project.id}" readonly>
			<table cellpadding="5" cellspacing="5">
				<tr>
					<td><label for="name">Project Name</label></td>
					<td><input name="name" id="name" type="text" required
						value="${project.name}"
						pattern="^[\s]*([a-zA-Z]{3,60})[\s]*$|^[\s]*([a-zA-Z]{3,30} [a-zA-Z]{2,30}){1,5}[\s]*$">
					</td>
				</tr>
				<tr>
					<td><label for="description"> Description</label></td>
					<td><input name="description" id="description" type="text"
						required pattern="^(.{0,145}([A-Za-z] ?){10}.{0,145})$"
						value="${project.description}"></td>
				</tr>
				<tr>
					<td><label for="manager">Manager Name</label></td>
					<td><input name="manager" id="manager" type="text"
						pattern="^(\s*[a-zA-Z]{3,20}\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20})\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\s*)$"
						required value="${project.manager}"></td>
				</tr>

				<tr>
					<td>Status</td>
					<td><c:choose>
							<c:when test='${project.status == "DEVELOPMENT"}'>
								<input name="status" id="development" type="radio" value="D"
									required checked>
								<label for="development">Development</label>
								<input name="status" id="testing" type="radio" value="T"
									required>
								<label for="testing">Testing</label>
								<input name="status" id="live" type="radio" value="L" required>
								<label for="live">Live</label>
							</c:when>

							<c:when test='${project.status == "TESTING"}'>
								<input name="status" id="development" type="radio" value="D"
									required>
								<label for="development">Development</label>
								<input name="status" id="testing" type="radio" value="T"
									required checked>
								<label for="testing">Testing</label>
								<input name="status" id="live" type="radio" value="L" required>
								<label for="live">Live</label>
							</c:when>

							<c:when test='${project.status == "LIVE"}'>
								<input name="status" id="development" type="radio" value="D"
									required>
								<label for="development">Development</label>
								<input name="status" id="testing" type="radio" value="T"
									required>
								<label for="testing">Testing</label>
								<input name="status" id="live" type="radio" value="L" required
									checked>
								<label for="live">Live</label>
							</c:when>
							<c:otherwise>
								<input name="status" id="development" type="radio" value="D"
									required>
								<label for="development">Development</label>
								<input name="status" id="testing" type="radio" value="T"
									required>
								<label for="testing">Testing</label>
								<input name="status" id="live" type="radio" value="L" required>
								<label for="live">Live</label>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
			<br> <input type="submit" id="submit" value="Submit">
		</fieldset>
	</form>
	<br>
	<a href="index.jsp"><button>Cancel</button></a>

</body>
</html>