<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee</title>

<style type="text/css">
form {
	text-align: center;
}

input {
	width: 100px;
}
</style>


</head>
<body>

	
	<h1 align="center">
		Welcome </h1>
	<br>
	<span style="text-align: center; display: block;"> 
	<a href="LogoutServlet">Logout</a>
	</span>

	<form:form method="post" modelAttribute="employeeForm" action="saveEmployee">

		<table align="center">
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" /></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" /></td>
			</tr>

			<tr>
				<td>Age:</td>
				<td><form:input path="age" /></td>
			</tr>
		</table>

		<input type="submit" value="submit" /> <a href="main">Back</a>
	</form:form>
	
	<center>
		<h3> Existing Employee </h3>
		<c:forEach var="employee" items="${employees}">
		Firstname: ${employee.firstName} Lastname: ${employee.lastName} Age: ${employee.age}
		<br>
		</c:forEach>
	</center>
	
	
</body>
</html>