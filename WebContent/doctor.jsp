<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="pms.model.*"%>
<%
	Login user = (Login) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<title>PMS- Dr. <%=user.getDoctor().getFirstName()%> <%=user.getDoctor().getMiddleName()%>
	<%=user.getDoctor().getLastName()%></title>
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
			<li class="last"><a href="" id="home_nav">Home</a></li>
		</ul>
		<div id="navbarrt" style="float: right; padding: .5em 1em;">
			<a id="logout_link" class="button green tooltip" title="Logout"
				href="logout"><i class="icon-signout"></i><span
				class="hide-phone">Logout</span></a>
		</div>
	</nav>
	<div id="content">
		<div id="menu" class="grid">
			<ul class="alt" id="dr_menu">
				<li><a href="" id="upld_menu">Upload Prescription</a></li>
				<li><a href="" id="pt_menu">Patients</a>- Manage your patients</li>
				<li><a href="" id="pf_menu">Profile</a></li>
				<li><a href="" id="rp_menu">Reports</a>
			</ul>
		</div>
		<div id="upld_div" class="hide">
			<div class="grid">
				<div class="col_12 bxborder">
					<div class="divcaption">
						<span>Prescription</span>
					</div>
					<div class="divcontent">
						<form id="upldrx" class="horizontal" method="post"
							enctype="multipart/form-data" action="upload">
							<div class="hide divcontent">
								<img id="preview" class="hidden" src="#" alt="Preview Image" width="100%" />
							</div>
							<input name="rx" type="file" accept="image/*" id="rx" />
							<button class="medium green" type="submit">
								<i class="icon-upload"></i><span class="hide-phone" style="display:inline;"> Upload</span>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>