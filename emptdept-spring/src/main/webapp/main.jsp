<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
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

	<br>
	<span style="text-align: center; display: block;">
	 <a href="EmployeeAddServlet">Add Employee</a>
	</span>


	<br>
	<span style="text-align: center; display: block;">
	 <a href="DepartmentAddServlet">Add Department</a>
	</span>
</body>
</html>