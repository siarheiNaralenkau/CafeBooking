package com.servlets.venues;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class VenuesFreePlacesServlet
 * http://localhost:8080/BookingServer2/get_venues_free_places
 */
@WebServlet("/get_venues_free_places")
public class VenuesFreePlacesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public VenuesFreePlacesServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		List<Map<String, Object>> result = VenuesDAO.getVenuesFreeTables();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
