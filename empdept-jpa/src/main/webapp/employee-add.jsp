<%@page import="projects.kiran.programming.myapp.entity.Employee"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Statement"%>
<%@page import=" java.sql.ResultSet"%>
<%@page import="  java.sql.Connection"%>
<%@page import="  java.sql.DriverManager"%>





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

	<%
		String username = (String) session.getAttribute("username");
	%>
	<h1 align="center">
		Welcome <%=username%></h1>
	<br>
	<span style="text-align: center; display: block;"> 
	<a href="LogoutServlet">Logout</a>
	</span>

	<form action="EmployeeSaveServlet" method="post">

		<table align="center">
			<tr>
				<td>First Name:</td>
				<td><input name="firstName" size=15 type="text"></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><input name="lastName" size=15 type="text"></td>
			</tr>

			<tr>
				<td>Age:</td>
				<td><input name="age" size=15 type="text"></td>
			</tr>
		</table>

		<input type="submit" value="submit" /> <a href="main.jsp">Back</a>
	</form>
	
	<%
		String isError = (String)request.getAttribute("isError");
		if(isError == null || "true".equals(isError)) {
			List<Employee> employees = (List<Employee>)request.getAttribute("employees");

			if(employees != null) {
	%>
					<center><h3> Existing Employee </h3> 
	
	<% 
				for(Employee employee:employees) {
    %>				
					Firstname = <%= employee.getFirstName() %> Lastname = <%= employee.getLastName() %> Age = <%= employee.getAge() %>
				<br>
   <% 
				}
			}
		} else {
		
	%>
			<center><h3>Error occurred while adding employee</h3></center>
			
	<%
		}
	%>


</body>
</html>