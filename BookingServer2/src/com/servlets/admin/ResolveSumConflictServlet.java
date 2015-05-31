package com.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdminDAO;

@WebServlet("/ResolveSumConflictServlet")
public class ResolveSumConflictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResolveSumConflictServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		int newCheckSum = Integer.valueOf(request.getParameter("newSum"));
		String resolution = request.getParameter("resolution");
		System.out.println("Resolution: " + resolution);
		
		AdminDAO.resolveSumConflict(bookingId, newCheckSum, resolution);
	}

}
