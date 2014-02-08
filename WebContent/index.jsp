<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String msg = (String)request.getAttribute("msg");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<title>PMS</title>

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="css/kickstart.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />

<!-- Javascript -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/kickstart.js"></script>
<script type="text/javascript" src="js/pms.js"></script>

</head>
<body>
	<nav id="pms_nav" class="navbar">
		<ul id="navList">
			<li class="last"><a href="/pms">Login</a></li>
		</ul>
	</nav>
	<div id="content">
		<div class="grid">
			<div class="col_4">
			<%
				if(msg!=null && msg.length()>0){
			%>
				<div id="msg_div" class="notice error"><i class="icon-remove-sign icon-large"></i><div id="msg_txt"><%=msg %></div>
				</div>
			<%
				}
			%>
				<form class="vertical" action="login" method="post" id="lgn_form">
					<input maxlength="50" id="text_alias" type="text" name="username"
						placeholder="User Name">
					<input maxlength=50
						id="text_password" type="password" name="password"
						placeholder="Password"/>
					<button class="medium green" type="submit" id="lgn_submit" style="display:block; min-width: 100%"><i class="icon-signin"  style="display:inline-block;"></i><span  class="hide-phone" style="display:inline;"> Submit</span></button>
					<a href="" class="center" style="display:block; min-width: 100%">Forgot Password</a>
				</form>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>