package com.servlets.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

@WebServlet("/get_venue_status")
public class GetVenueStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetVenueStatusServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");			
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));				
		Map<String, Object> venueStatus = VenuesDAO.getVenueStatus(venueId);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(venueStatus);	
		response.getWriter().write(jsonResult);
	}

}
