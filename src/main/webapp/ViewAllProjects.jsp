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
	<h1>Manage Projects</h1>
	<a href="index.jsp"><button>
			<b>Home Page</b>
		</button></a>
	<a href="viewAllEmployees"><button>
			<b>View All Employees</b>
		</button></a>
	<c:choose>
		<c:when test="${!projects.isEmpty()}">
			<a href="deleteAllProjects"><button>
					<b>Delete All</b>
				</button></a>
			<br>
			<br>
			<table align="center" border="2" cellpadding="5" cellspacing="5"
				bordercolor="green">
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
								<th>Employee ID</th>
								<th>Name</th>
								<c:forEach items="${project.employees}" var="employee">
									<tr>
										<td>${employee.id}</td>
										<td>${employee.name}</td>
									</tr>
								</c:forEach>
							</table>
						</td>

						<td><a href="updateProjectForm?id=${project.id}"><button>
									<b>Update</b>
								</button></a><br> <br> <a href="deleteProject?id=${project.id}"><button>
									<b>Delete</b>
								</button></a></td>
						<td><a href="assignEmployeesForm?id=${project.id}"><button>
									<b>Assign Employees</b>
								</button></a> <br> <br> <a
							href="unAssignEmployeesForm?id=${project.id}"><button>
									<b>UnAssingn Employees</b>
								</button></a></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<br>
		</c:when>
		<c:otherwise>
			<h3>No Projects Found!</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>