<?php
/**
 * This page contains the necessary div's for the completion of map,
 * They need to be initialized here if they are to work properly
 */
?>
<style type="text/css">
.chat-bubble-arrow-border {
  border-color: #666666 transparent transparent transparent;
  border-style: solid;
  border-width: 10px;
  height:0;
  width:0;
  position:absolute;
  bottom:-22px;
  left:50%;
}
.chat-bubble-arrow {
  border-color: #EDEDED transparent transparent transparent;
  border-style: solid;
  border-width: 10px;
  height:0;
  width:0;
  position:absolute;
  bottom:-19px;
  left:50%;
}
</style>
  	<div id="previewer" onmouseover="document.getElementById('previewer_on').value='1';" onmouseout="document.getElementById('previewer_on').value='0';" style="z-index:100;border:1px grey solid;box-shadow:0px 0px 3px 3px grey;border-radius:5px;background-color:white;position:absolute;"></div>
    <div id="duplicate" style="border-radius:3px;position:absolute;display:block;"></div>
    
<div class="overlay" id="overlay" style="display:none;"></div>

<div class="box" id="box">
 <!--a id="tweet_button" href="https://twitter.com/share" class="twitter-share-button" style="position:relative;top:50px;left:800px;">Tweet</a-->
    <!--h1>Important message</h1>
    <p>
    Here comes a very important message for your user.
    Turn this window off by clicking the cross.
    </p-->
    <!--table id="mygallerytable"><tr>
    	<td align="center" id='mygalleryid'>
        <div id="galleria">
		</div>
    	</td>
        <td id="picture_text_td">
        <div id='picture_text'>    
        	<p align="right">
        		<a style="cursor:pointer;text-decoration:none;" onclick="close_overlay();">X</a><br />
            		<!--div id="social_buttons" style="position:relative;top:-35px;left:0px;width:200px;height:21px;">
        			</div->
    		</p>
        </div>
        </td>
    </tr></table-->
    <div id="mygallerytable" style="position:relative;">
    	<div style="position:relative;float:left;" id='mygalleryid'>
            <div id="galleria">
            </div>
        </div>
        <div style="position:relative;float:left;" id="picture_text_td">
            <div id='picture_text'>    
                <p align="right">
                    <a style="cursor:pointer;text-decoration:none;" onclick="close_overlay();">X</a><br />
                        <!--div id="social_buttons" style="position:relative;top:-35px;left:0px;width:200px;height:21px;">
                        </div-->
                </p>
            </div>
        </div>
    </div>
</div>
<!--script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script-->
	<input type="hidden" id="overlay_code" value="" />
    <input type="hidden" id="previewer_on" value="0" />
    <input type="hidden" id="previewer_off" value="0" />
    <input type="hidden" id="visit_by_hash" value="0" />
    <!--input type="hidden" id="sidebar_heading" value="" /-->
    <div style="display:none">
    <img style='background:url(images/loading.gif) no-repeat;'/>
    </div>