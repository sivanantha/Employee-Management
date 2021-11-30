<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Employee Management</title>
</head>

<body>
	<c:if test='${formAction.equals("createEmployee")}'>
		<header>
			<h1>Create Employee</h1>
		</header>
	</c:if>
	<c:if test='${formAction.equals("updateEmployee")}'>
		<header>
			<h1>Update Employee</h1>
		</header>
	</c:if>
	<div class="form-border">
		<spring:form action="${formAction}" modelAttribute="employee"
			method="post" cssClass="form-container">
			<fieldset>
				<spring:hidden path="id" />
				<div class="row">
					<div class="col-25">
						<spring:label path="name" for="name">Employee Name</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="name" id="name" type="text"
							required="requried" value="${employee.name}"
							pattern="^(\s*[a-zA-Z]{3,20}\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20})\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\s*)$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="dateOfBirth" for="dateOfBirth"> Date Of Birth</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="dateOfBirth" id="dateOfBirth" type="date"
							required="required" value="${employee.dateOfBirth}" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="gender">Gender</spring:label>
					</div>
					<div class="col-75, radio-btn">
						<spring:radiobutton path="gender" value="male" required="required"
							checked="${employee.gender.equals('male') ? 'checked': ''}" />
						<spring:label path="gender" for="gender1">Male</spring:label>
						<spring:radiobutton path="gender" value="female"
							required="required"
							checked="${employee.gender.equals('female') ? 'checked': ''}" />
						<spring:label path="gender" for="gender2">Female</spring:label>
						<spring:radiobutton path="gender" value="others"
							required="required"
							checked="${employee.gender.equals('others') ? 'checked': ''}" />
						<spring:label path="gender" for="gender3">Others</spring:label>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="mobileNumber">Mobile Number</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="mobileNumber" type="tel"
							pattern="^(\s*[6-9][0-9]{9}\s*)$" required="required"
							value="${employee.mobileNumber}" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="email">Email</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="email" type="email" required="required"
							value="${employee.email}" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="salary">Salary</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="salary" type="text" min="0"
							pattern="^\s*([0-9]{1,20}(\.[0-9]{1,2})?)\s*$"
							required="required" value="${employee.salary}" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="dateOfJoining">Date Of Joining</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="dateOfJoining" type="date" required="required"
							value="${employee.dateOfJoining}" />
					</div>
				</div>
				<c:if test="${ null != employee}">
					<c:set value="${employee.addresses.get(0)}" var="address"
						scope="request"></c:set>
				</c:if>
				<div class="sub-header">Address Details</div>
				<spring:hidden path="id" value="${address.id}" />
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].doorNumber">Door Number</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].doorNumber" type="text"
							value="${address.doorNumber}" required="required"
							pattern="^[\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][1-9][0-9]{0,4}[A-Z]?|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?|([A-Z]|[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])[1-9][0-9]{0,4}[/][A-Z]?[1-9][0-9]{0,4}|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][A-Z]?[1-9][0-9]{0,4})[\s]*$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].street">Street</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].street" type="text"
							required="required" value="${address.street}"
							pattern="^[\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\.]?|[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\s]*$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].locality">Locality</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].locality" type="text"
							required="required" value="${address.locality}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].city">City</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].city" type="text"
							required="required" value="${address.city}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].state">State</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].state" type="text"
							required="required" value="${address.state}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].country">Country</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].country" type="text"
							required="required" value="${address.country}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$" />
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<spring:label path="addresses[0].pinCode">Pin Code</spring:label>
					</div>
					<div class="col-75">
						<spring:input path="addresses[0].pinCode" type="text"
							required="required" value="${address.pinCode}"
							pattern="^[\s]*([0-9]{3,9})[\s]*$" />
					</div>
				</div>
				<div class="center">
					<input type="submit" id="submit" value="Submit" />
				</div>
			</fieldset>
		</spring:form>
	</div>
	<a href="/viewAllEmployees"><button class="cancel-btn">Cancel</button></a>
</body>
</html>