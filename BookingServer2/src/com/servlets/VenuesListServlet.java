package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.beans.Venue;
import com.dao.VenuesDAO;
import com.google.gson.Gson;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

/**
 * Servlet implementation class VenuesListServlet
 */
@WebServlet("/VenuesListServlet")
public class VenuesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private static final int DEFAULT_LIMIT = 30;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {			
			request.setCharacterEncoding("UTF-8");
			double lat = Double.valueOf(request.getParameter("lat"));
			double lng = Double.valueOf(request.getParameter("lng"));
			int limit = DEFAULT_LIMIT;
			if(request.getParameterMap().containsKey("limit")) {
				limit = Integer.valueOf(request.getParameter("limit"));
			}
			String responseType = request.getParameter("responseType");
			List<Venue> nearestVenues = VenuesDAO.getVenues(lat, lng, limit);
			System.out.println("Venues list: ");
			for(Venue venue: nearestVenues) {
				System.out.println(venue.getName() + ": " + venue.getDistance() + " metres");
			}
			request.setAttribute("venues", nearestVenues);
			if(responseType.equals("json")) {
				response.setContentType("application/json;charset=utf-8");
				Gson gson = new Gson();
				String jsonResult = gson.toJson(nearestVenues);			
				response.getWriter().write(jsonResult);
			} else if(responseType.equals("jsp")) {
				forwardToList(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
	
	protected void forwardToList(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_venues.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
