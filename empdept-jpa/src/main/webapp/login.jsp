<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body >

<h1 align="center"  >Login </h1> 
<%
	Object isLoginFailure = request.getAttribute("isLoginFailure");
	if(isLoginFailure != null && "true".equals(isLoginFailure)) {
%>
	<h5 align="center">Incorrect username or password</h5>
<%		
	}
%>
	<form  action="LoginServlet" method="Post" >
		<table align = "center"    >
			<tr >
				<td >Name :</td>
				<td><input name="username" size=15 type="text"/></td> 
			</tr>
			<tr>
				<td>Password :</td>
				<td><input name="password" size=15 type="password" /></td>
			</tr>
		</table>
		<button  type="submit" value="submit"> Submit </button> 
		<button  type="reset" value="reset"> Reset</button>
	</form>
</body>
</html>