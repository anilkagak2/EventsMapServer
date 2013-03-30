<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddEvent</title>

</head>
<body>
	<c:if test="${not empty sessionScope.error}">
		<h2>ERROR! ${sessionScope.error}
		</h2>
		<c:remove var="sessionScope.error" />
	</c:if>

	<form name="BackToHome" action="Details" method="get">
		<input type="button" value="Back to Home" onclick="JavaScript:window.location='/Secured/Admin.jsp';">
	</form>

	<form name="addEvent" method="post" action="AddEvent" onsubmit="return checkAddEventForm();">
	<table>
		<tr><td>Title</td>
			<td><input type="text" name="title"></td></tr>
		<tr><td>Content</td>
			<td><input type="text" name="content"></td></tr>
			
		<!--  <label> Main Land: -->
		<select name="mainland">
			<c:forEach items="${mainland}" var="mainland">
				<option value="${mainland.mainLandId}"><c:out value="${mainland.mainLand}" /></option>
			</c:forEach>
		</select>
		<!-- </label> -->
		
		<tr><td>subLand</td>
			<td><input type="text" name="subland"></td></tr>
		<tr><td>Start Time</td>
			<td><input type="datetime" name="starttime">
		<tr><td>End Time</td>
			<td><input type="datetime" name="endtime">
		<label>Category: <select name="category">
		  <c:forEach items="${category}" var="category">
		     <option value="${category.categoryId}"><c:out value="${category.category}" /></option>
		 </c:forEach>
		  </select>
		  </label>
		<select name="status">
		  <option value="1">ONGOING</option>
		  <option value="2">SCHEDULED</option>
		  <option value="3">CANCELLED</option>
		  <option value="4">COMPLETED</option>
		  </select>
	<!--  	<form name="Add Event" action="AddEvent" method="post">-->
		<input type="submit" value="Add">
	</table>
	</form>
	
	
</body>
</html>