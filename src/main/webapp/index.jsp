<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Employee Management</title>
</head>
<body>
	<div class="container">
		<div class="title-container">
			<header>
				<h1>Employee Management System</h1>
			</header>
		</div>

		<div class="menu">
			<div class="employees-container">
				<span>Employees</span> <a id="create-employee"
					href="createEmployeeForm">Create Employee</a> <br> <a
					id="view-employee" href="viewAllEmployees">Manage Employees</a>
			</div>
			<div class="projects-container">
				<span>Projects</span> 
				<a href="createProjectForm">CreateProject</a> 
				<a href="viewAllProjects">Manage Projects</a>
			</div>
		</div>
	</div>
</body>
</html>