package com.filters;

import java.io.IOException;

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

/**
 * Servlet Filter implementation class ApplicationAdminFilter
 */
@WebFilter("/app_admin_filter")
public class ApplicationAdminFilter implements Filter {
	
	private FilterConfig config;
	
	private static final String ADMIN_LOGIN = "adminLogin";
	private static final String ADMIN_PASSWORD = "adminPassword";
	
	private static final String LOGIN = "venueAdmin";
	private static final String PASSWORD = "canEditVenues";
	
    public ApplicationAdminFilter() {    
    }
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		if(isLoggedAsAdmin(hRequest.getSession())) {
			chain.doFilter(request, response);
		} else {			
			ServletContext context = config.getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/admin_servlet");
			rd.forward(hRequest, hResponse);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
	
	public boolean isLoggedAsAdmin(HttpSession session) {
		boolean result;
		
		String adminLogin = (String)session.getAttribute(ADMIN_LOGIN);
		String adminPassword = (String)session.getAttribute(ADMIN_PASSWORD);
		
		if(!LOGIN.equals(adminLogin) || !PASSWORD.equals(adminPassword)) {
			result = false;
		} else {
			result = true;
		} 
		
		return result;
	}

}
