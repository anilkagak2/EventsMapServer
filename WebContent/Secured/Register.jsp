<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri">${req.requestURI}</c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

<link rel="stylesheet" href="css/screen.css" type="text/css"
	media="screen" title="default" />

<link
	href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<!--[if IE]>
<link rel="stylesheet" media="all" type="text/css" href="css/pro_dropline_ie.css" />
<![endif]-->

<!--  jquery core -->
<script src="js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

<!-- 
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
	
</script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
	
</script>
<script type="text/javascript"
	src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
	
</script>
-->

<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="js/jquery/bootstrap/bootstrap.min.js">
	
</script>
<script type="text/javascript"
	src="js/jquery/bootstrap/bootstrap-datetimepicker.min.js">
	
</script>
<script type="text/javascript"
	src="js/jquery/bootstrap/bootstrap-datetimepicker.pt-BR.js">
	
</script>


<script type="text/javascript">
	$(function() {
		$('#startDatetimepicker').datetimepicker({
			language : 'en',
			format : 'yyyy-MM-dd hh:mm:ss',
		});

		$('#endDatetimepicker').datetimepicker({
			language : 'en',
			format : 'yyyy-MM-dd hh:mm:ss',
		});
	});
</script>

<!--  checkbox styling script -->
<script src="js/jquery/ui.core.js" type="text/javascript"></script>
<script src="js/jquery/ui.checkbox.js" type="text/javascript"></script>
<script src="js/jquery/jquery.bind.js" type="text/javascript"></script>
<script type="text/javascript">
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

<!-- Javascript for validation checks -->
<script type="text/javascript">
	function checkCreateLoginForm() {
		var x = document.forms["createForm"];

		/* email filter */
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

		if (x["user"].value == null || x["user"].value == "") {
			alert("Invalid user name field.");
			return false;
		} else if ((x["pass"].value != x["repass"].value)) {
			alert("Password & Re-passwords do not match.");
			return false;
		} else if (!filter.test(x["email"].value)) {
			alert("Invalid Email Id.");
			return false;
		} else if (x["post"].value == "" || x["post"].value == null) {
			alert("Post invalid.");
			return false;
		} else
			return true;
	}
</script>

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
<script src="js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).pngFix();
	});
</script>

</head>
<body>
	<!-- Start: page-top-outer -->
	<div id="page-top-outer">

		<!-- Start: page-top -->
		<div id="page-top">

			<!-- start logo -->
			<div id="logo" style="height: 24px; width: 176px;">
				<!--  ${user} from the Login.java doPost -->
				<c:if test="${not empty sessionScope.user}">
					<h1 style="color: orange; font-size: 22px">Hi
						${sessionScope.user}</h1>
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
						<a href="FetchDetails" id="acc-details">Personal details </a>
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
				<h1>Register</h1>
			</div>


			<table border="0" width="100%" cellpadding="0" cellspacing="0"
				id="content-table">
				<tr>
					<th rowspan="3" class="sized"><img
						src="images/shared/side_shadowleft.jpg" width="20" height="300"
						alt="" /></th>
					<th class="topleft"></th>
					<td id="tbl-border-top">&nbsp;</td>
					<th class="topright"></th>
					<th rowspan="3" class="sized"><img
						src="images/shared/side_shadowright.jpg" width="20" height="300"
						alt="" /></th>
				</tr>
				<tr>
					<td id="tbl-border-left"></td>
					<td>
						<!--  start content-table-inner -->
						<div id="content-table-inner">

							<table border="0" width="100%" cellpadding="0" cellspacing="0">
								<tr valign="top">
									<td><div style="color: #FF0000;">
											<c:if test="${not empty error}">
												<h2>ERROR! ${error}</h2>
												<c:remove var="sessionScope.error" />
											</c:if>
										</div> <!-- start id-form -->

										<form name="Register" method="post" action="Register">
											<table border="0" cellpadding="0" cellspacing="0"
												id="id-form">
												<tr>
													<th valign="top">User:</th>
													<td><input type="text" class="inp-form" name="user"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Email:</th>
													<td><input type="text" class="inp-form" name="email"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Password:</th>
													<td><input type="password" class="inp-form"
														name="pass"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Re-Password:</th>
													<td><input type="password" class="inp-form"
														name="repass"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Post:</th>
													<td><input type="text" class="inp-form" name="post"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th>&nbsp;</th>
													<td valign="top"><input type="submit" value="Create"
														style="width: 100px; height: 40px" /> <input type="reset"
														value="Reset" style="width: 100px; height: 40px" /></td>
													<td></td>
												</tr>
											</table>
										</form> <!-- end id-form  -->
								<tr>
									<td><img src="images/shared/blank.gif" width="695"
										height="1" alt="blank" /></td>
									<td></td>
								</tr>
							</table>

							<div class="clear"></div>


						</div> <!--  end content-table-inner  -->
					</td>
					<td id="tbl-border-right"></td>
				</tr>
				<tr>
					<th class="sized bottomleft"></th>
					<td id="tbl-border-bottom">&nbsp;</td>
					<th class="sized bottomright"></th>
				</tr>
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