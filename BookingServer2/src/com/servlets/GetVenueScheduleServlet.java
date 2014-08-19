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
 * Servlet implementation class GetVenueScheduleServlet
 */
@WebServlet("/get_venue_schedule")
public class GetVenueScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");	
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		
		Map<Object, Object> result = VenuesDAO.getVenueSchedule(venueId);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
