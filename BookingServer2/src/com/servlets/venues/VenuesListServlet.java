package com.servlets.venues;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * boolean has_free_tables
 * boolean rating_order
 * String type
 * String cuisine
 * boolean has_wifi
 * boolean take_credit_cards
 * boolean has_outdoors_places
 * limit - Max amount of venues to return
 * Example:
 * http://localhost:8080/BookingServer2/venues_list?responseType=json&lat=50&lng=30&limit=10
 * http://bronimesto.by:8080/BookingServer2/venues_list?lat=52.45852&lng=31.018&limit=10 - 10 nearest venues to my home
 * http://bronimesto.by:8080/BookingServer2/venues_list?limit=10 - 10 nearest venues to Lenin's square.
 */ 
@WebServlet("/venues_list")
public class VenuesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		disableCaching(response);
		
		Double lat = null, lng = null;
		Boolean hasFreeTables = false, ratingOrder = false, hasWifi = false, takeCreditCards = false, hasOutdoorsPlaces = false;
		String type = null, cuisine = null;
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
		if(request.getParameterMap().containsKey("has_free_tables")) {
			hasFreeTables = Boolean.valueOf(request.getParameter("has_free_tables"));
		}
		if(request.getParameterMap().containsKey("rating_order")) {
			ratingOrder = Boolean.valueOf(request.getParameter("rating_order"));
		}
		if(request.getParameterMap().containsKey("has_wifi")) {
			hasWifi = Boolean.valueOf(request.getParameter("has_wifi"));
		}
		if(request.getParameterMap().containsKey("take_credit_cards")) {
			takeCreditCards = Boolean.valueOf(request.getParameter("take_credit_cards"));
		}
		if(request.getParameterMap().containsKey("has_outdoors_places")) {
			hasOutdoorsPlaces = Boolean.valueOf(request.getParameter("has_outdoors_places"));
		}
		if(request.getParameterMap().containsKey("type")) {
			type = request.getParameter("type");
		}
		if(request.getParameterMap().containsKey("cuisine")) {
			cuisine = request.getParameter("cuisine");
		}
		List<Venue> nearestVenues = VenuesDAO.getVenues(lat, lng, limit, hasFreeTables, ratingOrder, hasWifi, takeCreditCards, hasOutdoorsPlaces,
				type, cuisine);									
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
