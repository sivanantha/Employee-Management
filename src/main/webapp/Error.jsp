<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Employee Management</title>
</head>
<body>
	<c:choose>
		<c:when test="${null != exception}">
				<img src="images/oops.gif" align="middle">
			<div id="message">
				<span>ERROR : ${exception.getMessage()}</span>
			</div>
		</c:when>

		<c:when test="${null != errorMessage }">
				<img src="images/oops.gif" align="middle">
			<div id="message">
				<span>ERROR : ${errorMessage}</span>
			</div>
		</c:when>

		<c:otherwise>
				<img src="images/404.gif">
		</c:otherwise>
	</c:choose>
	<a href="index.jsp"><button>
			<b>Home Page</b>
		</button></a>
</body>
</html>