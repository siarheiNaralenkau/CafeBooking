package com.servlets.admin.venue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Venue;
import com.dao.AdminDAO;
import com.dao.VenuesDAO;

/**
 * Servlet implementation class VenueAdminServlet
 */
@WebServlet("/venue_admin")
public class VenueAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VENUE_ADMIN_LOGIN = "vAdminLogin";
	private static final String VENUE_ADMIN_PASSWORD = "vAdminPassword";
	private static final String VENUE_ID = "venueId";	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenueAdminServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Map<String, Object>> venuesShort = AdminDAO.getVenuesShort();
    	request.setAttribute("venuesList", venuesShort);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/venue_login.jsp");
		dispatcher.forward(request, response);
    }
}
