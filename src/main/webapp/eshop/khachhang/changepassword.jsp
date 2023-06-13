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

<style type="text/css">
.no-active {
	color: #6F6F6F !important;
}

.active {
	color: #ba6a62 !important;
}

.user--profile-left .user--profile--list-function li {
    margin-bottom: 10px;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="">
		<article class=" widget-navigation widget-bread-cumps-widget widget">
			<nav aria-label="breadcrumb">
				<div class="container">
					<ol class="breadcrumb"
						style="background-color: white; padding: 1rem 0">
						<li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
						<li class="breadcrumb-item"><a href="/user/information">Thông
								tin của tôi</a></li>
						<li class="breadcrumb-item"><a
							href="/user/information/changepassword">Đổi tên mật khẩu</a></li>
					</ol>
				</div>
			</nav>
		</article>
		<%if(khachHang != null) { %>
			<main id="layout-main" class="group mt-3">
				<div id="layout-content" class="group">
					<div id="content" class="group">
						<div class="zone zone-content">
							<div class="container">
								<div class="user--profile">
									<div class="row">
										<div class="col-md-3 bg-light">
											<div class="user--profile-left">
												<ul class="user--profile--list-function"
													style="list-style: none; margin: 0; padding: 0">
													<li class="active"><a class="no-active"
														href="/user/information">Thông tin & liên hệ </a></li>
													<li class="no-active"><a class="no-active"
														href="/user/information">Đổi tên người dùng </a></li>
													<li class="no-active"><a class="active"
														href="/user/information/changeusername">Đổi mật khẩu </a></li>
													<li><a class="no-active" href="/team/">Quản lý đội</a>
													</li>
												</ul>
											</div>
										</div>
										<%
										String error = (String)request.getAttribute("error");
										String success = (String)request.getAttribute("success");
										%>
										<div class="col-md-9 user--profile-right-container bg-light">
											<div class="user--profile-right">
												<div class="user--profile-group" style="min-height: 550px">
													<div id="change-password" class="tab-pane active">
														<form id="frmChangePassword" method="POST" action="<%=url %>/khach-hang">
															<input type="hidden" name="action" value="change-password">
															<div class="form-group" style="margin: 0">
																<input name="id" value="<%=khachHang.getMaKhacHang() %>" type="hidden" />
															</div>
															<%if(error != null) { %>
																<div class="form-group">
																	<span class="alert alert-danger"><%=error %></span>
																</div>
															<%} else if(success != null) { %>
																<div class="form-group">
																	<span class="alert alert-success"><%=success %></span>
																</div>
															<%} %>
															<div class="form-group">
																<label style="font-weight: bold; color: #333;"
																	for="currentPassword">Mật khẩu</label> 
																<input
																	name="currentPassword" type="password"
																	class="form-control" placeholder="Mật khẩu"
																	autocomplete="current-password" />
															</div>
															<p>
																<a
																	href="/users/account/logoff?returnUrl=~/forgot-password"
																	class="pull-right">Quên mật khẩu?</a>
															</p>
															<div class="form-group">
																<label style="font-weight: bold; color: #333;"
																	for="newPassword">Mật khẩu mới</label>
																	<input id="newPassword"
																	name="newPassword" type="password" class="form-control"
																	placeholder="Mật khẩu mới" onkeyup="checkPass()"/>
																	<span id="check_pass" style="display: none;"></span>
															</div>
															<div class="form-group">
																<label style="font-weight: bold; color: #333;"
																	for="confirmPassword">Mật khẩu xác nhận</label> 
																	<input id="confirmPassword"
																	name="confirmPassword" type="password"
																	class="form-control" placeholder="Mật khẩu xác nhận" onkeyup="checkPass()"/>
																	<span id="check_repass" style="display: none;"></span>
															</div>
															<div class="form-group">
																<button id="buttonSubmit"
																	class="btn btn-sm pull-right btn-save btn-success"
																	type="submit">Thay đổi
																</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		<%} else { %>
			<div class="container">
				<a href="javascript:void(0)" data-toggle="modal" data-target="#loginModal" onclick="openModal('login')" id="not-auth" style="display: inline-block; color:red; margin: 10px 0px; font-style: italic">
					Please sign in to update your password!
				</a>
			</div>
		<%} %>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>

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
<script type="text/javascript">
function checkPass() {
	var pass = document.getElementById("newPassword");
	var re_pass = document.getElementById("confirmPassword");
	
	if(pass.value.length <= 0) {
		document.getElementById("check_pass").style = "display: block";
		document.getElementById("check_pass").style = "color: red";
		document.getElementById("check_pass").innerHTML = "You must type a password";
		return;
	}
	else {
		document.getElementById("check_pass").style = "display: none";
    	if(pass.value != re_pass.value) {
    		document.getElementById("check_repass").style = "display: block";
    		document.getElementById("check_repass").style = "color: red";
    		document.getElementById("check_repass").innerHTML = "Password retype doesn't match";
    		document.getElementById("buttonSubmit").disabled = true;
    		document.getElementById("buttonSubmit").classList.add("bg-secondary");
    		document.getElementById("buttonSubmit").style = "color: black";
    		return;
    	}
    	else {
    		document.getElementById("check_repass").style = "display: none";
    		document.getElementById("buttonSubmit").disabled = false;
    		document.getElementById("buttonSubmit").classList.remove("bg-secondary");
    		document.getElementById("buttonSubmit").style = "color: white";
    		return;
    	}        		
	}
}
</script>
</html>