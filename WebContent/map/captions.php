<?php
include('php/auth.php');
?>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IITG Campus Map - Admin</title>
</head>
<body>
<?php
	$cur_folder = "/images/campus";
	if(isset($_GET['dir']))
		$cur_folder = $_GET['dir'];
	if(isset($_POST['submit']) && isset($_POST['data-description']) && isset($_POST['filename']) && isset($_POST['data-title'])){
		if($_POST['data-title'] != "" || $_POST['data-description'] != ""){
			$fh = fopen(getcwd().$cur_folder."/".$_POST['filename'], 'w');
			if($_POST['data-title'] != "")
				fwrite($fh, "data-title=\"".str_replace('"',"'",strip_tags($_POST['data-title']))."\"\n");
			if($_POST['data-description'] != "")
				fwrite($fh, "data-description=\"".str_replace('"',"'",strip_tags($_POST['data-description']))."\"\n");
			fclose($fh);
			echo "<br/>details added successfully<br/>";
		}
		else{
			echo "<br/>";
			unlink(getcwd().$cur_folder."/".$_POST['filename']);
			echo "<br/>details erased successfully<br/>";
		}
	}
?>
<style type="text/css">
a{
	text-decoration:none;
}
</style>
<script type="text/javascript">
function load_pic(pic){
	document.getElementById("preview_pic").src = pic;
}
function load_file(addr,filename){
	document.getElementById("filename").value = filename;
	/*var xmlhttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			document.getElementById("data-description").innerHTML = "";
			document.getElementById("data-title").value = "";
			var parts = xmlhttp.responseText.split('\n');
			for(var i = 0; i < parts.length; i++){
				var s = parts[i].split('=');
				if(s[0] == "data-title"){
					document.getElementById(s[0]).value = parts[i].substring(12,parts[i].length - 1);
				}
				else if(s[0] == "data-description")
					document.getElementById(s[0]).innerHTML = unescape(parts[i].substring(18,parts[i].length - 1));
			}
		}
	}
	xmlhttp.open("GET",addr,true);
	xmlhttp.send();
	*/
}
function load_default(file){
	document.getElementById("data-description").innerHTML = "";
	document.getElementById("data-title").value = "";
	document.getElementById("filename").value = file;
}
</script>

<table>
	<tr>
    	<td>
        <h2>IITG Campus Map - Admin</h2>
        	<p><b>List of files:</b></p>
<?php
 	if ($handle = opendir(getcwd().$cur_folder)) {
		while (false !== ($file = readdir($handle))){
			if ($file != "."){
				if($file == ".."){
					$parts = explode('/',$cur_folder);
					if(count($parts) <= 3)
						continue;
					echo '<a href="?dir='.substr($cur_folder,0,-(2+strlen($parts[count($parts) - 1])-1)).'">';
					echo'<img src="images/folder-icon.png"> '.$file.'</a><br/>';
				}
				else if(is_dir(getcwd().$cur_folder."/".$file)){
					echo '<a href="?dir='.$cur_folder."/".$file.'">';
					echo '<img src="images/folder-icon.png"> '.$file.'</a><br/>';
				}
				else{
					$parts = explode('.',$file);
					if($parts[count($parts) - 1] == "jpg"){
						$srls = explode('_',$file);
						if($srls[0] == "thumb"){
							$txtfile = "desc_".substr($file,6,-4).".txt";
							//echo $txtfile;
							if(file_exists(getcwd().$cur_folder."/".$txtfile)){
								echo "<b style='cursor:pointer' onclick=\"load_pic('".$cur_folder."/".$file."');load_file('".$cur_folder."/".$txtfile."','".$txtfile."');\">";
							}
							else{
								echo "<b style='cursor:pointer' onclick=\"load_pic('".$cur_folder."/".$file."');load_default('".$txtfile."');\">";
							}
							
							echo "<img src='images/icon-post-views.png'/> ".$file."</b><br/>";
							
						}
					}
				}
			}
		}
		closedir($handle);
  	}
?>
		</td>
    </tr>
</table>
<div style="position:absolute;top:40px;left:325px;">
		<a href="?logout">Logout</a><br>
		<div style="position:relative;height:200px;width:300px;border:thin;overflow:hidden">
        <img id="preview_pic"/></div>
        <form action="?dir=<?php echo $cur_folder."/".$file;?>" method="post">
        Submit empty Title and Description to delete the caption file
        <table>
        <tr>
        	<td align="right">Filename :</td>
            <td align="left"> <input type="text" value="" name="filename" id="filename" readonly/></td>
        </tr>
        <tr>
        	<td align="right">Title:</td>
            <td align="left"> <input type="text" value="" name="data-title" id="data-title"/></td>
        </tr>
        <tr>
        	<td align="right">Description (opt):</td>
            <td align="left"> <textarea name="data-description" id="data-description"></textarea></td>
        </tr>
        </table>
        <input type="submit" name="submit" value="Submit"/><br>
        </form>
</div>
</body>
</html>