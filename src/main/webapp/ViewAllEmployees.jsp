<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.time.format.DateTimeFormatter, java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management</title>
</head>
<body>
	<h1>Manage Employees</h1>
	<a href="index.jsp"><button>
			<b>Home Page</b>
		</button></a>
	<a href="viewAllProjects"><button>
			<b>View All Projects</b>
		</button></a>
	<c:choose>
		<c:when test="${!employees.isEmpty()}">
			<a href="deleteAllEmployees"><button>
					<b>Delete All</b>
				</button></a>
			<br>
			<br>
			<table border="2" align="center" cellpadding="5" cellspacing="5"
				bordercolor="green">
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
						<address>
							<td><c:forEach items="${employee.addresses}" var="address">
						${address.doorNumber},${address.street},<br>
						${address.locality},${address.city},<br>
						${address.state},${address.state},<br>
						${address.pinCode}.
					</c:forEach>
						</address>
						</td>
						<td>
							<ol>
								<c:forEach items="${employee.projects}" var="project">
									<li>${project.name}</li>
								</c:forEach>
							</ol>
						</td>
						<td><a href="updateEmployeeForm?id=${employee.id}"><button>
									<b>Update</b>
								</button></a><br> <br> <a href="deleteEmployee?id=${employee.id}"><button>
									<b>Delete</b>
								</button></a></td>
						<td><a href="assignProjectsForm?id=${employee.id}"><button>
									<b>Assign Projects</b>
								</button></a> <br> <br> <a
							href="unAssignProjectsForm?id=${employee.id}"><button>
									<b>UnAssingn Projects</b>
								</button></a></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<br>
		</c:when>
		<c:otherwise>
			<h3>No Employees Found!</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>