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
	<a href="index.jsp"><button>
			<b>Home Page</b>
		</button></a>
	<c:choose>
		<c:when test="${null != param.errorMessage}">
			<div id="message">
				<span>ERROR : ${param.errorMessage}</span>
			</div>
			<img src="images/oops.gif" align="middle">			
		</c:when>

		<c:otherwise>
			<img src="images/404.gif">
		</c:otherwise>
	</c:choose>
	
</body>
</html>