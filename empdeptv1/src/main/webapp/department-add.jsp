<!DOCTYPE html>
<%@page import="projects.kiran.programming.myapp.model.Employee"%>
<%@page import="projects.kiran.programming.myapp.model.Department"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import=" java.sql.ResultSet"%>
<%@page import="  java.sql.Connection"%>
<%@page import="  java.sql.DriverManager"%>
<%@page import="  java.sql.ResultSet"%>


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

			<%
			    List<Employee> employees = (List<Employee>) request.getAttribute("employees");

			    if (employees != null) {
					for (Employee employee : employees) {
			%>
			<tr>
				<td><input type="checkbox" name="employeeId" value="<%=employee.getId()%>"></td>
				<td><%=employee.getFirstName() + " " + employee.getLastName()%></td>
			</tr>
			<%
			    	}
			    }
			%>

		</table>
		<input type="submit" value="submit" /> <a href="main.jsp">Back</a>

	</form>
	
		<%
	    String isError = (String) request.getAttribute("isError");
	    if (isError == null || "true".equals(isError)) {
			List <Department> departmentEmployees = (List<Department>) request.getAttribute("departmentEmployees");

			if (departmentEmployees != null) {
	%>
	<center>
		<h3>Existing Department</h3>

		<%
		    	for (Department departmentEmployee : departmentEmployees) {
		%>
					<br>
					<br>
					Department Name= <%=departmentEmployee.getDepartmentName()%>
					Department Email=<%=departmentEmployee.getDepartmentEmail()%>
					<br>Employees :
		<%
					for(Employee employee: departmentEmployee.getDepartmentEmployees()) {
		%>
		
						<%=employee.getFirstName()%> &nbsp; <%=employee.getLastName()%> &nbsp; <%= employee.getAge() %>
						<br>
		<%
		    		}
				}
		    } 
	    }
		%>
	
	
</body>
</html>