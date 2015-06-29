package com.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.Consts;
import com.dao.VenuesDAO;

@WebFilter("/venue_admin_filter")
public class VenueAdminFilter implements Filter {
	
	private static final String ADMIN_LOGIN = "adminLogin";
	private static final String ADMIN_PASSWORD = "adminPassword";
	
	private static final String LOGIN = "venueAdmin";
	private static final String PASSWORD = "canEditVenues";
	
	private FilterConfig config;
	
    public VenueAdminFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		if(isLoggedIn(hRequest.getSession())) {
			chain.doFilter(request, response);
		} else {			
			ServletContext context = config.getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/venue_admin");
			rd.forward(hRequest, hResponse);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
	
	protected boolean isLoggedIn(HttpSession session) {
		boolean result;
		Object oLogin = session.getAttribute("login");
		Object oPassword = session.getAttribute("password");
		Object oVenueId = session.getAttribute("venueId");
		if(oLogin == null || oPassword == null || oVenueId == null) {
			if(session.getAttribute(ADMIN_LOGIN) != null && session.getAttribute(ADMIN_PASSWORD) != null) {
				result = true;
			} else {
				result = false;
			}
		} else {
			int venueId = (Integer)oVenueId;
			String login = oLogin.toString();
			String password = oPassword.toString();
			Map<String, Object> credentialCheck = VenuesDAO.checkAdmin(login, password, venueId);
			if(credentialCheck.get("status").toString().equals(Consts.STATUS_SUCCESS)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

}
