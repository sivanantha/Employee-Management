<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.time.format.DateTimeFormatter, java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Employee Management</title>
</head>
<body>
	<header>
		<h1>Manage Employees</h1>
	</header>
	<a href="index.jsp"><button>
			Home Page
		</button></a>
	<a href="viewAllProjects"><button>
			Manage Projects
		</button></a>
	<c:choose>
		<c:when test="${!employees.isEmpty()}">
			<a href="deleteAllEmployees"><button>
					Delete All
				</button></a>
			<div class="table-container">
				<table>
					<tr>
						<th>Employee ID</th>
						<th>Name</th>
						<th>Date Of Birth</th>
						<th>Gender</th>
						<th>Mobile Number</th>
						<th>Email</th>
						<th>Salary</th>
						<th>Date Of Joining</th>
						<th>Addresses</th>
						<th>Projects</th>
					</tr>
					<c:forEach items="${employees}" var="employee">
						<c:set var="dateOfBirth" scope="request"
							value='${employee.dateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}'>
						</c:set>
						<c:set var="dateOfJoining" scope="request"
							value='${employee.dateOfJoining.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}'>
						</c:set>
						<tr>
							<td>${employee.id}</td>
							<td>${employee.name}</td>
							<td>${dateOfBirth}</td>
							<td>${employee.gender}
							<td>${employee.mobileNumber}</td>
							<td>${employee.email}</td>
							<td>${employee.salary}</td>
							<td>${dateOfJoining}</td>
							
								<td><c:forEach items="${employee.addresses}" var="address">
								<address>
						${address.doorNumber},${address.street},<br>
						${address.locality},${address.city},<br>
						${address.state},${address.state},<br>
						${address.pinCode}.
						</address><br><br>
					</c:forEach></td>
							
							
							<td>
								<table id="project-inner-table">
									<c:forEach items="${employee.projects}" var="project">
										<tr><td>${project.name}</td></tr>
									</c:forEach>
								</table>
							</td>
							<td><div class="dropdown">
							<button class="dropdown-btn">Manage</button>
								<div class="dropdown-content">
						<a href="updateEmployeeForm?id=${employee.id}">
										Update</a> <a href="deleteEmployee?id=${employee.id}">
										Delete</a> <a href="assignProjectsForm?id=${employee.id}">
										Assign Projects</a> 
							<a href="unAssignProjectsForm?id=${employee.id}">
										UnAssingn Projects</a>
						</div></div>
							</td>
						</tr>
						
					</c:forEach>
				</table>
			</div>
			<br>
			<br>
		</c:when>
		<c:otherwise>
			<h3>No Employees Found!</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>