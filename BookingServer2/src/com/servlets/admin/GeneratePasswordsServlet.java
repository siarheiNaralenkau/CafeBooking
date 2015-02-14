package com.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;

/**
 * Servlet implementation class GeneratePasswordsServlet
 * http://bronimesto.by:8080/BookingServer2/generate_passwords
 */
@WebServlet("/generate_passwords")
public class GeneratePasswordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public GeneratePasswordsServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO.generateAdminPasswords();
	}
}
