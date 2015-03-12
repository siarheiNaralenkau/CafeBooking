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

@WebServlet("/VenueStatsForUser")
public class VenueStatsForUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VenueStatsForUser() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");	
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		int userId = Integer.valueOf(request.getParameter("userId"));
		
		List<Map<String, Object>> userBookings = AdminDAO.getBookingsForUser(venueId, userId, startDate, endDate);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(userBookings);		
		response.getWriter().write(jsonResult);
	}

}
