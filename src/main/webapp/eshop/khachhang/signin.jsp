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

<!-- ================================================================ -->

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="<%=url%>/eshop/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=url%>/eshop/css/style.css" rel="stylesheet">
<link href="<%=url%>/eshop/css/changeinformation.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="main">
		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="<%=url%>/eshop/login/images/signin-image.jpg"
								alt="sing up image">
						</figure>
						<a href="<%=url%>/eshop/khachhang/signup.jsp"
							class="signup-image-link">Create an account</a>
					</div>

					<div class="signin-form">
						<h2 class="form-title">Sign in</h2>
						<form method="POST" action="<%=url%>/khach-hang"
							class="register-form" id="login-form">
							<%
							String error = (String) request.getAttribute("error");
							String username = (String) request.getAttribute("username");
							if (error != null) {
							%>
							<span id="all_error" style="color: red;"><%=error%></span>
							<%
							}
							%>
							<input type="hidden" name="action" value="sign-in">
							<div class="form-group">
								<label for="your_name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="your_name" id="your_name"
									placeholder="Your Name" <%if (error != null) {%>
									value="<%=username%>" <%}%> />
							</div>
							<div class="form-group">
								<label for="your_pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="your_pass" id="your_pass"
									placeholder="Password" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> <label for="remember-me"
									class="label-agree-term"><span><span></span></span>Remember
									me</label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>
						<div class="social-login">
							<span class="social-label">Or login with</span>
							<ul class="socials">
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-facebook"></i></a></li>
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-twitter"></i></a></li>
								<li><a href="#"><i
										class="display-flex-center zmdi zmdi-google"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- JS -->
	<script src="<%=url%>/eshop/login/vendor/jquery/jquery.min.js"></script>
	<script src="<%=url%>/eshop/login/js/main.js"></script>
	
	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="<%=url%>/eshop/lib/easing/easing.min.js"></script>
	<script src="<%=url%>/eshop/lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Contact Javascript File -->
	<script src="<%=url%>/eshop/mail/jqBootstrapValidation.min.js"></script>
	<script src="<%=url%>/eshop/mail/contact.js"></script>

	<!-- Template Javascript -->
	<script src="<%=url%>/eshop/js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>