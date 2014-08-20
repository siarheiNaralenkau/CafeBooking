package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Venue;
import com.dao.VenuesDAO;

/**
 * Servlet implementation class EditTablesLoginServlet
 */
@WebServlet("/edit_tables_login")
public class EditTablesLoginServlet extends HttpServlet {
	protected final String ADM = "booking_admin";
	protected final String ADM_PASS = "bgr54TV%*;Q"; 
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("userName");
		String password = request.getParameter("password");
		if(!ADM.equals(login) || !ADM_PASS.equals(password)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");		
			request.setAttribute("errorMsg", "Неправильные логин или пароль");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("login", login);
			List<Venue> venues = VenuesDAO.getVenues(null, null, null);
			request.setAttribute("venues", venues);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add_table_to_venue.jsp");
			dispatcher.forward(request, response);			
		}
	}

}
