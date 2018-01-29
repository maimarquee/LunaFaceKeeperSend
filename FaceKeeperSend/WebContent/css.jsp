<%@ page import="com.facekeeper.util.*" %>
<style>
	body {
<%-- 	  background: url('common/images/<%=SettingsUtil.OWNER_CODE%>/wallpaper.jpg') no-repeat center center fixed; --%>
	background: url('common/images/BCC/bagoCityCollegeFaceKeeper.jpg') no-repeat center center fixed;
	  background-size: cover;
	}
	
	.custom {
		  overflow: hidden;
		  box-shadow: 2px 2px 3px 1px rgba(0, 0, 0, 0.2);
		  border-radius:10px
		}
		
		#pict1 .pict_base {
			height: 80%;
			background-color: rgba(250, 250, 250, 0.7);
			overflow: hidden;
			padding: 0px 15px;
			box-shadow: 2px 2px 3px 1px rgba(0, 0, 0, 0.2);
			border-radius:10px;
		}
		
		#pict1 #face_log {
			min-height: 100%;
			position:center;
			size:cover;
			width:110%;
			overflow:hidden;
			margin:0px -20px;
		}
		
		#pict1 .face_log {
			height:65%;
			margin:15px auto 10px;
			overflow:hidden;
			position:relative;
		}
		
		#pict1 #avatar {
			min-width: 100%;
			min-height: 100%;
			position:center;
			size:cover;
			width:110%;
			overflow:hidden;
			padding-left:10px;
			margin:0px -20px;
		}
		
		#pict1 .avatar {
			float: left;
			width: 50%;
			border:solid white 3px;
			height:100%;
			position:relative;
			overflow:hidden;
			margin: -20px auto 20px;
			box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);
			border-radius:10px;
		}
		
		#pict1 .details {
			float: right;
			width: 50%;
			min-height:80%;
			height:90%;
			margin: 1% -10px -10px 0px;
			padding:20px 25px 10px;
			text-align: center;
		}
		
		#pict1 .details .firstname {
			font-size:25px;
			font-weight:700;
			color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>;
		}
		
		#pict1 .details .lastname {
			font-size:20px;
			font-weight:50;
			color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>;
			margin-top:-25px;
			line-height: 200%;
	  		border-bottom: 1px solid <%=SettingsUtil.OWNER_PRIMARY_COLOR%>;
		}
		
		#pict1 .details .timestamp {
			font-size:18px;
			font-weight:500;
			color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>;
		}
		
		#pict1 .bottom_part {
			position:relative;
			min-height:1px;
			padding-right:15px;
			padding-left:15px;
			width:100%;
			height:32%;
			margin: -20px auto 10px;
		}
		
		#pict2 .is_in, #pict3 .is_in, #pict4 .is_in {
			text-align:center;
			position:absolute;
			top:0%;
			left:7%;
			padding:15px 15px;
			border-bottom-left-radius:10px;
			border-bottom-right-radius:10px;
			background-color:green;
			box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);
			font-weight:600;
			font-size: 10px;
			color:white;
		}
		
		#pict2 .is_out, #pict3 .is_out, #pict4 .is_out {
			text-align:center;
			position:absolute;
			top:0%;
			left:7%;
			padding:15px 15px;
			border-bottom-left-radius:10px;
			border-bottom-right-radius:10px;
			background-color:blue;
			box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);
			font-weight:600;
			font-size: 10px;
			color:white;
		}
		
		#pict1 .is_in {
			text-align:center;
			position:absolute;
			top:0%;
			left:7%;
			padding:20px 20px;
			border-bottom-left-radius:10px;
			border-bottom-right-radius:10px;
			background-color:green;
			box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);
			font-weight:600;
			color:white;
		}
		
		#pict1 .is_out {
			text-align:center;
			position:absolute;
			top:0%;
			left:7%;
			padding:20px 20px;
			border-bottom-left-radius:10px;
			border-bottom-right-radius:10px;
			background-color:blue;
			box-shadow: 2px 3px 3px 1px rgba(0, 0, 0, 0.2);
			font-weight:600;
			color:white;
		}
		
		#pict2 img, #pict3 img, #pict3 img {
			border-radius: 10px;
		}
	
		#pict2 .pict_base, #pict3 .pict_base, #pict4 .pict_base {
			width:100%;
			height:inherit;
			background-color: rgba(250, 250, 250, 0.7);
			overflow: hidden;
			margin: 0px auto 10px;
			box-shadow: 2px 2px 3px 1px rgba(0, 0, 0, 0.2);
			border-radius:10px;
		}
		
		#pict2 #face_log, #pict3 #face_log, #pict4 #face_log {
			float: left;
			position:center;
			width: 100% ;
			size:cover;
			overflow:hidden;
		}
		
		#pict2 .face_log, #pict3 .face_log, #pict4 .face_log {
			float: right;
			width: 41%;
			overflow: hidden;
			margin: 15px 30px 27px 10px;
			right: 10px;
		}
		
		#pict2 #avatar, #pict3 #avatar, #pict4 #avatar {
			float: right;
			position:center;
			size:cover;
			width:100%;
			overflow:hidden;
		}
		
		#pict2  .avatar, #pict3  .avatar, #pict4  .avatar {
			float: left;
			width: 41%;
			height: 161px;
			overflow: hidden;
			margin: 15px 10px 5px 30px;
			left: 10px;
		}
		
		#pict2 .details, #pict3 .details, #pict4 .details {
			width: 100%;
			text-align: center;
			font-size:15px;
			font-weight:700;
			color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>;
			margin:-7px auto 2px;
			float: left;
		}
	
		#pict2 .carded, #pict3 .carded, #pict4 .carded {
			margin: -40px auto 0px 0px;
			background-color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>;
			color: white;
			padding-top: 10px;
		}
	
		#pict2 .details .firstname, #pict3 .details .firstname, #pict4 .details .firstname {
			float:left;
			margin-left: 30px;
		}
		
		#pict2 .details .lastname, #pict3 .details .lastname, #pict4 .details .lastname {
			float:left;
			margin-left: 10px;
		}
		
		#pict2 .details .timestamp, #pict3 .details .timestamp, #pict4 .details .timestamp {
			float:right;
			margin-right: 30px;
		}
	
		.previous-students {
			height: 10%;
			padding-left:auto;
			padding-right:auto;
			overflow-y: hidden;
			overflow-x: hidden;
			float: middle;
		}
	
		.previous-students .students-log{
			height: 100%;
			margin: 0px 10px;
			float: left;
			color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>; 
		}
	
		.previous-students .students-log .time-log{
			background-color: <%=SettingsUtil.OWNER_PRIMARY_COLOR%>; 
			padding: 4px 5px; 
			color: white; 
			border-radius: 5px;
		}
	}
</style>