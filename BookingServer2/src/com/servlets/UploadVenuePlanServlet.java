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
 * Servlet implementation class UploadVenuePlanServlet
 * Example
 * http://bronimesto.by:8080/BookingServer2/upload_venue_plan?venueId=1&venuePlan=someJson
 * http://localhost:8080/BookingServer2/upload_venue_plan?venueId=100&venuePlan={someJson}
 */
@WebServlet("/upload_venue_plan")
public class UploadVenuePlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		String venuePlan = request.getParameter("venuePlan");
		
		Map<String, Object> result = VenuesDAO.setVenuePlan(venueId, venuePlan);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
