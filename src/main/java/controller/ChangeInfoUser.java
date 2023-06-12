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

/**
 * Servlet implementation class ChangeInfoUser
 */
@WebServlet("/change-info")
public class ChangeInfoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeInfoUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String url = "/eshop/changeinformation.jsp";
		String success = "Cập nhật thông tin thành công";
		request.setAttribute("success", success);
		
		HttpSession session = request.getSession();
		session.setAttribute("khachHang", khachHang);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
