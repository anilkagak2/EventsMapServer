<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>nextPage</title>

<!-- Javascript for validation checks -->
 <script type="text/javascript">
 function checkLoginForm (){
	 var x = document.forms["loginForm"];
	 
	 if (x["user"].value == null || x["user"].value==""){
		 alert ("Invalid user name field.");
		 return false;
	 }
	 else if ((x["pass"].value == null || x["pass"].value=="")){
		alert ("Invalid Password.");
		return false;
	 }
	 else return true;
 }
 </script>


</head>
<body>
	<form name="loginForm" method="post" action="Login" onsubmit="return checkLoginForm();" >
	<table>
		<tr><td>User</td><td><input type="text" name="user"></td></tr>
		<tr><td>Password</td><td><input type="password" name="pass"></td></tr>
		<tr><td></td><td><input type="submit" value="Login"></td></tr>
		<tr><td></td><td><a href="Register.jsp">Register Here</a></td></tr>
	</table>
	</form>
</body>
</html>