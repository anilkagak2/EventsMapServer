
<?php

function getDirectoryList ($directory) {
// create an array to hold directory list
$results = array();
// create a handler for the directory
$handler = opendir($directory);
// open directory and walk through the filenames
while ($file = readdir($handler)) {
  // if file isn't this directory or its parent, add it to the results
  if ($file != "." && $file != "..") {
	$results[] = $file;
  }
}
// tidy up: close the handler
closedir($handler);
// done!
return $results;
}
if(!isset($_GET['code']))
	die();
?>


    	<div style="position:relative;float:left;" align="center" id='mygalleryid'>
        <div id="galleria">
<?php
	$pic = $_GET['code'];
	$path = "images/campus/".$pic."/";
	$files = getDirectoryList(getcwd()."/".$path);
	$thumbpic = "thumb_".$pic.".jpg";
	if(file_exists(getcwd()."/".$path."desc_".$pic.".txt")){
		$html_text = "<a href='".$path."small_".$pic.".jpg'><img ".file_get_contents(getcwd()."/".$path."desc_".$pic.".txt")." src='".$path.$thumbpic."'/></a>";
	}
	else{
		$html_text = "<a href='".$path."small_".$pic.".jpg'><img src='".$path.$thumbpic."'/></a>";
	}
	foreach ($files as $file){
		$t = explode("_", $file);
		if($t[0] != "thumb")
			continue;
		else if(strcmp($file,$thumbpic)){
			$remaining = "small";
			$orig = "";
			for($i = 1; $i < count($t); $i++){
				$remaining .= "_".$t[$i];
				$orig .= "_".$t[$i];
			}
			//echo $orig." ";
			$ori = explode(".",$orig);
			$orig = "";
			for($i = 0; $i < count($ori) - 1; $i++){
				$orig .= $ori[$i];
			}
			//echo getcwd()."/".$path."desc".$orig.".txt";
			if(file_exists(getcwd()."/".$path."desc".$orig.".txt")){
				//echo " exists<br/>";
				$html_text .= "<a href='".$path.$remaining."'><img ".file_get_contents(getcwd()."/".$path."desc".$orig.".txt")." src='".$path.$file."'/></a>"; 
			}
			else{
				//echo " no<br/>";
				$html_text .= "<a href='".$path.$remaining."'><img src='".$path.$file."'/></a>"; 
			}
		}
	}
	echo $html_text;
?>
		</div>
    	</div>
        <?php
        if(!isset($_GET['road']) && file_exists($path."index.html")){
		?>
        <div style="position:relative;float:left;" id="picture_text_td"> 
        <div id='picture_text' style='overflow:auto;background-color:#FFF;width:240px;padding:15px;' align='left'>
            <p align="right">
                <a style="cursor:pointer;text-decoration:none;" onclick="close_overlay();"><img src="images/Cross_icon.png" width="24" style="margin-right:5px;" title='Click to close the Gallery'/></a><br />
            		<!--div id="social_buttons" style="position:relative;top:-35px;left:0px;width:200px;height:21px;">
        			</div-->
            </p>
        <div id="social_buttons">
        </div>
			<h3 id="side_heading"></h3>
			<?php include($path."index.html");?>
        </div>
        </div>
        <?php
		}
		else{
		?>
        <div style="position:relative;float:left;" id="picture_text_td"> 
        <div id='picture_text' style='overflow:auto;padding:15px;'>
        &nbsp;&nbsp;&nbsp;&nbsp;
                <a style="cursor:pointer;text-decoration:none;" onclick="close_overlay();" ><img src="images/Cross_icon.png" width="24" style="margin-right:5px;" title='Click to close the Gallery'/></a>
				<!--div id="social_buttons" style="position:relative;top:-35px;left:0px;width:200px;height:21px;">
        		</div-->
        </div>

        </div>
        <?php
		}
		?>
