<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Edit Item</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/EditItem.js"></script>
		<title>Insert title here</title>
	</head>
	<body> 
	
		<form method="get" action="EditItemProt">
		<input type="hidden" name="editFlag" value="${flEditItem}">
		<c:if test="${flEditItem eq '0'}">		
			<input name="titleofitem" size=20 maxlength=20 required placeholder="Title of Item"><br/>
			<textarea rows="10" cols="45" name="description" placeholder="Description"></textarea><br/>
			<input name="startPrice" size=20 required placeholder="Start price"><br/>
			<input type="text" input name="timeLeft" size=10 required placeholder="Time left" id="timeLeft"><br/>
			<input name="bidInc" size=20 required placeholder="Bid inc" id="bidInc" ><br/>
			Buy It Now:	<input type="checkbox" name="buyItNow" onClick="disableBidInc()" id="buyItNow" ><br/>
			<input type="hidden" name="flEditItem" value="0">
		</c:if>
		<c:if test="${flEditItem eq '1'}">		
			<input name="titleofitem" value="${title}" size=20 maxlength=20 required placeholder="Title of Item"><br/>
			<textarea rows="10" cols="45" name="description" placeholder="Description">${descr}</textarea><br/>
			<input name="startPrice" value="${startBid}" size=20 required placeholder="Start price"><br/>
			<input type="text" input name="timeLeft" size=10 required placeholder="Time left" id="timeLeft"><br/>
			<input name="bidInc" value="${bidInc}" size=20 required placeholder="Bid inc" id="bidInc" ><br/>
			Buy It Now:	<input type="checkbox" name="buyItNow" value="${flBuyItNow}" onClick="disableBidInc()" id="buyItNow" ><br/>
			<input type="hidden" name="flEditItem" value="1">
			<input type="hidden" name="itemId" value="${itemId}">
		</c:if>
			<button type="button" onclick="form.reset()">Reset</button><br/>
			<input type="submit" name="confirm" value="Confirm"> 
		</form>
	</body>
</html>