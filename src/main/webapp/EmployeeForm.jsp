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
	<form action="${formAction}" method="post">
		<fieldset>
			<input name="id" type="hidden" value="${employee.id}" readonly>
			<table cellpadding="5" cellspacing="5">
				<tr>
					<td><label for="name">Employee Name</label></td>
					<td><input name="name" id="name" type="text" required
						value="${employee.name}"
						pattern="^(\s*[a-zA-Z]{3,20}\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20})\s*)$|^((\s*[a-zA-Z]{3,20}) ([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\s*)$">
					</td>
				</tr>
				<tr>
					<td><label for="dateOfBirth"> Date Of Birth</label></td>
					<td><input name="dateOfBirth" id="dateOfBirth" type="date"
						required value="${employee.dateOfBirth}"></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><c:choose>
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
						</c:choose></td>
				</tr>
				<tr>
					<td><label for="mobileNumber">Mobile Number</label></td>
					<td><input name="mobileNumber" id="mobileNumber" type="tel"
						pattern="^(\s*[6-9][0-9]{9}\s*)$" required
						value="${employee.mobileNumber}"></td>
				</tr>
				<tr>
					<td><label for="email">Email</label></td>
					<td><input name="email" id="email" type="email" required
						value="${employee.email}"></td>
				</tr>
				<tr>
					<td><label for="salary">Salary</label></td>
					<td><input name="salary" id="salary" type="text" min="0"
						pattern="^\s*([0-9]{1,20}(\.[0-9]{1,2})?)\s*$" required
						value="${employee.salary}"></td>
				</tr>
				<tr>
					<td><label for="dateOfJoining">Date Of Joining</label></td>
					<td><input name="dateOfJoining" id="dateOfJoining" type="date"
						required value="${employee.dateOfJoining}"></td>
				</tr>
				<c:choose>
					<c:when test="${null == employee}">
						<tr>
							<td><br> <b>Address Details</b><br> <br></td>
						</tr>
						<tr>
							<td><label for="doorNumber">Door Number</label></td>
							<td><input name="doorNumber" id="doorNumber" type="text"
								required
								pattern="^[\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][1-9][0-9]{0,4}[A-Z]?|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?|([A-Z]|[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])[1-9][0-9]{0,4}[/][A-Z]?[1-9][0-9]{0,4}|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][A-Z]?[1-9][0-9]{0,4})[\s]*$">
							</td>
						</tr>
						<tr>
							<td><label for="street">Street</label></td>
							<td><input name="street" id="street" type="text" required
								pattern="^[\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\.]?|[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\s]*$">
							</td>
						</tr>
						<tr>
							<td><label for="locality">Locality</label></td>
							<td><input name="locality" id="locality" type="text"
								required
								pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
							</td>
						</tr>
						<tr>
							<td><label for="city">City</label></td>
							<td><input name="city" id="city" type="text" required
								pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
							</td>
						</tr>
						<tr>
							<td><label for="state">State</label></td>
							<td><input name="state" id="state" type="text" required
								pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
							</td>
						</tr>
						<tr>
							<td><label for="country">Country</label></td>
							<td><input name="country" id="country" type="text" required
								pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
							</td>
						</tr>
						<tr>
							<td><label for="pinCode">Pin Code</label></td>
							<td><input name="pinCode" id="pinCode" type="text" required
								pattern="^[\s]*([0-9]{3,9})[\s]*$"></td>
						</tr>
						<br>
					</c:when>

					<c:otherwise>
						<c:forEach items="${employee.addresses}" var="address">
							<tr>
								<td><br> <b>Address Details</b><br> <br></td>
							</tr>
							<input name="addressId" type="hidden" value="${address.id}"
								readonly>
							<tr>
								<td><label for="doorNumber">Door Number</label></td>
								<td><input name="doorNumber" id="doorNumber" type="text"
									required value="${address.doorNumber}"
									pattern="^[\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][1-9][0-9]{0,4}[A-Z]?|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?|([A-Z]|[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])[1-9][0-9]{0,4}[/][A-Z]?[1-9][0-9]{0,4}|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/][A-Z]?[1-9][0-9]{0,4})[\s]*$">
								</td>
							</tr>
							<tr>
								<td><label for="street">Street</label></td>
								<td><input name="street" id="street" type="text" required
									value="${address.street}"
									pattern="^[\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\.]?|[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\s]*$">
							<tr>
								<td><label for="locality">Locality</label></td>
								<td><input name="locality" id="locality" type="text"
									required value="${address.locality}"
									pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
									<br></td>
							</tr>
							<tr>
								<td><label for="city">City</label></td>
								<td><input name="city" id="city" type="text" required
									value="${address.city}"
									pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
									<br></td>
							</tr>
							<tr>
								<td><label for="state">State</label></td>
								<td><input name="state" id="state" type="text" required
									value="${address.state}"
									pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
								</td>
							</tr>
							<tr>
								<td><label for="country">Country</label></td>
								<td><input name="country" id="country" type="text" required
									value="${address.country}"
									pattern="^[\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2}){1,2})[\s]*$">
								</td>
							</tr>
							<tr>
								<td><label for="pinCode">Pin Code</label></td>
								<td><input name="pinCode" id="pinCode" type="text" required
									value="${address.pinCode}" pattern="^[\s]*([0-9]{3,9})[\s]*$">
								</td>
							</tr>
							<br>
							<br>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<br> <input type="submit" id="submit" value="Submit">
		</fieldset>
	</form>
	<br>
	<a href="index.jsp"><button>Cancel</button></a>
</body>
</html>