package com.servlets.admin.venue;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(true);
		String vAdminLogin = (String)session.getAttribute(VENUE_ADMIN_LOGIN);
		String vAdminPassword = (String)session.getAttribute(VENUE_ADMIN_PASSWORD);
		int venueId = (Integer)session.getAttribute(VENUE_ID);
		if(vAdminLogin == null || vAdminLogin == null || venueId == 0) {
			vAdminLogin = request.getParameter(VENUE_ADMIN_LOGIN);
			vAdminPassword = request.getParameter(VENUE_ADMIN_PASSWORD);
			venueId = Integer.valueOf(request.getParameter(VENUE_ID));		
			
			Map<String, Object> checkAdmin = VenuesDAO.checkAdmin(vAdminLogin, vAdminPassword, venueId);
			if(checkAdmin.get("status").equals("success")) {
				// TODO - receive - id-name list of venues(Order by name).
	//			VenuesDAO.getVenuesShort();
			} else {
				// TODO - Process login error.
			}				
		} else {
			// TODO - Verify stored credentials for venue and redirect to venue admin page or error page.			
		}
	}	

}
