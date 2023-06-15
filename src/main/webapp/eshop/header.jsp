<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.KhachHang"%>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<!-- Topbar Start -->
<div class="container-fluid">
	<div class="row bg-secondary py-2 px-xl-5">
		<div class="col-lg-6 d-none d-lg-block">
			<div class="d-inline-flex align-items-center">
				<a class="text-dark" href="">FAQs</a> <span class="text-muted px-2">|</span>
				<a class="text-dark" href="">Help</a> <span class="text-muted px-2">|</span>
				<a class="text-dark" href="">Support</a>
			</div>
		</div>
		<div class="col-lg-6 text-center text-lg-right">
			<%
			KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
			if (khachHang == null || !khachHang.isTrangThaiXacThuc()) {
			%>
			<div class="d-inline-flex align-items-center">
				<a href="<%=url%>/eshop/khachhang/signin.jsp"
					class="nav-item nav-link px-2">Sign In</a> <a
					href="<%=url%>/eshop/khachhang/signup.jsp"
					class="nav-item nav-link px-2">Sign Up</a>
			</div>
			<%
			} else {
			%>
			<div class="d-inline-flex align-items-center">
				<div class="navbar-nav ml-auto py-0">
					<div class="nav-item dropdown">
						<a href="#" class="nav-link dropdown-toggle"
							data-toggle="dropdown">Xin chào <%=khachHang.getTenDangNhap()%></a>
						<div class="dropdown-menu rounded-0 m-0">
							<a href="<%=url%>/eshop/khachhang/changeinformation.jsp"
								class="dropdown-item">Thông tin tài khoản</a> <a
								href="cart.html" class="dropdown-item">Đơn hàng của tôi</a> <a
								href="<%=url%>/eshop/khachhang/changepassword.jsp"
								class="dropdown-item">Đổi mật khẩu</a>
							<div style="width: 100%; border: 1px solid #ccc; margin: 4px 0;"></div>
							<a href="<%=url%>/khach-hang?action=sign-out"
								class="dropdown-item">Đăng xuất</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			%>
			<div class="d-inline-flex align-items-center">
				<a class="text-dark px-2" href=""> <i class="fab fa-facebook-f"></i>
				</a> <a class="text-dark px-2" href=""> <i class="fab fa-twitter"></i>
				</a> <a class="text-dark px-2" href=""> <i
					class="fab fa-linkedin-in"></i>
				</a> <a class="text-dark px-2" href=""> <i class="fab fa-instagram"></i>
				</a> <a class="text-dark pl-2" href=""> <i class="fab fa-youtube"></i>
				</a>
			</div>
		</div>
	</div>
	<div class="row align-items-center py-3 px-xl-5">
		<div class="col-lg-3 d-none d-lg-block">
			<a href="" class="text-decoration-none">
				<h1 class="m-0 display-5 font-weight-semi-bold">
					<span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper
				</h1>
			</a>
		</div>
		<div class="col-lg-6 col-6 text-left">
			<form action="">
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="Search for products">
					<div class="input-group-append">
						<span class="input-group-text bg-transparent text-primary">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
			</form>
		</div>
		<div class="col-lg-3 col-6 text-right">
			<a href="" class="btn border"> <i
				class="fas fa-heart text-primary"></i> <span class="badge">0</span>
			</a> <a href="" class="btn border"> <i
				class="fas fa-shopping-cart text-primary"></i> <span class="badge">0</span>
			</a>
		</div>
	</div>
</div>
<!-- Topbar End -->