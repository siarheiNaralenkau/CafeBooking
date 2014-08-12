package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class VenuesListServlet
 */
@WebServlet("/VenuesListServlet")
public class VenuesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {			
			request.setCharacterEncoding("UTF-8");
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
				response.setContentType("application/json;charset=utf-8");
				Gson gson = new Gson();
				String jsonResult = gson.toJson(nearestVenues);			
				response.getWriter().write(jsonResult);
			} else if(responseType.equals("jsp")) {
				request.setAttribute("venues", nearestVenues);
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
