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
 * Servlet implementation class AddTableServlet
 * Example 1:
 * http://localhost:8080/BookingServer2/add_table?venueId=1&places=4&xPos=1&yPos=2&number=1&positionNotes=2
 * Example 2(Only mandatory parameters):
 * http://localhost:8080/BookingServer2/add_table?venueId=1&places=2
 */
@WebServlet("/add_table")
public class AddTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VENUE_ID = "venueId";
	private static final String PLACES = "places";
	private static final String X_POS = "xPos";
	private static final String Y_POS = "yPos";
	private static final String NUMBER = "number";
	private static final String POSITION_NOTES = "positionNotes";
	private static final String PHOTO_URL = "photoUrl";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		Integer venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		Integer places = Integer.valueOf(request.getParameter(PLACES));
				
		Integer xPos = null, yPos = null, number = null, positionNotes = 1;
		String photoUrl = null;
		
		if(request.getParameterMap().containsKey(X_POS)) {
			xPos = Integer.valueOf(request.getParameter(X_POS));
		}
		if(request.getParameterMap().containsKey(Y_POS)) {
			yPos = Integer.valueOf(request.getParameter(Y_POS));
		}
		if(request.getParameterMap().containsKey(NUMBER)) {
			number = Integer.valueOf(request.getParameter(NUMBER));
		}
		if(request.getParameterMap().containsKey(POSITION_NOTES)) {
			positionNotes = Integer.valueOf(request.getParameter(POSITION_NOTES));
		}
		if(request.getParameterMap().containsKey(PHOTO_URL)) {
			photoUrl = request.getParameter(PHOTO_URL);
		}
		
		Map<String, Object> result = VenuesDAO.addTable(venueId, xPos, yPos, places, number, positionNotes, photoUrl);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
