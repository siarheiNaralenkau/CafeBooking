package com.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin_servlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String ADMIN_LOGIN = "adminLogin";
	private static final String ADMIN_PASSWORD = "adminPassword";
	
	private static final String LOGIN = "venueAdmin";
	private static final String PASSWORD = "canEditVenues";
	
    public AdminServlet() {
        super();       
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession(true);
		String adminLogin = (String)session.getAttribute(ADMIN_LOGIN);
		String adminPassword = (String)session.getAttribute(ADMIN_PASSWORD);
		if(adminLogin == null || adminPassword == null) {
			adminLogin = request.getParameter(ADMIN_LOGIN);
			adminPassword = request.getParameter(ADMIN_PASSWORD);
			if(!LOGIN.equals(adminLogin) || !PASSWORD.equals(adminPassword)) {
				forwardLoginPage(request, response);
			} else {
				saveCredentialsInSession(session, adminLogin, adminPassword);
				forwardVenuesList(request, response);
			}
		} else {
			if(adminLogin.equals(LOGIN) && adminPassword.equals(PASSWORD)) {
				forwardVenuesList(request, response);
			} else {
				forwardLoginPage(request, response);
			}
		}
	}
	
	protected void forwardLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void forwardVenuesList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServletContext servletContext = getServletContext();
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/get_grouped_venues");
		dispatcher.forward(request, response);
	}
	
	protected void saveCredentialsInSession(HttpSession session, String login, String password) {
		session.setMaxInactiveInterval(3600);
		session.setAttribute("adminLogin", login);
		session.setAttribute("adminPassword", password);		
	}

}
