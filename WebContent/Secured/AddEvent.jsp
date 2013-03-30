<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Event</title>

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
		$('#date-pick').datePicker(
		// associate the link with a date picker
		{
			createButton : false,
			startDate : '01/01/2005',
			endDate : '31/12/2020'
		}).bind(
		// when the link is clicked display the date picker
		'click', function() {
			updateSelects($(this).dpGetSelected()[0]);
			$(this).dpDisplay();
			return false;
		}).bind(
		// when a date is selected update the SELECTs
		'dateSelected', function(e, selectedDate, $td, state) {
			updateSelects(selectedDate);
		}).bind('dpClosed', function(e, selected) {
			updateSelects(selected[0]);
		});

		var updateSelects = function(selectedDate) {
			var selectedDate = new Date(selectedDate);
			$('#d option[value=' + selectedDate.getDate() + ']').attr(
					'selected', 'selected');
			$('#m option[value=' + (selectedDate.getMonth() + 1) + ']').attr(
					'selected', 'selected');
			$('#y option[value=' + (selectedDate.getFullYear()) + ']').attr(
					'selected', 'selected');
		}
		// listen for when the selects are changed and update the picker
		$('#d, #m, #y').bind('change', function() {
			var d = new Date($('#y').val(), $('#m').val() - 1, $('#d').val());
			$('#date-pick').dpSetSelected(d.asString());
		});

		// default the position of the selects to today
		var today = new Date();
		updateSelects(today.getTime());

		// and update the datePicker to reflect it...
		$('#d').trigger('change');
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
						<a href="" id="acc-details">Personal details </a>
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
							<c:set var="homePage" value="/Secured/Admin.jsp" />
						</c:when>
						<c:otherwise>
							<c:set var="homePage" value="/General/Events.jsp" />
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
										<form name="addEvent" method="post" action="AddEvent">
											<table border="0" cellpadding="0" cellspacing="0"
												id="id-form">
												<tr>
													<th valign="top">Title:</th>
													<td><input type="text" class="inp-form" name="title"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Content:</th>
													<td><input type="text" class="inp-form" name="content"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">MainLand:</th>
													<td><select name="mainland">
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
													<td><input type="text" class="inp-form" name="subland"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Start Time:</th>
													<td><input type="datetime" class="inp-form"
														name="starttime"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">End Time:</th>
													<td><input type="datetime" class="inp-form"
														name="endtime"></td>
													<td>
														<div class="error-left"></div>
														<div class="error-inner">This field is required.</div>
													</td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Status:</th>
													<td><select class="styledselect_form_1" name="status">
															<option value="1">ONGOING</option>
															<option value="2">SCHEDULED</option>
															<option value="3">CANCELLED</option>
															<option value="4">COMPLETED</option>
													</select></td>
													<td></td>
												</tr>

												<tr>
													<th valign="top">Category:</th>
													<td><select name="category">
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
													<th valign="top">Select a date:</th>
													<td class="noheight">

														<table border="0" cellpadding="0" cellspacing="0">
															<tr valign="top">
																<td>
																	<form id="chooseDateForm" action="#">

																		<select id="d" class="styledselect-day">
																			<option value="">dd</option>
																		</select>
																</td>
																<td><select id="m" class="styledselect-month">
																		<option value="">mmm</option>
																</select></td>
																<td><select id="y" class="styledselect-year">
																		<option value="">yyyy</option>
																</select>
																	</form></td>
																<td><a href="" id="date-pick"><img
																		src="images/forms/icon_calendar.jpg" alt="" /></a></td>
															</tr>
														</table>

													</td>
													<td></td>
												</tr>

												<tr>
													<th>&nbsp;</th>
													<td valign="top"><input type="button" value=""
														class="form-submit" /> <input type="reset" value=""
														class="form-reset" /></td>
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