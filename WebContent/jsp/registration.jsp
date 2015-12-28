<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}js/registrationConfirm.js"></script> 
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/Registration" id="registrationForm">
		<input name="fullname" size=30 required placeholder="Full Name">
		<br/>
		<input name="billingAdress" size=50 required placeholder="Billing Adress">
		<br/>
		<input type="email" input name="email" size=30 pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}" required placeholder="E-mail" >
		<br/>
		<input name="login" size=20 required placeholder="Login">
		<br/>
		<input type="password" name="pswd" id="pswd" size=20 required placeholder="Password">
		<br/>
		<input type="password" name="passwordConfirm" id="passwordConfirm"  size=20 required placeholder="Re-enter password"><br/>
		<input type="submit" value="Registration"><br/>
		<button type="button" onclick="form.reset()">Reset</button>
	</form>
	<form method="get" action="${pageContext.request.contextPath}/ShowItemsGuest">
		<input type="submit" name="showAll" value="Continue as Guest">
	</form>
</body>
</html>
