package controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Email;

/**
 * Servlet implementation class SendEmail
 */
@WebServlet("/send-email")
public class SendEmail extends HttpServlet {
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
       
    public SendEmail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recipient = request.getParameter("recipient");
		String subject = "Thông báo nhận thông tin " + System.currentTimeMillis();
		String content = "Bạn đã đăng ký thành công việc nhận email từ website của chúng tôi, "
				+ "nhấn vào <a href=\"{urlDirection}\">link</a> này để đến website!!";
		String resultMessage = "camboo";
		
		try {
			Email.sendEmail(this.host, this.port, this.user, this.pass, recipient, subject, content);
			resultMessage = "The e-mail was sent successfully";
		} catch (AddressException e) {
			e.printStackTrace();
			System.out.println("email was not sent");
		} catch (MessagingException e) {
			e.printStackTrace();
			resultMessage = "There were an error: " + e.getMessage();
			System.out.println("email was not sent");
		} catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			 System.out.println(resultMessage);
			 request.setAttribute("Message", resultMessage);
			 getServletContext().getRequestDispatcher("/eshop/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
