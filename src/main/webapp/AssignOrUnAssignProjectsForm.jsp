<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Employee Management</title>
</head>
<body>
	<c:if test='${formAction.equals("assignProjects")}'>
		<h1>Assign Projects</h1>
	</c:if>
	<c:if test='${formAction.equals("unAssignProjects")}'>
		<h1>UnAssign Projects</h1>
	</c:if>
	<div class="table-container">
		<table id="assign-unassign">
			<c:choose>
				<c:when test="${!projects.isEmpty()}">
					<form action="${formAction}" method="post">
						<fieldset>
							<th></th>
							<th>Projects</th>
							<c:forEach items="${projects}" var="project">
								<tr>
									<td><input name="selectedProjects" id="${project.id}"
										type="checkbox" value="${project.id}"></td>
									<td><label for="${project.id}">${project.name}</label> <br></td>
								</tr>
							</c:forEach>
							<br>
							<c:if test='${formAction.equals("assignProjects")}'>
								<tr>
									<td><input type="submit" value="Assign"></td>
							</c:if>
							<c:if test='${formAction.equals("unAssignProjects")}'>
								<tr>
									<td><input type="submit" value="UnAssign"></td>
							</c:if>

						</fieldset>
					</form>
					<br>
					<td><a href="viewAllEmployees"><button>Cancel</button></a></td>
				</c:when>
				<c:when
					test='${projects.isEmpty() && formAction.equals("assignProjects")}'>
					<div id="message">
						<span>No Projects Available To Assign!</span>
					</div>
					<tr>
						<td><a href="viewAllEmployees"><button>Go Back</button></a></td>
				</c:when>

				<c:when
					test='${empty projects && formAction.equals("unAssignProjects")}'>
					<div id="message">
						<span>No Projects Available To UnAssign!</span>
					</div>
					<tr>
						<td><a href="viewAllEmployees"><button>Go Back</button></a></td>
				</c:when>
			</c:choose>
			<td><a href="index.jsp"><button>Home Page</button></a></td>
			</tr>
		</table>
	</div>
</body>
</html>