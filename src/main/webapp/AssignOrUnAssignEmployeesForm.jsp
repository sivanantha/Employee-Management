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
	<c:if test='${formAction.equals("assignEmployees")}'>
		<h1>Assign Employees</h1>
	</c:if>
	<c:if test='${formAction.equals("unAssignEmployees")}'>
		<h1>UnAssign Employees</h1>
	</c:if>
	<div class="table-container">
		<table id="assign-unassign">
			<c:choose>
				<c:when test="${not empty employees}">
					<form action="${formAction}" method="post">
						<fieldset>
							<th></th>
							<th>ID</th>
							<th>Employee</th>

							<c:forEach items="${employees}" var="employee">
								<tr>
									<td><input name="selectedEmployees" id="${employee.id}"
										type="checkbox" value="${employee.id}"></td>
									<td>${employee.id}</td>
									<td><label for="${employee.id}">${employee.name}</label> <br></td>
								</tr>
							</c:forEach>
							<br>
							<c:if test='${formAction.equals("assignEmployees")}'>
								<tr>
									<td><input type="submit" value="Assign"></td>
							</c:if>
							<c:if test='${formAction.equals("unAssignEmployees")}'>
								<tr>
									<td><input type="submit" value="UnAssign"></td>
							</c:if>

						</fieldset>
					</form>
					<br>
					<td><a href="viewAllProjects"><button>Cancel</button></a></td>
				</c:when>
				<c:when
					test='${empty employees && formAction.equals("assignEmployees")}'>
					<div id="message">
						<span>No Employees Available To Assign!</span>
					</div>
					<tr>
						<td><a href="viewAllProjects"><button>Go Back</button></a></td>
				</c:when>

				<c:when
					test='${empty employees && formAction.equals("unAssignEmployees")}'>
					<div id="message">
						<span>No Employees Available To UnAssign!</span>
					</div>
					<tr>
						<td><a href="viewAllProjects"><button>Go Back</button></a></td>
				</c:when>
			</c:choose>
			<td><a href="index.jsp"><button>Home Page</button></a></td>
			</tr>
		</table>
	</div>
</body>
</html>