package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public static void sendEmail(String host, String port, final String user, final String pass, String to, String subject, String content) 
			throws AddressException, MessagingException {
		//sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host); // SMTP HOST
		properties.put("mail.smtp.port", port); // TLS 587 SSL 465
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// create Authenticator
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		};

		Session session = Session.getInstance(properties, auth);
		MimeMessage msg = new MimeMessage(session);

		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.setFrom(new InternetAddress(user));
		InternetAddress[] toAddresses = { new InternetAddress(to) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date()); // Quy đinh ngày gửi
//		msg.setReplyTo(InternetAddress.parse(from, false)); // Quy định email nhận phản hồi
		msg.setContent(content, "text/HTML; charset=UTF-8");
		Transport.send(msg);
	}
}
