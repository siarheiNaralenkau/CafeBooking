package com.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserBookingsUnregServlet")
public class UserBookingsUnregServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserBookingsUnregServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String venueId = request.getParameter("venueId");
		System.out.println(userName);
	}

}
