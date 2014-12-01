package com.servlets.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddDeviceToNotify
 */
@WebServlet("/add_device_to_notify")
public class AddDeviceToNotify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String VENUE_ID = "venue_id";
	private static final String REGISTRATION_ID = "registration_id";
	
    public AddDeviceToNotify() {
        super();       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		String registrationId = request.getParameter(REGISTRATION_ID);
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		
		Map<String, Object> result = AdminDAO.addDeviceToNotify(venueId, registrationId);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
