package com.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;

/**
 * Servlet implementation class SaveVenueChangesServlet
 */
@WebServlet("/save_venue_changes")
public class SaveVenueChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VENUE_ID = "venueId";
	private static final String VENUE_NAME = "name";
	private static final String PHONE = "phone";
	private static final String ADDRESS = "address";
	private static final String HAS_FREE_SEATS = "hasFreeSeats";
	private static final String ADMIN_USER = "adminUser";
	private static final String TABLES_AMOUNT = "tablesAmount";
	private static final String ICON_URL = "iconUrl";
	private static final String OPEN_TIME = "openTime";
	private static final String CLOSE_TIME = "closeTime";
	private static final String CUISINE = "cuisine";
	private static final String HAS_WIFI = "hasWiFi";
	private static final String TAKE_CREDIT_CARDS = "takeCreditCards";
	private static final String HAS_OUTDOORS_SEATS = "hasOutdoorsSeats";
	private static final String CATEGORY = "category";
       
    public SaveVenueChangesServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		String name = request.getParameter(VENUE_NAME);
		String phone = request.getParameter(PHONE);
		String address = request.getParameter(ADDRESS);
		boolean hasFreeSeats = Boolean.valueOf(request.getParameter(HAS_FREE_SEATS));
		String adminUser = request.getParameter(ADMIN_USER);
		int tablesAmount = Integer.valueOf(request.getParameter(TABLES_AMOUNT));
		String iconURL = request.getParameter(ICON_URL);
		String openTime = request.getParameter(OPEN_TIME);
		String closeTime = request.getParameter(CLOSE_TIME);
		String cuisine = request.getParameter(CUISINE);
		boolean hasWiFi = Boolean.valueOf(request.getParameter(HAS_WIFI));
		boolean takeCreditCards = Boolean.valueOf(request.getParameter(TAKE_CREDIT_CARDS));
		boolean hasOutdoorsSeats = Boolean.valueOf(request.getParameter(HAS_OUTDOORS_SEATS));
		String category = request.getParameter(CATEGORY);
		Map<String, Object> updateResult = VenuesDAO.updateVenue(venueId, name, phone, address, hasFreeSeats, 
				adminUser, tablesAmount, iconURL, openTime, closeTime, cuisine, hasWiFi, takeCreditCards, 
				hasOutdoorsSeats, category);
		if(updateResult.get("status").equals("success")) {
			// Redirect to venuesList.
			ServletContext servletContext = getServletContext();
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/get_grouped_venues");
			dispatcher.forward(request, response);
		} else {
			System.out.println(updateResult.get("error"));
		}
	}

}
