<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri">${req.requestURI}</c:set>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>
<link rel="stylesheet" href="css/screen.css" type="text/css"
	media="screen" title="default" />
<!--  jquery core -->

<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

<script src="js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

<!-- Custom jquery scripts -->
<script src="js/jquery/custom_jquery.js" type="text/javascript"></script>

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
<script src="js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
$(document).pngFix( );
});
</script>

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
<body id="login-bg">

	<!-- Start: login-holder -->
	<div id="login-holder">

		<!-- start logo -->
		<div id="logo-login">
			<!-- <a href="login.html"><img src="images/shared/logo.png" width="156" height="40" alt="" /></a> -->
			<a href="" class="back-login"><img src="images/shared/logo.png"
				width="156" height="40" alt="" /></a>
		</div>
		<!-- end logo -->

		<div class="clear"></div>

		<div style="color: #FF0000;">
			<c:if test="${not empty error}">
				<script type="text/javascript">
					alert('<c:out value="${error}" />');
				</script>
				<!-- <h2>ERROR! ${error}</h2>  -->
				<c:remove var="error" />
			</c:if>
		</div>

		<!--  start loginbox ................................................................................. -->
		<div id="loginbox">

			<!--  start login-inner -->
			<div id="login-inner">

				<form name="loginForm" method="post" action="Login"
					onsubmit="return checkLoginForm();">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th>Webmail-Id</th>
							<td><input type="text" class="login-inp" name="email" /></td>
						</tr>
						<tr>
							<th>Password</th>
							<td><input type="password" onfocus="this.value=''"
								class="login-inp" name="pass" /></td>
						</tr>
						
						<tr>
							<th valign="top">Post:</th>
							<td><select name="post" selected="${posts[0]}">
									<c:forEach items="${posts}" var="post">
										<option value="${post}">
											<c:out value="${post}" />
										</option>
									</c:forEach>
								</select>
							</td>
							<!-- <td>
								<div class="error-left"></div>
								<div class="error-inner">This field is required.</div>
							</td> -->
							<td></td>
						</tr>
						
						<tr>
							<th></th>
							<td valign="top"><input type="checkbox"
								class="checkbox-size" id="login-check" /><label
								for="login-check">Remember me</label></td>
						</tr>
						<tr>
							<th></th>
							<td><input type="submit" class="submit-login" value="Login" /></td>
						</tr>
					</table>
				</form>
			</div>
			<!--  end login-inner -->
			<div class="clear"></div>
			<a href="" class="forgot-pwd">Forgot Password?</a>
		</div>
		<!--  end loginbox -->

		<!--  start forgotbox ................................................................................... -->
		<div id="forgotbox">
			<div id="forgotbox-text">Please send us your email and we'll
				reset your password.</div>
			<!--  start forgot-inner -->
			<div id="forgot-inner">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th>Email address:</th>
						<td><input type="text" value="" class="login-inp" /></td>
					</tr>
					<tr>
						<th></th>
						<td><input type="button" class="submit-login" /></td>
					</tr>
				</table>
			</div>
			<!--  end forgot-inner -->
			<div class="clear"></div>
			<a href="" class="back-login">Back to login</a>
		</div>
		<!--  end forgotbox -->

	</div>
	<!-- End: login-holder -->
</body>
</html>