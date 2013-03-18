<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>

<!-- Javascript for validation checks -->
 <script type="text/javascript">
 function checkCreateLoginForm (){
	 var x = document.forms["createForm"];
	 
	 /* email filter */
	 var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	 
	 if (x["user"].value == null || x["user"].value==""){
		 alert ("Invalid user name field.");
		 return false;
	 }
	 else if ((x["pass"].value != x["repass"].value)){
		alert ("Password & Re-passwords do not match.");
		return false;
	 }
	 else if (!filter.test(x["email"].value)){
		 alert ("Invalid Email Id.");
		 return false;
	 }
	 else if (x["post"].value == "" || x["post"].value==null){
		 alert("Post invalid.");
		 return false;
	 }
	 else return true;
 }
 </script>


</head>
<body>
	<form name="createForm" method="get" action="Register" onsubmit="return checkCreateLoginForm();">
	<table>
		<tr><td>User</td>
			<td><input type="text" name="user"></td></tr>
		<tr><td>Email-Id</td>
			<td><input type="text" name="email"></td></tr>
		<tr><td>Password</td>
			<td><input type="password" name="pass"></td></tr>
		<tr><td>Re-Password</td>
			<td><input type="password" name="repass"></td></tr>
		<tr><td>User</td>
			<td><input type="text" name="post"></td></tr>
		<tr><td></td>
			<td><input type="submit" value="Create"></td></tr>
	</table>
	</form>
</body>
</html>