<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
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
	<%
		String username = (String) session.getAttribute("username");
	%>
	<h1 align="center">
		Welcome
		<%=username%></h1>

	<br>
	<span style="text-align: center; display: block;"> <a
		href="LogoutServlet">Logout</a>
	</span>

	<br>

	<form action="DepartmentSaveServlet" method="post">
		<table align="center">
			<tr>
				<td>Dept Name :</td>
				<td><input name="departmentName" size=15 type="text" /></td>
			</tr>
			<tr>
				<td>Dept Email :</td>
				<td><input name="departmentEmail" size=15 type="text" /></td>
			</tr>
			<tr>
				<td colspan="2">Employee:</td>
			</tr>


			<c:forEach var="employee" items="${employees}">
			<tr>
				<td><input type="checkbox" name="employeeId"
					value="${employee.id}"></td>
				<td>${employee.firstName} ${employee.lastName}  </td>
			</tr>
			</c:forEach>
			
			
			
		</table>
		<input type="submit" value="submit" /> <a href="main.jsp">Back</a>

	</form>
	
	<center>
			<h3>Existing Department</h3>

	<c:forEach var="department" items="${departments}">

		
			<br> Department Name: ${department.departmentName} Department
			Email: ${department.departmentEmail} <br>
			<c:forEach var="employee" items="${department.departmentEmployees}">
					Firstname: ${employee.firstName} Lastname: ${employee.lastName} Age: ${employee.age}
		
		</c:forEach>

		
	</c:forEach>

	</center>



</body>
</html>