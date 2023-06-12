package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.KhachHangDAO;
import model.KhachHang;
import util.MaHoa;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/change-password")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		String error = "";
		String success =  "";
		String url = "/eshop/changepassword.jsp";
		
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
