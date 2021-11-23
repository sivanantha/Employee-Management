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
	<c:if test="${employee == null }">
		<header>
			<h1>Create Employee</h1>
		</header>
	</c:if>
	<c:if test="${employee != null}">
		<header>
			<h1>Update Employee</h1>
		</header>
	</c:if>
	<div class="form-border">
		<form action="${formAction}" method="post" class="form-container">
			<fieldset >
				<input name="id" type="hidden" value="${employee.id}" readonly>
				<div class="row">
					<div class="col-25">
						<label for="name">Employee Name</label>
					</div>
					<div class="col-75">
						<input name="name" id="name" type="text" required
							value="${employee.name}"
							pattern="^(\s*[a-zA-Z]{3,20}\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20})\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\s*)$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="dateOfBirth"> Date Of Birth</label>
					</div>
					<div class="col-75">
						<input name="dateOfBirth" id="dateOfBirth" type="date" required
							value="${employee.dateOfBirth}">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label id="gender">Gender</label>
					</div>
					<div class="col-75, radio-btn">
						<c:choose>
							<c:when test='${employee.gender.equals("male")}'>
								<input name="gender" id="male" type="radio" value="male"
									required checked>
								<label for="male">Male</label>
								<input name="gender" id="female" type="radio" value="female"
									required>
								<label for="female">Female</label>
								<input name="gender" id="others" type="radio" value="others"
									required>
								<label for="others">Others</label>
							</c:when>

							<c:when test='${employee.gender.equals("female")}'>
								<input name="gender" id="male" type="radio" value="male"
									required>
								<label for="male">Male</label>
								<input name="gender" id="female" type="radio" value="female"
									required checked>
								<label for="female">Female</label>
								<input name="gender" id="others" type="radio" value="others"
									required>
								<label for="others">Others</label>
							</c:when>

							<c:when test='${employee.gender.equals("others")}'>
								<input name="gender" id="male" type="radio" value="male"
									required>
								<label for="male">Male</label>
								<input name="gender" id="female" type="radio" value="female"
									required>
								<label for="female">Female</label>
								<input name="gender" id="others" type="radio" value="others"
									required checked>
								<label for="others">Others</label>
							</c:when>
							<c:otherwise>
								<input name="gender" id="male" type="radio" value="male"
									required>
								<label for="male">Male</label>
								<input name="gender" id="female" type="radio" value="female"
									required>
								<label for="female">Female</label>
								<input name="gender" id="others" type="radio" value="others"
									required>
								<label for="others">Others</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="mobileNumber">Mobile Number</label>
					</div>
					<div class="col-75">
						<input name="mobileNumber" id="mobileNumber" type="tel"
							pattern="^(\s*[6-9][0-9]{9}\s*)$" required
							value="${employee.mobileNumber}">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="email">Email</label>
					</div>
					<div class="col-75">
						<input name="email" id="email" type="email" required
							value="${employee.email}">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="salary">Salary</label>
					</div>
					<div class="col-75">
						<input name="salary" id="salary" type="text" min="0"
							pattern="^\s*([0-9]{1,20}(\.[0-9]{1,2})?)\s*$" required
							value="${employee.salary}">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="dateOfJoining">Date Of Joining</label>
					</div>
					<div class="col-75">
						<input name="dateOfJoining" id="dateOfJoining" type="date"
							required value="${employee.dateOfJoining}">
					</div>
				</div>
				<c:if test="${ null != employee}">
					<c:set value="${employee.addresses.get(0)}" var="address"
						scope="request"></c:set>
				</c:if>
				<div class="sub-header">Address Details</div> 
				<input name="addressId" type="hidden"
					value="${address.id}" readonly>
				<div class="row">
					<div class="col-25">
						<label for="doorNumber">Door Number</label>
					</div>
					<div class="col-75">
						<input name="doorNumber" id="doorNumber" type="text"
							value="${address.doorNumber}" required
							pattern="^[\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][1-9][0-9]{0,4}[A-Z]?|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?|([A-Z]|[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])[1-9][0-9]{0,4}[/][A-Z]?[1-9][0-9]{0,4}|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][A-Z]?[1-9][0-9]{0,4})[\s]*$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="street">Street</label>
					</div>
					<div class="col-75">
						<input name="street" id="street" type="text" required
							value="${address.street}"
							pattern="^[\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\.]?|[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\s]*$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="locality">Locality</label>
					</div>
					<div class="col-75">
						<input name="locality" id="locality" type="text" required
							value="${address.locality}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="city">City</label>
					</div>
					<div class="col-75">
						<input name="city" id="city" type="text" required
							value="${address.city}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="state">State</label>
					</div>
					<div class="col-75">
						<input name="state" id="state" type="text" required
							value="${address.state}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="country">Country</label>
					</div>
					<div class="col-75">
						<input name="country" id="country" type="text" required
							value="${address.country}"
							pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="pinCode">Pin Code</label>
					</div>
					<div class="col-75">
						<input name="pinCode" id="pinCode" type="text" required
							value="${address.pinCode}" pattern="^[\s]*([0-9]{3,9})[\s]*$">
					</div>
				</div>
				<input type="submit" id="submit" value="Submit">
			</fieldset>
		</form>
	</div>
	<a href="index.jsp"><button class="cancel-btn">Cancel</button></a>
</body>
</html>