package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Venue;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Receives 4 nonmandatory parameters:
 * responseType: Expected response format(json or jsp) - default is json.
 * lat - latitude - Default is center of Gomel for now
 * lng - longutude - Default is center of Gomel for now
 * limit - Max amount of venues to return
 * Example:
 * http://localhost:8080/BookingServer2/venues_list?responseType=json&lat=50&lng=30&limit=10
 * http://bronimesto.by:8080/BookingServer2/venues_list?lat=52.45852&lng=31.018&limit=10 - 10 nearest venues to my home
 * http://bronimesto.by:8080/BookingServer2/venues_list?limit=10 - 10 nearest venues to Lenin's square.
 */ 
public class VenuesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		disableCaching(response);
		
		Double lat = null, lng = null;
		Integer limit = null;
		String responseType = "json";
		if(request.getParameterMap().containsKey("lat")) {
			lat = Double.valueOf(request.getParameter("lat"));
		} 
		if(request.getParameterMap().containsKey("lng")) {
			lng = Double.valueOf(request.getParameter("lng"));
		}			
		if(request.getParameterMap().containsKey("limit")) {
			limit = Integer.valueOf(request.getParameter("limit"));
		}
		if(request.getParameterMap().containsKey("responseType")) {
			responseType = request.getParameter("responseType");
		}
		List<Venue> nearestVenues = VenuesDAO.getVenues(lat, lng, limit);									
		if(responseType.equals("json")) {
			response.setContentType("application/json; charset=UTF-8");
			Gson gson = new Gson();
			String jsonResult = gson.toJson(nearestVenues);			
			response.getWriter().write(jsonResult);
		} else if(responseType.equals("jsp")) {
			request.setAttribute("venues", nearestVenues);
			forwardToList(request, response);
		}		
	}	
	
	private void disableCaching(HttpServletResponse response) {
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
	}
	
	protected void forwardToList(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_venues.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
