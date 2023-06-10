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
import util.MaHoa;

/**
 * Servlet implementation class Register
 */
@WebServlet("/process-register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			urlDirection = "/eshop/login/signup.jsp";
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
			urlDirection = "/eshop/login/success.jsp"; // start from root(webapp)
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(urlDirection);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
