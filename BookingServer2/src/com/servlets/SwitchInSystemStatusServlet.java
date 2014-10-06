package com.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class SwitchInSystemStatusServlet
 * Adds a venue to our booking system.
 * Example:
 * http://localhost:8080/BookingServer2/switch_in_system?venueId=1&inSystem=true
 * http://localhost:8080/BookingServer2/switch_in_system?venueId=2&inSystem=false
 */
@WebServlet("/switch_in_system")
public class SwitchInSystemStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");	
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		boolean inSystemStatus = Boolean.valueOf(request.getParameter("inSystem"));		
		Map<Object, Object> result = VenuesDAO.switchInSystem(venueId, inSystemStatus);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
