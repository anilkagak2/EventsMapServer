<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Events</title>

<style>
a.LinkButton {
  border-style: solid;
  border-width : 1px 1px 1px 1px;
  text-decoration : none;
  padding : 4px;
  border-color : #000000;
}

input.LinkButton {
  border-style: solid;
  border-width : 1px 1px 1px 1px;
  text-decoration : none;
  padding : 4px;
  border-color : #000000;
}

</style>

</head>
<body>
	<!--  ${user} from the Login.java doPost -->
	<c:if test="${not empty user}">
	<h1>Welcome ${user}
	</h1>
	</c:if>
		
	<c:choose>
		<c:when test="{not empty events}">
			<c:forEach items="${events}" var="event">
	                <tr>
	                    <td>Event Id 	<fmt:formatNumber 	value="${event.id}" />		</td>
	                    <td>Title 		<c:out 				value="${event.title}" />	</td>
	                    <td>Start Time	<fmt:formatDate 	value="${event.startTime}"/></td>
	                    <td>End Time	<fmt:formatDate 	value="${event.endTime}" />	</td>
	                    <td>Address 	<c:out 				value="${event.address}" />	</td>
	                    <td>Content 	<c:out 				value="${event.content}" />	</td>
	                </tr>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			No events added by you.	
		</c:otherwise>
	</c:choose>

	<form name="Logout" action="Logout" method="post">
		<input type="submit" value="Log Out">
	</form>

	<form name="Add Event" action="FetchLocationCategory" method="post">
		<input type="submit" value="Fetch">
	</form>

</body>
</html>