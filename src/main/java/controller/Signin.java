package controller;

import java.io.IOException;

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
 * Servlet implementation class Signin
 */
@WebServlet("/sign-in")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Signin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			url = "/eshop/login/signin.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}
