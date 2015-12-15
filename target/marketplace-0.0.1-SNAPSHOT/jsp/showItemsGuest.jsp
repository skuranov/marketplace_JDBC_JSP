<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/showItems.js"></script>
</head>
<body>


<h1>Online marketplace</h1>
Logged as Anonymous <a href="jsp/login.jsp">Login</a>
or <a href="jsp/registration.jsp">Registration</a>

<div align="right">
	<form method="get" action="${pageContext.request.contextPath}/ShowItemsGuest">
		<input type="submit" name="showAll" value="Show All Items">
	</form>
</div>



<div align="left">
	<form method="get" action="${pageContext.request.contextPath}/ShowItemsGuest">
		<h3>Search parameters</h3>
		<input name="keywords" size=20 maxlegnth=200 placeholder="Keywords">
		<input type="submit" name="searchByDescr" value="Search">
	</form>
</div>	

<table>
	<tr>
		<td>ItemId</td>
		<td>Title</td>
		<td>Description</td>
		<td>Seller</td>
		<td>Start Price</td>
		<td>Bid Inc</td>
		<td>Bid Offer</td>
		<td>Stop date</td>
	</tr>
	
	<c:forEach var="entry" items="${answer}">
		<tr>
			<td><c:out value="${entry.getItemId()}"/></td>
			<td><c:out value="${entry.getTitle()}"/></td>
 			<td><c:out value="${entry.getDescription()}"/></td>
			<td><c:out value="${entry.getFullName()}"/></td>
			<td><c:out value="${entry.getStartPrice()}"/></td>
			<td><c:out value="${entry.getBidIncremment()}"/></td>
			<td><c:out value="${entry.getBidValue()}"/></td>
			<td><c:out value="${entry.getStopDate()}"/></td> 
		</tr>
	</c:forEach>
</table>	
</body>
</html>