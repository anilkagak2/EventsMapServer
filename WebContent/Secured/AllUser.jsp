<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri">${req.requestURI}</c:set>
<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users</title>

<link rel="stylesheet" href="css/screen.css" type="text/css"
	media="screen" title="default" />
<!--[if IE]>
<link rel="stylesheet" media="all" type="text/css" href="css/pro_dropline_ie.css" />
<![endif]-->

<!--  jquery core -->
<script src="js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

<!--  checkbox styling script -->
<script src="js/jquery/ui.core.js" type="text/javascript"></script>
<script src="js/jquery/ui.checkbox.js" type="text/javascript"></script>
<script src="js/jquery/jquery.bind.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
	function toggle_visibility(id) {
		var e = document.getElementById(id);
		if (e.style.display == 'block')
			e.style.display = 'none';
		else
			e.style.display = 'block';
	}
	//-->


	function changePassword(id) {
		var pass = document.getElementById(id + "new_pass").value;
		var confPass = document.getElementById(id + "confirm_pass").value
		if (pass == confPass && pass != '') {

			document.getElementById(id + "error").innerHTML = "";
			$
					.post(
							"ChangePassword",
							{
								userName : document.getElementById(id + "userName").value,
								webmail : document.getElementById(id + "webmail").value,	
								id : id,
								password : document.getElementById(id + "new").value
							},
							function(data, status) {
								document.getElementById(id + "success").innerHTML = "<p style='color:green'>Password Changed<p>";
								toggle_visibility(id + 'set');
								toggle_visibility(id + 'reset');
							});

		} else {
			document.getElementById(id + "error").innerHTML = "<p style='color:red'>Not Matching/Empty<p>";
		}
	}

	$(function() {
		$('input').checkBox();
		$('#toggle-all').click(function() {
			$('#toggle-all').toggleClass('toggle-checked');
			$('#mainform input[type=checkbox]').checkBox('toggle');
			return false;
		});
	});
</script>

<![if !IE 7]>
<!--  styled select box script version 1 -->
<script src="js/jquery/jquery.selectbox-0.5.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.styledselect').selectbox({
			inputClass : "selectbox_styled"
		});
	});
</script>
<![endif]>


<!--  styled select box script version 2 -->
<script src="js/jquery/jquery.selectbox-0.5_style_2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.styledselect_form_1').selectbox({
			inputClass : "styledselect_form_1"
		});
		$('.styledselect_form_2').selectbox({
			inputClass : "styledselect_form_2"
		});
	});
</script>

<!--  styled select box script version 3 -->
<script src="js/jquery/jquery.selectbox-0.5_style_2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.styledselect_pages').selectbox({
			inputClass : "styledselect_pages"
		});
	});
</script>

<!--  styled file upload script -->
<script src="js/jquery/jquery.filestyle.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("input.file_1").filestyle({
			image : "images/forms/upload_file.gif",
			imageheight : 29,
			imagewidth : 78,
			width : 300
		});
	});
</script>

<!-- Custom jquery scripts -->
<script src="js/jquery/custom_jquery.js" type="text/javascript"></script>

<!-- Tooltips -->
<script src="js/jquery/jquery.tooltip.js" type="text/javascript"></script>
<script src="js/jquery/jquery.dimensions.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$('a.info-tooltip ').tooltip({
			track : true,
			delay : 0,
			fixPNG : true,
			showURL : false,
			showBody : " - ",
			top : -35,
			left : 5
		});
	});
</script>

<!--  date picker script -->
<link rel="stylesheet" href="css/datePicker.css" type="text/css" />
<script src="js/jquery/date.js" type="text/javascript"></script>
<script src="js/jquery/jquery.datePicker.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
		// initialise the "Select date" link
		$('#startdatepicker').datePicker();
		$('#enddatepicker').datePicker();
		$('#endTimePicker').timepicker({
			'step' : 15
		});
		$('#startTimePicker').timepicker({
			'step' : 15
		});
	});
</script>

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
<script src="js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).pngFix();
	});
</script>

<!-- <style>
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

</style> -->

</head>
<body>
	<!-- Start: page-top-outer -->
	<div id="page-top-outer">

		<!-- Start: page-top -->
		<div id="page-top">

			<!-- start logo -->
			<div id="logo">
				<!--  ${user} from the Login.java doPost -->
				<c:if test="${not empty sessionScope.user}">
					<h1 style="color: orange">Hi ${sessionScope.user}</h1>
				</c:if>
			</div>
			<!-- end logo -->

		</div>
		<!-- End: page-top -->

	</div>
	<!-- End: page-top-outer -->

	<div class="clear">&nbsp;</div>

	<!--  start nav-outer-repeat................................................................................................. START -->
	<div class="nav-outer-repeat">
		<!--  start nav-outer -->
		<div class="nav-outer">

			<!-- start nav-right -->
			<div id="nav-right">

				<div class="nav-divider">&nbsp;</div>
				<div class="showhide-account">
					<img src="images/shared/nav/nav_myaccount.gif" width="93"
						height="14" alt="" />
				</div>
				<div class="nav-divider">&nbsp;</div>
				<a href="Logout" id="logout"><img
					src="images/shared/nav/nav_logout.gif" width="64" height="14"
					alt="" /></a>
				<div class="clear">&nbsp;</div>

				<!--  start account-content -->
				<div class="account-content">
					<div class="account-drop-inner">
						<a href="General/Settings.jsp" id="acc-settings">Settings</a>
						<div class="clear">&nbsp;</div>
						<div class="acc-line">&nbsp;</div>
						<a href="" id="acc-details">Personal details </a>
					</div>
				</div>
				<!--  end account-content -->

			</div>
			<!-- end nav-right -->


			<!--  start nav -->
			<div class="nav">
				<div class="table">
					<c:choose>
						<c:when test="${sessionScope.loginId == 1}">
							<c:set var="homePage" value="Secured/Admin.jsp" />
						</c:when>
						<c:otherwise>
							<c:set var="homePage" value="General/Events.jsp" />
						</c:otherwise>
					</c:choose>

					<ul class="select">
						<li><a href="${homePage}"><b>Dashboard</b> <!--[if IE 7]><!--></a>
						</li>
					</ul>

					<div class="clear"></div>
				</div>
			</div>
			<!-- start nav -->
		</div>
	</div>


	<div class="clear"></div>

	<!-- start content-outer -->
	<div id="content-outer">
		<!-- start content -->
		<div id="content">


			<div id="page-heading">
				<h1>All Users</h1>
			</div>

			<c:if test="${not empty sessionScope.error}">
				<h2>ERROR! ${sessionScope.error}</h2>
				<c:remove var="sessionScope.error" />
			</c:if>
			<!-- start id-form -->

			<table border="0" cellpadding="0" cellspacing="0">

				<c:choose>
					<c:when test="${sessionScope.loginId == 1}">
						<table>
							<col width="150">
							<col width="150">
							<col width="150">
							<col width="150">
							<tr>
								<th>User Id</th>
								<th>Uase Name</th>
								<th>Email</th>
								<th>Post</th>
							</tr>
							<c:forEach items="${AllUserList}" var="user">
								<tr>
									<td align="center">${user.loginId}</td>
									<td align="center">${user.userName}</td>
									<td align="center">${user.emailId }</td>
									<td align="center">${user.post }</td>
									<td id="${user.loginId}set" style="display: block;"><input
										type="Button"
										onclick="toggle_visibility('${user.loginId}set');toggle_visibility('${user.loginId}reset');"
										value="Reset Account" /></td>

									<td>
										<table style="display: none;" id="${user.loginId}reset">
											<tr>
												<th>User Name</th>
												<th>Webmail</th>
												<th>New Password</th>
												<th>Confirm</th>
											</tr>
											<tr>
												<td><input id="${user.loginId}userName" type='text' value='${user.userName}' /></td>
												<td><input id="${user.loginId}webmail" type='text' value='${user.emailId }'/></td>
												<td><input id="${user.loginId}new" type='password' /></td>
												<td><input id="${user.loginId}confirm" type='password' /></td>
												<td><input type='Button' value="Submit"
													onclick="changePassword('${user.loginId}');" /></td>
												<td><input type='Button' value="Cancel"
													onclick="toggle_visibility('${user.loginId}set');toggle_visibility('${user.loginId}reset');" /></td>
												<td id='${user.loginId}error'></td>
											</tr>

										</table>
									</td>
									<td id='${user.loginId}success'></td>
								</tr>
							</c:forEach>
						</table>
					</c:when>

					<c:otherwise>
						<div class="right">You are Not allowed Here !!!</div>
					</c:otherwise>
				</c:choose>
			</table>

			<div class="clear">&nbsp;</div>

		</div>
		<!--  end content -->
		<div class="clear">&nbsp;</div>
	</div>
	<!--  end content-outer -->


	<div class="clear">&nbsp;</div>


</body>
</html>
