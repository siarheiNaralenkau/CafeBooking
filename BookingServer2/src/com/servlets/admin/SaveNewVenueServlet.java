package com.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveNewVenueServlet
 */
@WebServlet("/save_new_venue")
public class SaveNewVenueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String ADDRESS = "address"; // Mandatory
	private static final String NAME = "name"; // Mandatory
	private static final String TABLES_AMOUNT = "tablesAmount"; // Integer
	private static final String AVG_CHECK = "avgCheck"; // Integer
	private static final String CATEGORY = "category";
	private static final String CITY = "city"; // Mandatory (Default - Гомель)
	private static final String OPEN_TIME = "openTime"; // Time (hh:mm)
	private static final String ADMIN_USER = "adminUser"; // Mandatory ?
	private static final String PHONE = "phone"; // Mandatory
	private static final String COUNTY = "country"; // Mandatory (Default - Беларусь)
	private static final String CLOSE_TIME = "closeTime"; // Time (hh:mm)
	private static final String ADMIN_PASSWORD = "adminPassword"; // Mandatory ?
	private static final String CUISINE = "cuisine";
	private static final String LAT = "lat"; // Double, Mandatory
	private static final String HAS_WIFI = "hasWiFi";
	private static final String ICON_URL = "iconUrl";
	private static final String LNG = "lng"; // Double, Mandatory
	private static final String TAKE_CREDIT_CARDS = "takeCreditCards";
	private static final String RATING = "rating"; // Double(0-10)
	private static final String HAS_OUTDOORS_SEATS = "hasOutdoorsSeats";
	
    public SaveNewVenueServlet() {
        super();        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
