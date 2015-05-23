package com.servlets.admin.venue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Venue;
import com.constants.Consts;
import com.dao.VenuesDAO;

/**
 * Servlet implementation class VenueAdminLoginServlet
 */
@WebServlet("/venue_admin_login")
public class VenueAdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VENUE_ADMIN_LOGIN = "vAdminLogin";
	private static final String VENUE_ADMIN_PASSWORD = "vAdminPassword";
	private static final String VENUE_ID = "venueId";	
           
    public VenueAdminLoginServlet() {
        super();        
    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		String login = request.getParameter(VENUE_ADMIN_LOGIN);
		String password = request.getParameter(VENUE_ADMIN_PASSWORD);
		
		Map<String, Object> credentialsCheck = VenuesDAO.checkAdmin(login, password, venueId);
		String checkStatus = credentialsCheck.get("status").toString();
		if(checkStatus.equals(Consts.STATUS_SUCCESS)) {
			Venue venue = VenuesDAO.getVenueById(venueId);
			request.setAttribute("venue", venue);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			String endDate = dateFormat.format(calendar.getTime());
			calendar.add(Calendar.DATE, -30);
			String startDate = dateFormat.format(calendar.getTime());
			String redirectURL = String.format("/venue_stats_jq.jsp?venueId=%s&dateFrom=%s&dateTo=%s", venueId, startDate, endDate);
			RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
			dispatcher.forward(request, response);
		}		
	}

}
