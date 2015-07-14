package com.servlets.admin.venue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Venue;
import com.constants.Consts;
import com.dao.AdminDAO;
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
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String VENUE = "venue";
	private static final String STATUS = "status";
	private static final String VENUES_LIST = "venuesList";
	private static final String ERROR_MSG = "errorMsg";
	
	private static final String INCORRECT_CREDENTIALS_TXT = "Неверный логин или пароль";
	
	private static final String VENUE_LOGIN_URL = "/venue_login.jsp";
	private static final String VENUE_STATS_URL = "/venue_stats_jq.jsp?venueId=%s&dateFrom=%s&dateTo=%s";	
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
           
    public VenueAdminLoginServlet() {
        super();        
    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(isLoggedIn(request.getSession(), request)) {
			int venueId = (Integer)request.getSession().getAttribute(VENUE_ID);
			request.setAttribute("singleVenueAdmin", true);
			forwardToStats(request, response, venueId);
		} else {		
			int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
			String login = request.getParameter(VENUE_ADMIN_LOGIN);
			String password = request.getParameter(VENUE_ADMIN_PASSWORD);
			
			Map<String, Object> credentialsCheck = VenuesDAO.checkAdmin(login, password, venueId);
			String checkStatus = credentialsCheck.get(STATUS).toString();
			if(checkStatus.equals(Consts.STATUS_SUCCESS)) {
				saveCredentialsInSession(request.getSession(), login, password, venueId);
				request.setAttribute("singleVenueAdmin", true);
				forwardToStats(request, response, venueId);
			} else {
				request.setAttribute(ERROR_MSG, INCORRECT_CREDENTIALS_TXT);
				request.setAttribute(VENUES_LIST, AdminDAO.getVenuesShort());
				RequestDispatcher dispatcher = request.getRequestDispatcher(VENUE_LOGIN_URL);
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void forwardToStats(HttpServletRequest request, HttpServletResponse response, int venueId) throws ServletException, IOException {
		Venue venue = VenuesDAO.getVenueById(venueId);
		request.setAttribute(VENUE, venue);
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		String endDate = dateFormat.format(calendar.getTime());
		calendar.add(Calendar.DATE, -30);
		String startDate = dateFormat.format(calendar.getTime());
		String redirectURL = String.format(VENUE_STATS_URL, venueId, startDate, endDate);
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
		dispatcher.forward(request, response);
	}
	
	protected void saveCredentialsInSession(HttpSession session, String login, String password, int venueId) {
		session.setMaxInactiveInterval(3600);
		session.setAttribute(LOGIN, login);
		session.setAttribute(PASSWORD, password);
		session.setAttribute(VENUE_ID, venueId);		
	}

	// TODO Add additional heck if venueId from request parameter(if presents) equals venueId from session.	
	protected boolean isLoggedIn(HttpSession session, HttpServletRequest request) {
		boolean result;
		Object oLogin = session.getAttribute(LOGIN);
		Object oPassword = session.getAttribute(PASSWORD);
		Object oVenueId = session.getAttribute(VENUE_ID);
		if(oLogin == null || oPassword == null || oVenueId == null) {
			result = false;
		} else {
			String rVenueId = request.getParameter(VENUE_ID);
			String venueId = String.valueOf(oVenueId);
			String login = oLogin.toString();
			String password = oPassword.toString();
			Map<String, Object> credentialCheck = VenuesDAO.checkAdmin(login, password, Integer.valueOf(venueId));
			if(credentialCheck.get(STATUS).toString().equals(Consts.STATUS_SUCCESS) && rVenueId.equals(venueId)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}
}
