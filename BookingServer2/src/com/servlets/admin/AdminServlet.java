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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession(true);
		String adminLogin = (String)session.getAttribute(ADMIN_LOGIN);
		String adminPassword = (String)session.getAttribute(ADMIN_PASSWORD);
		if(adminLogin == null || adminPassword == null) {
			adminLogin = request.getParameter(ADMIN_LOGIN);
			adminPassword = request.getParameter(ADMIN_PASSWORD);
			if(!LOGIN.equals(adminLogin) || !PASSWORD.equals(adminPassword)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_login.jsp");
				dispatcher.forward(request, response);
			} else {
				session.setAttribute(ADMIN_LOGIN, adminLogin);
				session.setAttribute(adminPassword, ADMIN_PASSWORD);
				ServletContext servletContext = getServletContext();
				RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/get_grouped_venues");
				dispatcher.forward(request, response);
			}
		} 
	}

}
