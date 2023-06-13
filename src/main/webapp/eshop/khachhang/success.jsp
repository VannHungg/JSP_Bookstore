<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="<%=url%>/eshop/login/fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="<%=url%>/eshop/login/css/style.css">
</head>
<body>

	<div class="main">
		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
                        <figure><img src="<%=url %>/eshop/login/images/signin-image.jpg" alt="sing up image"></figure>
                    </div>

					<div class="signin-form">
						<h2 class="form-title">Congratulations, you have just sign up successfully!</h2>
						<div class="form-group">
							<p id="demo"></p>
						</div>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script type="text/javascript">
		setInterval(myTimer, 1000);
		var i = 5;
		function myTimer() {
		  if(i == -1) {
			  clearInterval(id);
		  }
		  else {
			  document.getElementById("demo").innerHTML = "You will direct go home on " + i;
			  i--;
		  }
		}
	</script>
	<script>
		var link = location.protocol + "//" + location.host + "/"
				+ location.pathname.split("/")[1];
		var linkDirection = link + "/eshop/index.jsp";
		setTimeout(function() {
			window.location.href = linkDirection;
		}, 7000);
	</script>
	<script src="<%=url%>/eshop/login/vendor/jquery/jquery.min.js"></script>
	<script src="<%=url%>/eshop/login/js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>