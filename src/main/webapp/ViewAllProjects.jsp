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
	<h1>Manage Projects</h1>
	<a href="index.jsp"><button>
			Home Page
		</button></a>
	<a href="viewAllEmployees"><button>
			Manage Employees
		</button></a>
	<c:choose>
		<c:when test="${!projects.isEmpty()}">
			<a href="deleteAllProjects"><button>
					Delete All
				</button></a>
			<div class="table-container">
			<table>
				<tr>
					<th>Project ID</th>
					<th>Project Name</th>
					<th>Description</th>
					<th>Project Manager</th>
					<th>Status</th>
					<th>Employees</th>
				</tr>
				<c:forEach items="${projects}" var="project">
					<tr>
						<td>${project.id}</td>
						<td>${project.name}</td>
						<td>${project.description}</td>
						<td>${project.manager}
						<td>${project.status}</td>
						<td>
							<table>
								<th>ID</th>
								<th>Name</th>
								<c:forEach items="${project.employees}" var="employee">
									<tr>
										<td>${employee.id}</td>
										<td>${employee.name}</td>
									</tr>
								</c:forEach>
							</table>
						</td>

						<td><div class="dropdown">
						<button class="dropdown-btn">Manage</button>
								<div class="dropdown-content">
						<a href="updateProjectForm?id=${project.id}">
									Update
								</a><a href="deleteProject?id=${project.id}">
									Delete
								</a>
						 <a href="assignEmployeesForm?id=${project.id}">
									Assign Employees
								</a>  
								<a href="unAssignEmployeesForm?id=${project.id}">
									UnAssingn Employees
								</a></div></div>
					</tr>
				</c:forEach>
			</table></div>
		</c:when>
		<c:otherwise>
			<h3>No Projects Found!</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>