package com.servlets.users;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.User;
import com.dao.UserDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateUserServlet
 * http://bronimesto.by:8080/BookingServer2/update_user?email=siarhei_naralenkau@epam.com&phone=+375447756879&password=p12P&userId=2
 * http://bronimesto.by:8080/BookingServer2/update_user?name=Ivan&surname=Petrov&userId=3
 */
@WebServlet("/update_user")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_ID = "userId";
	
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String E_MAIL = "email";
	private static final String PHONE = "phone";
	private static final String PASSWORD = "password";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		if(!request.getParameterMap().containsKey(USER_ID)) {
			result.put("status", "failure");
			result.put("error", "User's ID is undefined");
		} else {
			int userId = Integer.valueOf(request.getParameter(USER_ID));
			User user = UserDAO.getUserById(userId);
			if(user == null) {
				result.put("status", "failure");
				result.put("error", "User with id " + String.valueOf(userId) + " doesn't exists");
			} else {
				if(request.getParameterMap().containsKey(NAME)) {
					user.setName(request.getParameter(NAME));
				}
				if(request.getParameterMap().containsKey(SURNAME)) {
					user.setSurname(request.getParameter(SURNAME));
				}
				if(request.getParameterMap().containsKey(E_MAIL)) {
					user.setEmail(request.getParameter(E_MAIL));
				}
				if(request.getParameterMap().containsKey(PHONE)) {
					user.setPhone(request.getParameter(PHONE));
				}
				if(request.getParameterMap().containsKey(PASSWORD)) {
					user.setPassword(request.getParameter(PASSWORD));
				}
				result = UserDAO.updateUser(userId, user);
			}
		}
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}
}
