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

/**
 * Servlet implementation class SetFreeTablesServlet
 * http://bronimesto.by:8080/BookingServer2/set_free_tables
 * Params:
 * 	adminUser *
 *  adminPassword *
 *  venueId *
 *  freeTables *
 */
@WebServlet("/set_free_tables")
public class SetFreeTablesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER = "adminUser";
	private static final String ADMIN_PASSWORD = "adminPassword";
	private static final String VENUE_ID = "venueId";
	private static final String FREE_TABLES = "freeTables";
	
    public SetFreeTablesServlet() {
        super();
       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		Map<String, Object> result;
		String adminUser = request.getParameter(ADMIN_USER);
		String adminPassword = request.getParameter(ADMIN_PASSWORD);
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		int freeTables = Integer.valueOf(request.getParameter(FREE_TABLES));
		Map<String, Object> checkAdmin = VenuesDAO.checkAdmin(adminUser, adminPassword, venueId);
		if(checkAdmin.get("status").equals("success")) {
			result = VenuesDAO.setFreeTables(venueId, freeTables);
		} else {
			result = checkAdmin;
		}
				
		String jsonResult = gson.toJson(result);			
		response.getWriter().write(jsonResult);
	}

}
