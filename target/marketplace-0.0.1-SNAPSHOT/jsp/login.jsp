<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Login</title>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/loginConfirm.js"></script> 
	</head>
	<body>
			<form method="post" action="${pageContext.request.contextPath}/Login" id="loginForm">
					<input type="hidden" name="destination" value="${param.destination}"> 
 					Login: <br/>
					<input type="text" name="login" size=30 required placeholder="Login"><br/>
					Password: <br/>
					<input type="password" name="password" size=30 required placeholder="Password"><br/>
					<input type="submit" value="Sign In"><br/><br/>
			</form>
			<a href="${pageContext.request.contextPath}/ShowItems">Continue as guest</a><br/><br/>
			<a href="Registration.html">Register</a><br/><br/>
	</body>
</html>
