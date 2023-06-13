package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;
import util.Birthday;
import util.MaHoa;

/**
 * Servlet implementation class KhachHang
 */
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public KhachHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("sign-in")) {
			signin(request, response);
		} else if (action.equals("sign-out")) {
			signout(request, response);
		} else if (action.equals("process-register")) {
			register(request, response);
		} else if (action.equals("change-password")) {
			changePassword(request, response);
		}else if (action.equals("change-info")) {
			changeInfoUser(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void changeInfoUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String fullName = request.getParameter("FamilyName");
		System.out.println(fullName);
		String birthday = request.getParameter("BirthYear");
		Date dob = Birthday.changeStringToDate(birthday);
		String phoneNumber = request.getParameter("PhoneNumber");
		String email = request.getParameter("EmailAddress");
		String gender = request.getParameter("Gender");
		String address = request.getParameter("CityName");
		System.out.println(address);
		
		KhachHang kh = new KhachHang();
		kh.setMaKhacHang(id);
		kh.setHoVaTen(fullName);
		kh.setNgaySinh(dob);
		kh.setSoDienThoai(phoneNumber);
		kh.setEmail(email);
		kh.setGioiTinh(gender);
		kh.setDiaChi(address);
		
		KhachHangDAO khDAO = new KhachHangDAO();
		khDAO.updateById(kh);
		
		KhachHang khachHang = khDAO.selectById(kh);
		
		String url = "/eshop/khachhang/changeinformation.jsp";
		String success = "Cập nhật thông tin thành công";
		request.setAttribute("success", success);
		
		HttpSession session = request.getSession();
		session.setAttribute("khachHang", khachHang);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		String error = "";
		String success =  "";
		String url = "/eshop/khachhang/changepassword.jsp";
		
		KhachHangDAO khDAO = new KhachHangDAO();
		KhachHang temp = new KhachHang();
		temp.setMaKhacHang(id);
		KhachHang khTemp = khDAO.selectById(temp);
		String hashCurrentPassword = MaHoa.toMD5(currentPassword);
		if(!hashCurrentPassword.equals(khTemp.getMatKhau())) {
			error = "Password doesn't match with old password";
			request.setAttribute("error", error);
		}
		else if(!newPassword.equals(confirmPassword)) {
			error = "New password and confirm pass don't match, plz type it again";
			request.setAttribute("error", error);
		}
		else if(currentPassword.equals(newPassword)) {
			error = "Old pass and new pass is duplicate";
			request.setAttribute("error", error);
		}
		else {
			//có thể đổi mật khẩu
			khDAO.updatePassword(newPassword, id);
			success = "Đổi mật khẩu thành công";
			request.setAttribute("success", success);
		}
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tenDangNhap = request.getParameter("name");
		String email = request.getParameter("email");
		String matKhau = request.getParameter("pass");
		String matKhauNhapLai = request.getParameter("re_pass");
		
		//giữ lại thông tin đã nhập
		request.setAttribute("name", tenDangNhap);
		request.setAttribute("email", email);

		boolean checkError = false;
		String notify = "";
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		
		if(khachHangDAO.checkExistOfUsername(tenDangNhap, email)) {
			checkError = true;
			notify += "username is already exists, plz give us another name<br/>";
		}
		if(!matKhau.equals(matKhauNhapLai)) {
			checkError = true;
			notify += "password retype is not the same with password";
		}
		request.setAttribute("notify", notify);
		
		String urlDirection = "";
		if(checkError) {
			urlDirection = "/eshop/khachhang/signup.jsp";
		}
		else {
			String maKhachHang = System.currentTimeMillis() + "";
			matKhau = MaHoa.toMD5(matKhau);
			KhachHang khachHang = new KhachHang();
			khachHang.setMaKhacHang(maKhachHang);
			khachHang.setTenDangNhap(tenDangNhap);
			khachHang.setEmail(email);
			khachHang.setMatKhau(matKhau);
			
			HttpSession session = request.getSession();
			session.setAttribute("khachHang", khachHang);
			khachHangDAO.insert(khachHang);
			urlDirection = "/eshop/khachhang/success.jsp"; // start from root(webapp)
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(urlDirection);
		rd.forward(request, response);
	}
	
	protected void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("your_name");
		String password = request.getParameter("your_pass");
		password = MaHoa.toMD5(password);
		
		KhachHangDAO khDAO = new KhachHangDAO();
		KhachHang khachHang = khDAO.selectByUsernameAndPassword(username, password);
		
		String url = "";
		if(khachHang != null) {//đăng nhập thành công
			String urlDirection = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
			HttpSession session = request.getSession();
			session.setAttribute("khachHang", khachHang);//lưu lại object khachHang vao trong session
			response.sendRedirect(urlDirection + "/eshop/index.jsp");
		}
		else {
			request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
			request.setAttribute("username", username);
			url = "/eshop/khachhang/signin.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}
	
	protected void signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
		HttpSession session = request.getSession();
		session.removeAttribute("khachHang");//xóa object khachHang ra khoi session
		response.sendRedirect(url + "/eshop/index.jsp");
	}
}
