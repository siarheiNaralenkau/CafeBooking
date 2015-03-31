package com.servlets.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookingsDAO;
import com.google.gson.Gson;

@WebServlet("/VenueStatsRegJSON")
public class VenueStatsRegJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VenueStatsRegJSON() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");	
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		
		List<Map<String, Object>> bookingsRegistred = BookingsDAO.getBookingsForRegistredUsers(venueId, startDate, endDate);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(bookingsRegistred);		
		response.getWriter().write(jsonResult);
	}

}
