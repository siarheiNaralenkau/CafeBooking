package com.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VenueStatsForUser")
public class VenueStatsForUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VenueStatsForUser() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Displaying booking statistics for specified user here");
	}

}
