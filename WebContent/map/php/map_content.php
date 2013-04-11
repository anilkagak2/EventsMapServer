								<div class="iitg_map">
									<div class="map_help">
										<ul>
                                        <li>Zoom in or zoom out by using the mouse wheel.</li>
										<li>You can drag the map using the mouse.</li>
                                        <li>Click on a place to view the gallery.</li>
                                        <li>Use Index to quick find a place.</li>
										</ul>
                                        
                                        <br/>
											<a href="http://www.iitg.ac.in/aboutus" target="_blank">About IIT Guwahati</a><br/>
											<a href="http://www.iitg.ac.in/visitorsinfo" target="_blank">Visitors Information</a><br/>
                                            <a href="http://www.iitg.ac.in/faq" target="_blank">FAQ</a><br/>
											<a href="files/bus-timings-2010.pdf" target="_blank">Bus Timings</a><br/>
                                            

                                    </div><!-- map_help -->
									<div id="mapwrapper" class="mapwrapper">
										<div id="viewport">
											<!--div id="v2" style="width: 1458px; height: 1583px;">
												<img src="images/layer2.png" alt="" />
												<div id="bv2" class="mapcontent">
													<!--map content goes here->
												</div>
											</div-->
                                        	<?php
											/**
											 * this portion decides which mtype of map to show.
											 */
											if(!isset($_GET['hq'])){
											?>
											<div id="v1" style="background: url(images/layer1.jpg) no-repeat; width: 644px; height: 700px;">
												<!--top level map content goes here-->
											</div>
											<div id="v3" style="width: 2916px; height: 3165px;">
												<img src="images/layer3.jpg" alt="" />
												<div id="bv3" class="mapcontent">
													<!--map content goes here-->
												</div>
											</div>
                                            <?php
											}
											else{
											?>
											<div id="v1" style="background: url(images/layer1.png) no-repeat; width: 644px; height: 700px;">
												<!--top level map content goes here-->
											</div>
											<div id="v4" style="width: 5832px; height: 6329px;">
												<img src="images/layer4.png" alt="" />
												<div id="bv4" class="mapcontent">
													<!--map content goes here-->
												</div>
											</div>
											<?php
											}
											?>
										</div><!-- viewport -->
                                        <!--
                                        	anything that needs to be shown on the map should be placed from here on.
                                        -->
										<div id="map_control" class="map-control" style='z-index:100;'>
											<a href="#left" class="left">Left</a>
											<a href="#right" class="right">Right</a>
											<a href="#up" class="up">Up</a>
											<a href="#down" class="down">Down</a>
											<a href="#zoom" class="zoom">Zoom</a>

											<a href="#zoom_out" class="back">Back</a>
										</div><!-- map-control -->
                                        <div style="z-index:99;position:absolute;top:10px;left:10px;height:25px;border:1px solid;border-radius:3px;">
                                        <table id="hq_lq">
                                        <tr>
                                        <?php
											/**
											 * This is for the button for selecting hq & lq
											 */
                                            if(!isset($_GET['hq'])){
                                       ?>
                                            <td style="background-color:grey;">
                                        		LQ
                                            </td>
                                            <td style="background-color:white;">
                                        		<a style="text-decoration:none" href = "index.php?hq">HQ</a>
                                            </td>       
                                        <?php
                                            }
                                            else{
                                        ?>
                                            <td style="background-color:white;">
                                        		<a style="text-decoration:none" href = "index.php?lq">LQ</a>
                                            </td>
                                            <td style="background-color:grey;">
                                        		HQ
                                            </td>  
                                        <?php
                                            }
                                        
                                        ?>
                                        </tr>
                                        </table>
                                        </div>
                                        <div id="link_holder"></div>
									</div><!-- mapwrapper -->
									<input type="hidden" id="hid" value="0" />
									<div class = "pointer_text" style="width:360px;">
                                     
    <div id="sbuttons" style="position:relative;overflow:visible;">
<!-- AddThis Button BEGIN -->
        <div class="addthis_toolbox addthis_default_style ">
        <a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
        <a class="addthis_button_tweet"></a>
        <a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
        <a class="addthis_counter addthis_pill_style"></a>
        </div>                                            
<!-- AddThis Button END -->   
    </div>
    								<br>
                                    <b>Click on any part of this image to go to that location.</b>
									</div><!-- pointer_text -->

									<div id="pointer_div" class="pointer_class" onclick="point_it(event)" onmousedown="document.getElementById('hid').value='1';" onmouseup="document.getElementById('hid').value='0';point_it2(event);" onmousemove="point_it1(event);" >
										<div class="box_class" id="box_id" name="box_ie"></div>
									</div><!-- pointer_div -->
									<div class="print_map" style="width:100px;"><input type="hidden" id="map_address" value="images/print_map.jpg"/><input type="button" value=" Print Map " onClick="print_map();" />
                                    </div>
								</div> <!-- iitg_map --> 