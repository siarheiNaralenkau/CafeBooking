package com.servlets.users;

import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.google.gson.Gson;
import com.sun.mail.smtp.SMTPTransport;

/**
 * Servlet implementation class RestorePasswordServlet
 */
@WebServlet("/restore_password")
public class RestorePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String E_MAIL = "email";
	
    public RestorePasswordServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("application/json; charset=UTF-8");
		String email = request.getParameter(E_MAIL);
		Map<String, Object> opResult = UserDAO.getPassword(email);
		if("success".equals(opResult.get("status"))) {
			opResult.put("message", "Пароль отправлен на электронный ящик: " + email);
			String restoredPassword = (String)opResult.get("password");
			sendPasswordEmail(email, restoredPassword);
		} 
		Gson gson = new Gson();
		String jsonResult = gson.toJson(opResult);	
		response.getWriter().write(jsonResult);				        
	}
	
	private void sendPasswordEmail(String email, String password) {
		String hostName = "smtp.gmail.com";		
		String username = "naralenkov2010";
		String mailServerPassword = "qwerty12Q";								
				
		String from = "naralenkov2010@gmail.com";
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		
		Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        
        props.put("mail.smtps.quitwait", "false");
        
        Session session = Session.getInstance(props, null);
        
        final MimeMessage msg = new MimeMessage(session);
        
        try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
			msg.setSubject("Forgotten password for bronimesto.by");
			msg.setText("Ваш пароль: " + password, "utf-8");
			msg.setSentDate(new Date());
			SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
			t.connect(hostName, username, mailServerPassword);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
