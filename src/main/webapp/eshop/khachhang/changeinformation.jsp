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
	
	<div class="" style="background-color: <%if(khachHang == null) { %>white<%}else { %>#f1f1f1<%} %>; min-height: 300px; padding-bottom: 50px">
		<article class=" widget-navigation widget-bread-cumps-widget widget">
			<nav aria-label="breadcrumb">
				<div class="container">
					<ol class="breadcrumb" style="background-color: <%if(khachHang == null) { %>white<%}else { %>#f1f1f1<%} %>; padding: 1rem 0">
						<li class="breadcrumb-item">
							<a href="/">Trang chủ</a>
						</li>
						<li class="breadcrumb-item">
							<a href="/user/information">Thông tin của tôi</a>
						</li>
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
										<div class="col-md-3 bg-light" style="padding: 30px 0 0 25px; min-height: 400px">
											<div class="user--profile-left">
												<ul class="user--profile--list-function" style="list-style: none; margin: 0; padding: 0">
													<li class="active">
														<a class="active" href="/user/information">Thông tin & liên hệ </a>
													</li>
													<li class="no-active">
														<a class="no-active" href="/user/information">Đổi tên người dùng </a>
													</li>
													<li class="no-active">
														<a class="no-active" href="/user/information/changeusername">Đổi mật khẩu </a>
													</li>
													<li>
														<a class="no-active" href="/team/">Quản lý đội</a>
													</li>
												</ul>
											</div>
										</div>
										<div class="col-md-9 user--profile-right-container bg-light" style="padding: 30px 0 0 30px; min-height: 400px">
											<div class="user--profile-right">
												<div class="user--profile-group">
													<%
													String success = (String)request.getAttribute("success");
													if(success != null) {
													%>
														<div class="alert alert-success" style="margin-right: 30px;">
															  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
														    <strong>Success!</strong> This alert box could indicate a successful or positive action.
														 </div>
													<%} %>
													<h2 class="user--profile-title-group mb-3">Thông tin</h2>
													<%
													String hoVaTen = (khachHang.getHoVaTen() != null) ? khachHang.getHoVaTen() : "";
													String ngaySinh= (khachHang.getNgaySinh() != null) ? khachHang.getNgaySinh().toString() : "01/01/2001";
													String dienThoai= (khachHang.getSoDienThoai() != null) ? khachHang.getSoDienThoai() : "";
													String email= (khachHang.getEmail() != null) ? khachHang.getEmail() : "";
													String gioiTinh= (khachHang.getGioiTinh() != null) ? khachHang.getGioiTinh() : "";
													String diaChiKhachHang= (khachHang.getDiaChi() != null) ? khachHang.getDiaChi() : "";
													%>
													<form class="init" method="post" id="frm-info" action="<%=url%>/khach-hang" >
														<input type="hidden" name="action" value="change-info">
														<div class="row">
															<div class="col-md-2 col-xs-12">
		                                                        <div class="row">
		                                                            <div id="ImgPreview" class="no-img">
		                                                            </div>
		                                                        </div>
		                                                        <div class="row avatar-selector">
		                                                            <div class="form-group UploadAvatar">
		                                                                <label for="Avatar" id="messageValidateImage">Ảnh đại
		                                                                    diện của bạn</label>
		                                                                <input name="ImgFile" id="Avatar" class="file"
		                                                                    type="file"
		                                                                    accept="image/png,image/x-png,image/gif,image/jpeg,image/jpg" />
		                                                                <div class="input-group">
		                                                                    <span class="input-group-btn">
		                                                                        <button class="browse btn btn-primary input-sm" type="button" id="Upload-Ava">
		                                                                        	Chọn ảnh
		                                                                        </button>
		                                                                    </span>
		                                                                </div>
		                                                            </div>
		                                                        </div>
		                                                    </div>
															<div class="col-md-9 col-xs-12">
																<input name="id" value="<%=khachHang.getMaKhacHang() %>" type="hidden" />
																<div class="row">
																	<div class="col-lg-3 col-sm-4 col-xs-12">
																		<div class="form-group">
																			<label for="FamilyName" class="required">Họ và tên</label>
																		</div>
																	</div>
																	<div class="col-lg-9 col-sm-8 col-xs-12">
																		<div class="form-group">
																			<span id="span-name" class="span-display"></span> 
																			<input
																				name="FamilyName" type="text"
																				class="form-control is-required" id="FamilyName"
																				autocomplete="family-name" value="<%=hoVaTen%>"/>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-lg-3 col-sm-4 col-xs-12">
																		<div class="form-group">
																			<label for="BirthYear">Ngày sinh</label>
																		</div>
																	</div>
																	<div class="col-lg-9 col-sm-8 col-xs-12">
																		<div class="form-group">
																			<span id="span-birthday" class="span-display"></span> 
																			<input
																				name="BirthYear" type="date" id="BirthYear"
																				class="form-control" placeholder="yyyy-mm-dd" value="<%=ngaySinh%>">
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-lg-3 col-sm-4 col-xs-12">
																		<div class="form-group">
																			<label for="text" class="required">Số điện thoại</label>
																		</div>
																	</div>
																	<div class="col-lg-9 col-sm-8 col-xs-12">
																		<div class="form-group">
																			<span id="span-phone" class="span-display"></span> 
																			<input
																				name="PhoneNumber" type="text"
																				class="form-control is-required" id="PhoneNumber"
																				placeholder="Số điện thoại"
																				autocomplete="tel-national" value="<%=dienThoai%>"/>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-lg-3 col-sm-4 col-xs-12">
																		<div class="form-group">
																			<label for="EmailAddress" class="required">Địa
																				chỉ Email</label>
																		</div>
																	</div>
																	<div class="col-lg-9 col-sm-8 col-xs-12">
																		<div class="form-group">
																			<span id="span-email" class="span-display"></span> <input
																				name="EmailAddress" type="email"
																				class="form-control" id="EmailAddress"
																				placeholder="Địa chỉ Email" autocomplete="email" value="<%=email%>"/>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-lg-3 col-sm-4 col-xs-12">
																		<div class="form-group">
																			<label for="" class="required">Giới tính</label>
																		</div>
																	</div>
																	<div class="col-lg-9 col-sm-8 col-xs-12">
																		<div class="form-group">
																			<span id="span-gender" class="span-display"></span> 
																			<label for="GenderMale">Nam</label>
																			<input name="Gender" type="radio" id="GenderMale" class="mr-3" value="Nam"
																				<%if(gioiTinh.equals("Nam")) { %> checked="checked" <%} %>
																			/>
																			
																			<label for="GenderFemale">Nữ</label>
																			<input name="Gender" type="radio" id="GenderFemale" class="mr-3" value="Nữ"
																				<%if(gioiTinh.equals("Nữ")) { %> checked="checked" <%} %>
																			/>
																			
																			<label for="GenderOther">Khác</label>
																			<input name="Gender" type="radio" id="GenderOther" class="mr-3" value="Khác"
																				<%if(gioiTinh.equals("Khác")) { %> checked="checked" <%} %>
																			/>
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-lg-3 col-sm-4 col-xs-12">
																		<div class="form-group">
																			<label for="CityName">Địa chỉ</label>
																		</div>
																	</div>
																	<div class="col-lg-9 col-sm-8 col-xs-12">
																		<div class="form-group">
																			<span id="span-cityName" class="span-display"></span>
																			<textarea name="CityName" type="text"
																				class="form-control" id="CityName"
																				<%if(khachHang == null){ %> placeholder="Tỉnh/Thành phố bạn đang sống"<%}%> ><%=diaChiKhachHang %></textarea>
																		</div>
																	</div>
																</div>
																<div class="row mt-3">
		                                                            <div class="form-group pull-right">
		                                                                <button
		                                                                    class="btn btn-primary btn-sm pull-right btn-save save-info-button"
		                                                                    type="submit" id="btnSaveInfo"
		                                                                    style="height: 35px;width: 104px;border-radius: 3px;line-height: inherit;">Lưu</button>
		                                                                <button class="btn btn-secondary btn-sm pull-right btn-cancel "
		                                                                    type="submit" id="btnCancelInfo"
		                                                                    style="height: 35px;width: 104px;border-radius: 3px;margin-right: 18px; line-height: inherit;">Huỷ</button>
		                                                            </div>
		                                                        </div>
															</div>
															<div class="col-md-1">
																<p style="float: right;">
																	<a href="javascript:void(0)"> <i
																		class="cl-icon-pen edit-pencil-info"
																		id="edit-pencil-info"></i>
																	</a>
																</p>
															</div>
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
			</main>
	<%} else { %>
		<div class="container">
			<a href="javascript:void(0)" data-toggle="modal" data-target="#loginModal" onclick="openModal('login')" id="not-auth" style="display: inline-block; color:red; margin: 10px 0px; font-style: italic">
				Please sign in to update your information!
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
</html>