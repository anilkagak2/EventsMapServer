<?php
/**
 * This file contains the code for $(document).ready functions and other event binding stuff
 */
?>

	<script type="text/javascript"> 
		$(document).ready(function() {
			/**
			 * this part initializes the map and can be used to set additional rules
			 */
			$("#viewport").mapbox({ 
				mousewheel: true, 
				layerSplit: 16//smoother transition for mousewheel 
			}); 
			/// This function is for creating the clickable areas on the map
			$("#viewport").mapbox("createDivs");
			/// the next two lines are for setting up the box in the mini map
			$("#viewport").mapbox("zoom", 1);
			$("#viewport").mapbox("back", 1);
			//$("#viewport").mapbox("left", 200);
			//$("#viewport").mapbox("down", 80);
			/**
			 * This function is used for defining the actions of the control box on the top right side of the map
			 */
			jQuery(".map-control a").click(function() {//control panel 
				var viewport = $("#viewport"); 
				//this.className is same as method to be called 
				if(this.className == "zoom" || this.className == "back") { 
					viewport.mapbox(this.className, 2);//step twice 
				} 
				else { 
					viewport.mapbox(this.className); 
				} 
				return false; 
			}); 
            /**
			 * This block will open the gallery if the url has #!<gallery name> part in it
			 */
			 <?php
			 if(isset($_GET['gallery'])){
				 ?>
				$('#overlay').fadeIn('fast',function(){
					$('#box').animate({'top':'0px'},500);
				});
				$('#overlay_code').val('<?php echo $_GET['gallery'];?>');
				$('#visit_by_hash').val("1");
			 	getPicInfo('<?php echo $_GET['gallery'];?>');
				overlay();
				<?php
			 }
           /* if (window.location.hash.length)  
            {
                code = window.location.hash.replace("#!","");
				if(code != ""){
					//alert(code);
					$('#overlay_code').val(code);
					$('#visit_by_hash').val("1");
					getPicInfo(code);
					overlay();
				}
            }*/
			?>
            $("#about").colorbox({
                onClosed:function(){ //do something 
                }
            });
            
            $("#feedback").colorbox();
		}) ;
		/**
		 * This will hide and show the legend box on the right side
		 */
		$("#show_legend").click(function(){
			if(document.getElementById('legend_state').value == '1')
		  		$('#legend_state').val('0');
			else
				$('#legend_state').val('1');
		  	$("#legends_list").toggle('slow');
		}); 
		//$("#viewport").mapbox("zoom", 3);//zooms in 2 levels 
		//$("#viewport").mapbox("center");//centers current layer 
		/**
		 * This will open the gallery view on clicking #activator
		 */
		$(function() {
			$('#activator').click(function(){
				$('#overlay').fadeIn('fast',function(){
					$('#box').animate({'top':'0px'},500);
				});
			});
			/*$('#boxclose').click(function(){
				$('#box').animate({'top':'-2000px'},500,function(){
					$('#overlay').fadeOut('fast');
				});
			});*/
		 
		});
		/**
		 * The functions to be called when window resizes
		 */
		$(window).resize(function() {
		  //update stuff
		  update_galleria();
			/*var gheight = Math.floor($(window).height() * 0.9) < 400? 400: Math.floor($(window).height() * 0.9);
			var gwidth = Math.floor($(window).width() * 0.65) < 600 ? 600: Math.floor($(window).width() * 0.65);
		  Galleria.resize({width:gwidth,height:gheight},null);
		  Galleria.rescale();*/
		});
		/**
		 * Adding the feature of closing the gallery by pressing the escape button
		 */
		$(document).keyup(function(e) {
			if (e.keyCode == 27) {
				close_overlay();
			}   // esc
		});
		if(!$.browser.mozilla){
			$('#pointer_div').mousedown(function(){document.getElementById("hid").value="1";$('#box_id').hide();});
			$('#pointer_div').mouseup(function(){document.getElementById('hid').value='0';point_it2(event);$('#box_id').show();$("#viewport").mapbox("left", 1);$("#viewport").mapbox("right", 1);});
		}
		$(window).load(function() {
			$('#loading-image').hide();
			//$("#all_content").show();
		});
		if (!(typeof history.pushState === "function")){
			$("#sbuttons").css({left:900});
		}
		disableSelection(document.getElementById('legends_list'));
		disableSelection(document.getElementById('show_legend'));
		disableSelection(document.getElementById('hq_lq'));
	</script>
<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=satyadeepk"></script>