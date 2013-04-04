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
<title>Add Event</title>

<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

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
			format: 'yyyy-MM-dd hh:mm:ss',
		});
		
		$('#endDatetimepicker').datetimepicker({
			language : 'en',
			format: 'yyyy-MM-dd hh:mm:ss',
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
						<a href="" id="acc-settings">Settings</a>
						<div class="clear">&nbsp;</div>
						<div class="acc-line">&nbsp;</div>
						<a href="/FetchDetails" id="acc-details">Personal details </a>
					</div>
				</div>
				<!--  end account-content -->

			</div>
			<!-- end nav-right -->


			<!--  start nav -->
			<div class="nav">
				<div class="table">

					<ul class="select">
						<li><a href="#nogo"><b>Dashboard</b> <!--[if IE 7]><!--></a>
							<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
							<div class="select_sub">
								<ul class="sub">
									<li><a href="#nogo">Dashboard Details 1</a></li>
									<li><a href="#nogo">Dashboard Details 2</a></li>
									<li><a href="#nogo">Dashboard Details 3</a></li>
								</ul>
							</div> <!--[if lte IE 6]></td></tr></table></a><![endif]--></li>
					</ul>

					<div class="clear"></div>

					<c:choose>
						<c:when test="${sessionScope.loginId == 1}">
							<c:set var="homePage" value="Secured/Admin.jsp" />
						</c:when>
						<c:otherwise>
							<c:set var="homePage" value="General/Events.jsp" />
						</c:otherwise>
					</c:choose>

					<ul class="select">
						<li><a href="${homePage}"><b>Back To Home</b> <!--[if IE 7]><!--></a>
							<!--<![endif]--></li>
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
				<h1>Add Event</h1>
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
									<td><c:if test="${not empty sessionScope.error}">
											<h2>ERROR! ${sessionScope.error}</h2>
											<c:remove var="sessionScope.error" />
										</c:if> <!-- start id-form -->
										
										<c:choose>
											<c:when test="${not empty updateEvent}">
												<c:set var="Utitle" 	value="${updateEvent.title}" />
												<c:set var="UeventId" 	value="${updateEvent.eventId}" />
												<c:set var="Ucontent" 	value="${updateEvent.content}" />
												<c:set var="Umainland" 	value="${updateEvent.mainLand}" />
												<c:set var="Usubland" 	value="${updateEvent.subLand}" />
												<c:set var="Ustatus" 	value="${updateEvent.status}" />
												<c:set var="Ucategory" 	value="${updateEvent.category}" />												
												<fmt:formatDate var="Ustarttime" value="${updateEvent.startTime}" 	pattern="yyyy-MM-dd HH:mm:ss" />
												<fmt:formatDate var="Uendtime" 	value="${updateEvent.endTime}" 		pattern="yyyy-MM-dd HH:mm:ss" />
					
												<c:set var="Uaction" 	value="UPDATE" />
											</c:when>
											<c:otherwise>
												<c:set var="Utitle" 	value="" />
												<c:set var="UeventId" 	value="" />
												<c:set var="Ucontent" 	value="" />
												<c:set var="Umainland" 	value="" />
												<c:set var="Usubland" 	value="" />
												<c:set var="Ustatus" 	value="" />
												<c:set var="Ucategory" 	value="" />
												<c:set var="Ustarttime" value="" />
												<c:set var="Uendtime" 	value="" />
												<c:set var="Uaction" 	value="INSERT" />
											</c:otherwise>
										</c:choose>
										
										<form name="addEvent" method="post" action="AddEvent">
											<table border="0" cellpadding="0" cellspacing="0"
												id="id-form">
												<tr>
													<th valign="top">Title:</th>
													<td><input type="text" class="inp-form" name="title" value="${Utitle}"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Content:</th>
													<td><input type="text" class="inp-form" name="content" value="${Ucontent}"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">MainLand:</th>
													<td><select name="mainland" selected="${Umainland}">
															<c:forEach items="${mainland}" var="mainland">
																<option value="${mainland.mainLandId}">
																	<c:out value="${mainland.mainLand}" />
																</option>
															</c:forEach>
													</select>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">SubLand:</th>
													<td><input type="text" class="inp-form" name="subland" value="${Usubland}"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Start Time:</th>
													<td class="noheight">
														<div class="well">
															<div id="startDatetimepicker" class="input-append">
																<input data-format="yyyy-MM-dd HH:mm:ss" name="starttime" value="${Ustarttime}" type="text"></input>
																
																<span class="add-on"> <i
																	data-time-icon="icon-time"
																	data-date-icon="icon-calendar"> </i>
																</span>
															</div>
														</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">End Time:</th>
													<td class="noheight">
														<div class="well">
															<div id="endDatetimepicker" class="input-append">
																<input data-format="yyyy-MM-dd HH:mm:ss" name="endtime" value="${Uendtime}" type="text"></input>
																<span class="add-on"> <i
																	data-time-icon="icon-time"
																	data-date-icon="icon-calendar"> </i>
																</span>
															</div>
														</div>
													</td>
													<td></td>
												</tr>


												<tr>
													<th valign="top">Status:</th>
													<td><select class="styledselect_form_1" name="status" selected="${Ustatus}">
															<option value="1">ONGOING</option>
															<option value="2">SCHEDULED</option>
															<option value="3">CANCELLED</option>
															<option value="4">COMPLETED</option>
													</select></td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Category:</th>
													<td><select name="category" selected="${Ucategory}">
															<c:forEach items="${category}" var="category">
																<option value="${category.categoryId}">
																	<c:out value="${category.category}" />
																</option>
															</c:forEach>
													</select>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>



												<tr>
													<th>&nbsp;</th>
													<td valign="top">
														<input type="hidden" value="${Uaction}" name="action"/>
														<input type="hidden" value="${UeventId}" name="eventId"/>
														<input type="submit" value="Submit" style="width:100px;height:40px"/>
													 	<input type="reset" value="Reset" style="width:100px;height:40px" />
													 </td>
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