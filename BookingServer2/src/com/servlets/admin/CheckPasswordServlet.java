package com.servlets.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class CheckPasswordServlet
 * http://bronimesto.by:8080/BookingServer2/check_password_servlet?venueId=102&adminPassword=06aad4a4
 */
@WebServlet("/check_password_servlet")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	private static final String VENUE_ID = "venueId";
	private static final String ADMIN_PASSWORD = "adminPassword";
	
    public CheckPasswordServlet() {
        super();       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		response.setContentType("application/json; charset=UTF-8");
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		String adminPassword = request.getParameter(ADMIN_PASSWORD);
		
		result = VenuesDAO.checkAdminPassword(venueId, adminPassword);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);			
		response.getWriter().write(jsonResult);
	}

}
