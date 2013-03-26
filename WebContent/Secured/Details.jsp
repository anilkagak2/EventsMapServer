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
	
	<table border = "1" > 
	<c:choose>
		<c:when test="{not empty events}">
			<c:forEach items="${events}" var="event">
	                <tr>
						<td> <input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list00.link, this)"></td>												
	                    <td>User Id 		<c:out 				value="${event.user}" />	</td>
	                    <td>Username 		<c:out 				value="${event.userName}" />	</td>
	                    <td>Email Id		<c:out 				value="${event.emailId}" />	</td>
	                    <td>Post			<c:out 				value="${event.post}" />	</td>
	                </tr>
			</c:forEach>
		</c:when>
		
		<c:otherwise>
			No events added by you.	
		</c:otherwise>
	</c:choose>
	</table>
	
	<form name="Details" action="Details" method="get">
		<input type="button" value="Add user" onclick="JavaScript:window.location='/Secured/Register.jsp';">
	</form>
	
	<form name="Details" action="Details" method="get">
		<input type="button" value="Delete User" onclick>
	</form>

</body>
</html>