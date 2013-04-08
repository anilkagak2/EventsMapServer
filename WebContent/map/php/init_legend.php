<?php
/**
 * This page is for the legends part of the map.
 */
?>
<style type="text/css">
    .bullet-arrow {
    vertical-align: text-top;
    }
</style>
<script type="text/javascript">
	function toggle_legends(legend){
		var expands = ["hostel_legends","faculty_legends"];
		if(!$("#"+legend).is(':visible')){
			$("#"+legend).show('slow');
			$("#"+legend+"_arrow").attr("src", "images/arrow_down.png");
		}
		else{
			$("#"+legend).hide('slow');
			$("#"+legend+"_arrow").attr("src", "images/arrow_right.png");
		}
		for(var i = 0; i < expands.length; i++){
			if(expands[i] != legend){
				if($("#"+legend).is(':visible')){
					$("#"+expands[i]).hide('slow');
					$("#"+expands[i]+"_arrow").attr("src", "images/arrow_right.png");
				}
			}
		}
	}
    
    
</script>
<div id="legend_box" style='position:absolute;right:0px;top:300px;'>
	<div id="show_legend" style='background-color:#0087CC;border:thin;border-top-left-radius:15px;border-bottom-left-radius:15px;width:30px;cursor:pointer;float:left;font-family: Arial, sans-serif;'>
    <span style="font-size:14px;font-weight:bold;color:white;">
    	<br />

        &nbsp;&nbsp;&nbsp;I<br />
        &nbsp;&nbsp;n<br />
        &nbsp;&nbsp;d<br />
        &nbsp;&nbsp;e<br />
        &nbsp;&nbsp;x<br />
		<br />

       </span>
    </div>
    <div id="legends_list" style="float:left;display:none;border:thin;border-bottom-left-radius:15px;background-color:#DFDFDF;padding: 10px;">
        	<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(251,463);">Academic Complex</a><br />
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(250,389);">Lecture Theaters</a><br />
			<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(285,382);">Library &amp; Computer Center</a><br />           
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(264,362);">Adminstrative Building</a><br />
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(336,206);">Guest House</a><br />            
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(286,336);">Auditorium</a><br />
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(271,333);">Conference Hall</a><br />
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(524,281);">Technology Complex</a><br />            
            <div>
            	<a style="text-decoration:none;cursor:pointer;" onclick="toggle_legends('faculty_legends');">Faculty Quarters<img id="faculty_legends_arrow" src="images/arrow_right.png" class="bullet-arrow"/> </a>
            	<div id="faculty_legends" style="display:none">
                	&nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(380,83);">A Type</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(358,108);">B Type</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(271,113);">C Type</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(231,179);">D Type</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(150,266);">E Type</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(301,201);">F Type</a><br />
                </div>
            </div>
            <div>
            	<a style="text-decoration:none;cursor:pointer;" onclick="toggle_legends('hostel_legends');">Hostels<img id="hostel_legends_arrow" src="images/arrow_right.png" class="bullet-arrow"/> </a>
            	<div id="hostel_legends" style="display:none">
                	&nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(511,378);">Barak</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(435,460);">Brahmaputra</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(379,431);">Dibang</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(450,413);">Dihing</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(511,352);">Kameng</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(375,400);">Kapili</a><br />                    
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(458,390);">Manas</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(412,222);">Married Scholars</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(382,380);">Siang</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(338,283);">Subansiri</a><br />
                    &nbsp;&nbsp;&nbsp;<a style="text-decoration:none;cursor:pointer;" onclick="go_legend(511,407);">Umiam</a><br />                                                                           
                </div>
            </div>
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(376,321);">SAC</a><br />
            <a style="text-decoration:none;cursor:pointer;" onclick="go_legend(383,178);">Hospital</a><br />
            
    </div>
    <input type="hidden" id="legend_state" value="0"/>
</div>
