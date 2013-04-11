<!doctype html>
<html>
<head>
	<meta charset="utf-8">
		<title>IIT Guwahati Campus Map</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon" />
<?php include('php/css.php');flush();?>
</head>
<body class="not-logged-in front full-node node-type-page layout-main preface-first">
<style type="text/css">
#loading-image {
	background-color: #EEE498;
	position: fixed;
	top: 0px;
	left:50%;
	z-index: 101;
	border-radius: 0px 0px 10px 10px; /* future proofing */
	
}
</style>
<div id="loading-image"><p align="center" style="vertical-align:top">
	<!--img src="images/ajax-loader.gif" /><br/-->
    &nbsp;&nbsp;&nbsp;<span style="font-size:12px;color:#555;font-weight:bold;">Loading...</span>&nbsp;&nbsp;&nbsp;
    </p>
</div>
<!--div id="all_content" style="display:none;"-->
<?php
	include('php/body_main_start.php');flush();
	include('php/map_content.php');flush();
	include('php/body_main_end.php');flush();
	include('php/init_parts.php');flush();
	include('php/init_legend.php');flush();
	include('php/js_includes.php');flush();
	/*?>
    </div>
    <?php*/
	include('php/document_ready.php');flush();
?>

</body>
</html>