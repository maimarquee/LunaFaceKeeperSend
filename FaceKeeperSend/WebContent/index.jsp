<%@page import="javax.mail.Session"%>
<%@page import="com.facekeeper.dto.MessageDTO"%>
<%@page import="java.awt.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ page import="com.facekeeper.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
	<script type="text/javascript" src="common/jquery.min.js"></script>
	<script type="text/javascript" src="common/webcam/webcam.js"></script>
	<script type="text/javascript" src="common/technopal/general.js"></script>
	<!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="common/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<jsp:include flush="true" page="css.jsp"></jsp:include>
	<link type="text/css" href="common/css/docs.css" rel="stylesheet" media="all" />
	<link type="text/css" href="common/css/jquery.marquee.css" rel="stylesheet" title="default" media="all" />
</head>

<body>
<div id="myCamera" style="display:none"></div>
<input type="hidden" id="txtGroupNum" value="1" />
<%
if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)) {
%>
    <input class="form-control input-sm text-right" type="text" maxlength="11" placeholder="Input your CP# here. eg 09181234567 and press enter. FaceKeeper recommend to use Firefox browser." id="txtRFID" onkeypress="executeTap(event, this.value)">
    <script>
	    function executeTap(e, val) {
	        if(e.keyCode === 13) {
	        	e.preventDefault(); // Ensure it is only this code that runs
	           	tap(val, document.getElementById("txtGroupNum").value);
	        }
	    }
	</script>
<%
}
else {
%>
	<input type="hidden" id="txtRFID" />
<%	
}
%>
<div class="container" style="margin-top: 15px;" >
	<div class="col-sm-6 pull-left no-padding" style="height:87%;overflow:hidden;position: relative;">
		<div class="" style="width:107%;height:100%; padding-right:8%;overflow:hidden;">
			<div id='pict2' class='container col-sm-12 text-center'></div>
			<div id='pict3' class='container col-sm-12 text-center'></div>
			<div id='pict4' class='container col-sm-12 text-center'></div>
		</div>
	</div>
	<div id='pict1' class="col-sm-6 pull-right"></div>
</div>

<div class="footer navbar-fixed-bottom" style="background:<%=SettingsUtil.OWNER_PRIMARY_COLOR%>">
	<%
	if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)) {
	%>
		<marquee><h1 style="color:white;">FaceKeeper Demo Version Powered by Technopal Software. For inquiries please email mytechnopal@gmail.com.</h1></marquee>	
	<%	
	}
	else {
	%>
		<ul id="marquee3" class="marquee"></ul>
	<%
	}
	%>
</div>	

<script>
Webcam.set({
	width: 320,
	height: 250,
	image_format: 'jpg',
	jpeg_quality: 90,
});
Webcam.attach('#myCamera');
</script>

<script>
function showAlert(msg, duration) {
	 var el = document.createElement("div");
	<%--  if(msg.substring(0, 14) =="Double Topped!") {
		 el.setAttribute("style","text-align:center;position:absolute;top:75%;left:45%;padding:30px 60px;border-radius:10px;background-color:<%=SettingsUtil.OWNER_PRIMARY_COLOR%>;box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);font-weight:600;color:white; opacity:0.8;");
	 } else { --%>
	el.setAttribute("style","text-align:center;position:absolute;top:75%;left:70%;padding:20px 50px;border-radius:10px;background-color:<%=SettingsUtil.OWNER_PRIMARY_COLOR%>;box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);font-weight:600;color:white; opacity:0.8;");
	 //}
	 el.innerHTML = "<h4>" + msg + "</h4>";
	 setTimeout(function() {
	 	el.parentNode.removeChild(el);
	 	}, duration);
	 document.body.appendChild(el);
}

function tap() {
	if(<%=SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)%>) {
		if(isValidCPNumber($('#txtRFID').val())) {
			displayFace();
		}
		else {
			showAlert("Cellphone number is invalid", 5000);
		}
	}
	else {
		displayFace();
	} 
}

function displayFace() {
	if($('#txtRFID').val().length == 0) {
		showAlert("RFID is Empty", 5000);
	}
	else if($('#txtRFID').val().length < 10) {
		showAlert("RFID is Invalid", 5000);
	}
	else {
		Webcam.snap(function(dataUri) {
			$.ajax({
		    	url: 'AjaxController',
		    	data: {
		    		rfid: $('#txtRFID').val().substring(0, 10),
		    		groupNum: $('#txtGroupNum').val(),
		    		pict: dataUri
		    	},
		    	method: 'POST',
		    	dataType: 'JSON',
		    	success: function(response) {
		    		if(response.errMsg) {
		    			showAlert(response.errMsg, 5000);
		    		}
		    		else {
		    			$("#txtGroupNum").val(response.nextGroupNum);
			    		$("#pict4").html($("#pict3").html());
				    	$("#pict3").html($("#pict2").html());
				    	$("#pict2").html($("#pict1").html());
				    	$("#pict1").html(getHTMLStr(response.isIn, response.timeInPict, response.profilePict, response.firstName, response.lastName, response.timestamp));
		    		}
		    	}
			});	
		});
	}
	$('#txtRFID').val("");
}

function getHTMLStr(isIn, timeInPict, profilePict, firstName, lastName, timestamp) {
	inOutStr = "OUT";
	inOutClass = "is_out";
	if(isIn) {
		inOutStr = "IN";
		inOutClass = "is_in";
	}
	return "<div class='pict_base'>"
			+ "		<div class='face_log'>"
			+ "			<img id='face_log' src='" +  timeInPict +"'>"
			+ "		</div>"
			+ "		<div class='bottom_part'>"
			+ "			<div class='avatar'>"
			+ "				<img id='avatar' src='" + profilePict +"'>"
			+ "			</div>"
			+ "			<div class='details'>"
			+ "   			<p class='firstname'>" + firstName + "</p>"
			+ "   			<p class='lastname'>" + lastName + "</p>"
			+ "   			<p class='timestamp'>" + timestamp + "</p>"
			+ "			</div>"
			+ "		</div>"
			+ "		<div class='" + inOutClass + "'>" + inOutStr + "</div>"
			+ "</div>";
}

<%
if(!SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)) {
%>
	$(document).on('keyup', function(e){
		if(e.keyCode == 13) {
			tap();
		}
		else {
			$("#txtRFID").val($("#txtRFID").val() + String.fromCharCode(e.keyCode));
		}
	})
<%
}
%>
</script>
<script type="text/javascript">
	var contents = "";
	$(document).ready(function (){
		ajax();
		readyMarquee();
	});
	
	function readyMarquee() {
		setTimeout(function() {
			$('#marquee3').html(contents);
			$(".marquee").marquee({
				loop: -1
			});
		},1000);
	}
	
	var iNewMessageCount = 0;
	
	function addMessage(selector){
		iNewMessageCount++;
		var $ul = $(selector).append("<li>New message #" + iNewMessageCount + "</li>");
		$ul.marquee("update");
	}
	
	(function($){
		$.marquee = {version: "1.0.01"};
		$.fn.marquee = function(options) {
			var method = typeof arguments[0] == "string" && arguments[0];
			var args = method && Array.prototype.slice.call(arguments, 1) || arguments;
			var self = (this.length == 0) ? null : $.data(this[0], "marquee")
			if( self && method && this.length ){		
				if( method.toLowerCase() == "object" ) return self;
				else if( self[method] ){
					var result;
					this.each(function (i){
						var r = $.data(this, "marquee")[method].apply(self, args);
						if( i == 0 && r ){
							if( !!r.jquery ){
								result = $([]).add(r);
							} else {
								result = r;
								return false;
							}
						} else if( !!r && !!r.jquery ){
							result = result.add(r);
						}
					});
					return result || this;
				} else return this;
			} else {
				return this.each(function (){
					new $.Marquee(this, options);
				});
			};
		};
	
		$.Marquee = function (marquee, options){
			options = $.extend({}, $.Marquee.defaults, options);
			var self = this, $marquee = $(marquee), $lis = $marquee.find("> li"), current = -1, loop_count = 0;
			$.data($marquee[0], "marquee", self);
			this.update = function (){
				var iCurrentCount = $lis.length;
				$lis = $marquee.find("> li");
			}
	
			function show(i){
				if( $lis.filter("." + options.cssShowing).length > 0 ) return false;
				var $li = $lis.eq(i);
				if( $.isFunction(options.beforeshow) ) options.beforeshow.apply(self, [$marquee, $li]);
				var params = {
					top: (options.yScroll == "top" ? "-" : "+") + $li.outerHeight() + "px"
					, left: 0
				};
				
				$marquee.data("marquee.showing", true);
				$li.addClass(options.cssShowing);
				$li.css(params).animate({top: "0px"}, options.showSpeed, options.fxEasingShow, function (){
					if( $.isFunction(options.show) ) options.show.apply(self, [$marquee, $li]);
					$marquee.data("marquee.showing", false);
					scroll($li);
				});
			}
	
			function scroll($li, delay){
				delay = delay || options.pauseSpeed;
				var delayTimeLessThanHalfOfMarquee = Math.round((contents.length - 39) * options.timePerCharacterLessThanHalfOfMarquee/1000)*1000;
				var delayTimeMoreThanHalfOfMarquee = Math.round((contents.length - 39) * options.timePerCharacterMoreThanHalfOfMarquee/1000)*1000;
				setTimeout(function (){
					 if ($li.outerWidth() < ($marquee.innerWidth() / 2)){
						 setTimeout(function (){
							$li.animate({top: (options.yScroll == "top" ? "+" : "-") + $marquee.innerHeight() + "px"}, options.showSpeed, options.fxEasingScroll);
							finish($li);
						}, delayTimeLessThanHalfOfMarquee);
					}  else if ($li.outerWidth() > ($marquee.innerWidth() / 2) && $li.outerWidth() < $marquee.innerWidth()) {
						setTimeout(function (){
							$li.animate({top: (options.yScroll == "top" ? "+" : "-") + $marquee.innerHeight() + "px"}, options.showSpeed, options.fxEasingScroll);
							finish($li);
						}, delayTimeMoreThanHalfOfMarquee);
					}else {
						setTimeout(function (){
							var width = $li.outerWidth(), endPos = width * -1, curPos = parseInt($li.css("left"), 10);
							$li.animate({left: endPos + "px"}, ((width + curPos) * options.scrollSpeed), options.fxEasingScroll, function (){ finish($li); });	
						}, delay);
					}
				}, 500);
			}

			function finish($li){
				if( $.isFunction(options.aftershow) ) options.aftershow.apply(self, [$marquee, $li]);
				$li.removeClass(options.cssShowing);
				ajax();
				readyMarquee();
			}
	
			function showNext(){
				current++;
				if( current >= $lis.length ){
					if( !isNaN(options.loop) && options.loop > 0 && (++loop_count >= options.loop ) ) return false;
					current = 0;
				}
				show(current);
			}
	
			if( $.isFunction(options.init) ) options.init.apply(self, [$marquee, options]);
			showNext();
		};
	
		$.Marquee.defaults = {
			  yScroll: "top"
			, showSpeed: 850
			, scrollSpeed: 12
			, pauseSpeed: 5000
			, fxEasingScroll: "linear"
			, cssShowing: "marquee-showing"
			, timePerCharacterLessThanHalfOfMarquee: 230
			, timePerCharacterMoreThanHalfOfMarquee: 140
		};
	})(jQuery);

	function ajax(){
		message = $('#marquee3').val();
		$.ajax({
			type:'POST',
			data:{message: message},
			url:'MainController',
			success: function(result){
				contents = result;
			}
		})
	}
</script>
		<!-- Bootstrap 3.3.2 JS -->
		<script src="common/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>