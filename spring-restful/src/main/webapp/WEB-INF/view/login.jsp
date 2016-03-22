<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
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

<script type="text/javascript">
	
	function validate() {
		var login = document.getElementById("userid").value;
		var passwd =  document.getElementById("passwd").value;
		alert("login="+login + " password = " + passwd);
		
	}
</script>
</head>
<body>

	<h1 align="center">Login</h1>
		<c:if test="${not empty isLoginFailure}"> 
			<h5 align="center">Incorrect username or password</h5>
		</c:if>

	<form:form method="post" modelAttribute="loginForm" action="authenticate">
		<table align="center">
			<tr>
				<td>Name :</td>
				<td><form:input path="username" id="userid"/></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><form:password path="password" id="passwd"/></td>
			</tr>
		</table>
		<button type="submit" value="submit" >Submit</button>
		<button type="reset" value="reset">Reset</button>
	</form:form>
</body>
</html>