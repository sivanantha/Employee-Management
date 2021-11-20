<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management</title>
</head>
<body>
	<c:choose>
		<c:when test="${null != exception}">
			<center>
				<img src="images/oops.gif" align="middle">
			</center>
			<center>
				<h4>ERROR : ${exception.getMessage()}</h4>
			</center>
		</c:when>

		<c:when test="${null != errorMessage }">
			<center>
				<img src="images/oops.gif" align="middle">
			</center>
			<center>
				<h4>ERROR : ${errorMessage}</h4>
			</center>
		</c:when>

		<c:otherwise>
			<center>
				<img src="images/404.gif">
			</center>
		</c:otherwise>
	</c:choose>
	<a href="index.jsp"><button>
			<b>Home Page</b>
		</button></a>
</body>
</html>