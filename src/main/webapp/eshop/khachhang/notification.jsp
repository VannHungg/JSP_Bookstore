<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.KhachHang"%>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EShopper - Bootstrap Shop Template</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">
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
	
	<div class="" style="background-color: white; min-height: 300px; padding-bottom: 50px">
		<article class=" widget-navigation widget-bread-cumps-widget widget">
			<nav aria-label="breadcrumb">
				<div class="container">
					<ol class="breadcrumb" style="background-color: white; padding: 1rem 0">
						<li class="breadcrumb-item">
							<a href="/">Trang chủ</a>
						</li>
						<li class="breadcrumb-item">
							<a href="/user/information">Thông báo</a>
						</li>
					</ol>
				</div>
			</nav>
		</article>
		<%String notify = (String)request.getAttribute("notify"); %>
		<div class="container">
			<a href="javascript:void(0)" data-toggle="modal" data-target="#loginModal" onclick="openModal('login')" id="not-auth" style="display: inline-block; color:red; margin: 10px 0px; font-style: italic">
				<%=notify %>
			</a>
			<div class="form-group">
				<p id="demo"></p>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

	<!-- JavaScript Libraries -->
	<script type="text/javascript">
		setInterval(myTimer, 1000);
		var i = 5;
		function myTimer() {
		  if(i == -1) {
			  clearInterval(id);
		  }
		  else {
			  document.getElementById("demo").innerHTML = "You will direct to go home page on " + i;
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
	<script src="<%=url%>/eshop/login/vendor/jquery/jquery.min.js"></script>
	<script src="<%=url%>/eshop/login/js/main.js"></script>
</body>
</html>