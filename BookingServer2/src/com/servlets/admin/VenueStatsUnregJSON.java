package com.servlets.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDAO;
import com.google.gson.Gson;

@WebServlet("/VenuesStatsUnregJSON")
public class VenueStatsUnregJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VenueStatsUnregJSON() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");	
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		
		List<Map<String, Object>> stats = AdminDAO.getBookingsForUnregUsers(venueId, startDate, endDate);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(stats);		
		response.getWriter().write(jsonResult);
	}

}
