<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
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
		Welcome
		</h1>

	<br>
	<span style="text-align: center; display: block;"> <a
		href="LogoutServlet">Logout</a>
	</span>

	<br>

	<form:form method="post" modelAttribute="departmentForm" action="saveDepartment">
		<table align="center">
			<tr>
				<td>Dept Name :</td>
				<td><form:input path="departmentName"/></td>
			</tr>
			<tr>
				<td>Dept Email :</td>
				<td><form:input path="departmentEmail"/></td>
			</tr>
			<tr>
				<td colspan="2">Employee:</td>
			</tr>
			
			<c:forEach var="employee" items="${employees}">
			<tr>
				<td><form:checkbox path="employeeIds" value="${employee.id}"/></td>
				<td>${employee.firstName}  &nbsp; ${employee.lastName}</td>
			</tr>
			</c:forEach>
	
		</table>
		<input type="submit" value="submit" /> <a href="main">Back</a>
	</form:form>
	
		
	<center>
		<h3>Existing Department</h3>

		<c:forEach var="department" items="${departments}">
			<br>
			<br>
			Department Name= ${department.departmentName}
			Department Email= ${department.departmentEmail}
			<br>Employees :
			<c:forEach var="employee" items="${department.departmentEmployees}">
				${employee.firstName}&nbsp; ${employee.lastName}&nbsp;${employee.age}
				<br>
			</c:forEach>
		</c:forEach>
	</center>	
	
</body>
</html>