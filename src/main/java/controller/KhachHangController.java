package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.KhachHangDAO;
import model.KhachHang;
import util.Birthday;
import util.Email;
import util.Helper;
import util.MaHoa;
import util.RandomCode;

/**
 * Servlet implementation class KhachHang
 */
@WebServlet("/khach-hang")
@MultipartConfig
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;
	
	public void init() {
		ServletContext context = getServletContext();
		this.host = context.getInitParameter("host");
		this.port = context.getInitParameter("port");
		this.user = context.getInitParameter("user");
		this.pass = context.getInitParameter("pass");
	}
    
    public KhachHangController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action: " + action);
		if (action.equals("sign-in")) {
			signin(request, response);
		} else if (action.equals("sign-out")) {
			signout(request, response);
		} else if (action.equals("process-register")) {
			try {
				register(request, response);
			} catch (ServletException | IOException | ParseException | MessagingException e) {
				e.printStackTrace();
			}
		} else if (action.equals("change-password")) {
			changePassword(request, response);
		} else if (action.equals("change-info")) {
			try {
				changeInfoUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("confirm-account")) {
			try {
				confirmAccount(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	protected void confirmAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		String id = request.getParameter("makhachhang");
		System.out.println("id: " + id);
		String codeConfirm = request.getParameter("maxacthuc");
		System.out.println("code: " + codeConfirm);
		
		KhachHangDAO khDAO = new KhachHangDAO();
		KhachHang khachHang = new KhachHang();
		khachHang.setMaKhacHang(id);
		KhachHang kh = khDAO.selectById(khachHang);
		
		String notify = "";
		String url = "/eshop/khachhang/notification.jsp";
		//ss với mã xác thực nhận đc
		if(kh != null) {
			//xác nhận code còn hiệu lực hay không
			Date dateConfirm = Birthday.thoiHanHieuLuc(0);
			System.out.println("date confirm: " + dateConfirm);
			Date dateInDB = kh.getThoiGianHieuLucMaXacThuc();
			System.out.println("date in db: " + dateInDB);
			if(dateConfirm.compareTo(dateInDB) > 0) {//tg hiệu lực ko còn
				notify = "Thời gian hiệu lực để xác nhận tài khoản đã hết -.-";
			}
			else {
				if(kh.getMaXacThuc().equals(codeConfirm)) {
					notify = "Xác thực tài khoản thành công";
					khDAO.updateConfirmAccount(id);
				}
				else {
					notify = "Xác thực tài khoản không thành công, mã xác thực đã bị sai";
				}
			}
		}
		else {
			notify = "Bạn chưa đăng ký tài khoản, vui lòng đăng ký để tiếp tục";
		}
		
		request.setAttribute("notify", notify);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	@SuppressWarnings("deprecation")
	protected void changeInfoUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		KhachHang kh = new KhachHang();
		KhachHangDAO khDAO = new KhachHangDAO();
		String message = "";
		
		// gets values of text fields
		String id = request.getParameter("id");
		String fullName = request.getParameter("FamilyName");
		String birthday = request.getParameter("BirthYear");
		Date dob = Birthday.changeStringToDate(birthday);
		String phoneNumber = request.getParameter("PhoneNumber");
		String email = request.getParameter("EmailAddress");
		String gender = request.getParameter("Gender");
		String address = request.getParameter("CityName");
		
		Part part = request.getPart("photo");
		String photo = part.getSubmittedFileName();
		System.out.println(photo);
		
		kh.setMaKhacHang(id);
		kh.setHoVaTen(fullName);
		kh.setNgaySinh(dob);
		kh.setSoDienThoai(phoneNumber);
		kh.setEmail(email);
		kh.setGioiTinh(gender);
		kh.setDiaChi(address);
		kh.setDuongDanAnh(photo);
		
		if(khDAO.updateById(kh) == 0)  {
			message = "Cập nhật thông tin không thành công";						
		}
		else {
			message = "Cập nhật thông tin thành công";
			
			String path = request.getRealPath("/") + "eshop/khachhang/avatar" + File.separator + kh.getDuongDanAnh();
			System.out.println("path: " + path);
			Helper.deleteFile(path);
			if(Helper.saveFile(part.getInputStream(), path)) {
				System.out.println("photo updated...");
			}
			else {
				System.out.println("photo not update");
			}
			
		}
		KhachHang khachHang = khDAO.selectById(kh);
		
		String url = "/eshop/khachhang/changeinformation.jsp";
		request.setAttribute("message", message);
		
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
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, AddressException, MessagingException {
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
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlDirection);
			rd.forward(request, response);
		}
		else {
			String maKhachHang = System.currentTimeMillis() + "";
			matKhau = MaHoa.toMD5(matKhau);
			String maXacThuc = RandomCode.randomCode(6);
			boolean trangThaiXacThuc = false;
			java.sql.Date thoigianhieulucmaxacthuc = Birthday.thoiHanHieuLuc(10); //hiệu lực trong 10 ngày
			
			KhachHang khachHang = new KhachHang();
			khachHang.setMaKhacHang(maKhachHang);
			khachHang.setTenDangNhap(tenDangNhap);
			khachHang.setEmail(email);
			khachHang.setMatKhau(matKhau);
			khachHang.setMaXacThuc(maXacThuc);
			khachHang.setTrangThaiXacThuc(trangThaiXacThuc);
			khachHang.setThoiGianHieuLucMaXacThuc(thoigianhieulucmaxacthuc);
			
			HttpSession session = request.getSession();
			session.setAttribute("khachHang", khachHang);
			khachHangDAO.insert(khachHang);
			
			//send e-mail confirm account after sign up
			String recipient = khachHang.getEmail(); //?
			String subject = "Thông báo xác thực tài khoản " + System.currentTimeMillis();
			String content = getContent(khachHang);
			Email.sendEmail(this.host, this.port, this.user, this.pass, recipient, subject, content); //success

			request.setAttribute("id", maKhachHang);
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
			response.sendRedirect(url + "/eshop/khachhang/success.jsp?id=" + khachHang.getMaKhacHang());
		}
	}
	
	protected void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("your_name");
		String password = request.getParameter("your_pass");
		password = MaHoa.toMD5(password);
		
		KhachHangDAO khDAO = new KhachHangDAO();
		KhachHang khachHang = khDAO.selectByUsernameAndPassword(username, password);
		
		String url = "";
		if(khachHang != null && khachHang.isTrangThaiXacThuc()) {//đăng nhập thành công
			String urlDirection = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
			HttpSession session = request.getSession();
			session.setAttribute("khachHang", khachHang);//lưu lại object khachHang vao trong session
			response.sendRedirect(urlDirection + "/eshop/index.jsp");
		}
		else if(khachHang == null ) {
			request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
			request.setAttribute("username", username);
			url = "/eshop/khachhang/signin.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
		else if(!khachHang.isTrangThaiXacThuc()) {
			request.setAttribute("error", "Bạn chưa xác thực tài khoản, vui lòng truy cập email đã đăng ký để xác thực");
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
	
	public static String getContent(KhachHang kh) {
		String link = "http://localhost:8080/JSP_Bookstore2/khach-hang?action=confirm-account&makhachhang="+kh.getMaKhacHang()+"&maxacthuc="+kh.getMaXacThuc();
		String content = "<p>TITV.vn xin ch&agrave;o bạn <strong>"+kh.getHoVaTen()+"</strong>,</p>\r\n"
				+ "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"+kh.getMaXacThuc()+"</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
				+ "<p><a href=\""+link+"\">"+link+"</a></p>\r\n"
				+ "<p>Đ&acirc;y l&agrave; email tự động, vui l&ograve;ng kh&ocirc;ng phản hồi email n&agrave;y.</p>\r\n"
				+ "<p>Tr&acirc;n trọng cảm ơn.</p>";
		return content;
	}
}
