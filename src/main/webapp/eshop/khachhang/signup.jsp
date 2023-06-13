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
    <link rel="stylesheet" href="<%=url %>/eshop/login/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="<%=url %>/eshop/login/css/style.css">
</head>
<body>

	<%
	String notify = ((String)request.getAttribute("notify")) == null ? "" : (String)request.getAttribute("notify");
	String name = ((String)request.getAttribute("name")) == null ? "" : (String)request.getAttribute("name");
	String email = ((String)request.getAttribute("email")) == null ? "" : (String)request.getAttribute("email");
	%>
    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" class="register-form" id="register-form" action="<%=url + "/khach-hang" %>">
                        	<input type="hidden" name="action" value="process-register">
                        	<%if(!notify.equals("")) { %>
                        		<span id="all_error" style="color: red;"><%=notify %></span>
                        	<%} %>
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name" <%if(!notify.equals("")) { %>value="<%=name%>"<%} %>/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email" <%if(!notify.equals("")) { %>value="<%=email%>"<%} %>/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" required="required" onkeyup="checkPass()"/>
	                            <span id="check_pass" style="display: none; margin-left: 30px; color: red;">I don't agree</span>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"
                                	   required="required" onkeyup="checkPass()"/>
                                <span id="check_repass" style="display: none; margin-left: 30px; color: red;">I don't agree</span>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                            </div>
                        </form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="<%=url %>/eshop/login/images/signup-image.jpg" alt="sing up image"></figure>
                        <a href="<%=url %>/eshop/khachhang/signin.jsp" class="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script type="text/javascript">
	    const username = document.getElementById("name");
		const email = document.getElementById("email");
		
		username.addEventListener("change keyup paste click", (event) => {
			document.getElementById("all_error").style = "display: none";
		});
		email.addEventListener("change keyup paste click", (event) => {
			document.getElementById("all_error").style = "display: none";
		});
    	
    	function checkPass() {
    		var pass = document.getElementById("pass").value;
        	var re_pass = document.getElementById("re_pass").value;
        	
        	if(pass.length <= 0) {
        		document.getElementById("check_pass").style = "display: block";
        		document.getElementById("check_pass").style = "color: red";
        		document.getElementById("check_pass").innerHTML = "You must type a password";
        		return;
        	}
        	else {
        		document.getElementById("check_pass").style = "display: none";
	        	if(pass != re_pass) {
	        		document.getElementById("check_repass").style = "display: block";
	        		document.getElementById("check_repass").style = "color: red";
	        		document.getElementById("check_repass").innerHTML = "Password retype doesn't correct";
	        		return;
	        	}
	        	else {
	        		document.getElementById("check_repass").style = "display: none";
	        		return;
	        	}        		
        	}
		}
    </script>
    <script src="<%=url %>/eshop/login/vendor/jquery/jquery.min.js"></script>
    <script src="<%=url %>/eshop/login/js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>