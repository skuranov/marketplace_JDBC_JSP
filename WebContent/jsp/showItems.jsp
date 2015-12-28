<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/showItems.js"></script>
</head>
<body>

<h1>Online marketplace</h1>

Logged as "${userFullName}" <a href="Logout">Logout</a>


<div align="right">
	<form method="get" action="${pageContext.request.contextPath}/ShowItems">
		<input type="submit" name="showAll" value="Show All Items">
		<input type="submit" name="showMy" value="Show My Items">
		<input type="submit" name="addItem" value="Add Item to Sell">
	</form>
</div>
<div align="left">
	<form method="get" action="${pageContext.request.contextPath}/ShowItems">
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
		<c:if test="${!onlyMyItems && (userId!=null)}">
			<td>Bidding</td>
		</c:if>
		<c:if test="${onlyMyItems && (userId!=null)}">
			<td>Action</td>
		</c:if> 
	</tr>
	
	<c:forEach var="entry" items="${answer}">
	
	<form>
		<tr>
			<td>
				<c:out value="${entry.getItemId()}"/>
				<input type="hidden" name="itemId" value="${entry.getItemId()}"/>
			</td>
			
			<td>
				<c:out value="${entry.getTitle()}"/>
				<input type="hidden" name="title" value="${entry.getTitle()}"/>
			</td>
			
 			<td>
 				<c:out value="${entry.getDescription()}"/>
 				<input type="hidden" name="descr" value="${entry.getDescription()}"/>
 			</td>
 			
			<td>
				<c:out value="${entry.getFullName()}"/>
				<input type="hidden" name="fullName" value="${entry.getFullName()}"/>
			</td>
			
			<td>
				<c:out value="${entry.getStartPrice()}"/>
				<input type="hidden" name="startBid" value="${entry.getStartPrice()}"/>
			</td>
			
			<c:if test="${entry.getBuyItNow() eq '1'}">
				<td>
					<c:out value=" "/>
				</td>
			</c:if>
			
			<input type="hidden" name="flBuyItNow" value="${entry.getBuyItNow()}"/>
			
			<c:if test="${entry.getBuyItNow() ne '1'}">
				<td>
					<c:out value="${entry.getBidIncremment()}"/>
				</td>
			</c:if>
			<input type="hidden" name="bidInc" value="${entry.getBidIncremment()}"/>
			
			<c:if test="${entry.getBuyItNow() ne '1'}">
				<td>
					<c:out value="${entry.getBidValue()}"/>
				</td>
			</c:if>
			<input type="hidden" name="currentBid" value="${entry.getBidValue()}"/>
			
			<c:if test="${entry.getBuyItNow() eq '1'}">
				<td>
					<c:out value=" "/>
				</td>
			</c:if>
			
			<td>
				<c:out value="${entry.getStopDate()}"/>
			</td>

			<c:if test="${!onlyMyItems && entry.getBuyItNow() ne '1'}">
  				<td>
  					<input name="newBidValue" size=20 maxlegnth=20 placeholder="Place Bid">
					<input type="submit" name="makeBid" value="Bid">
				</td>
			</c:if>
			
			<c:if test="${!onlyMyItems && entry.getBuyItNow() eq '1'}">
  				<td>
  					<input type="submit" name="buyItNow" value="Buy It Now">
  				</td>
			</c:if>
			
			<c:if test="${onlyMyItems}">
				<td>
  					<input type="submit" name="editItem" value="Edit Item">
				</td>
			</c:if>
		</tr>
	</form>
	</c:forEach>
</table>	
</body>
</html>