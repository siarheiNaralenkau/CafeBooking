package com.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/create_user")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String E_MAIL = "email";
	private static final String PHONE = "phone";
	private static final String PASSWORD = "password";
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		String name = "";
		String surname = "";
		String phone = "";
		String eMail = "";
		String password = "";
		Map<String, Object> result = new HashMap<String, Object>();
		if(!request.getParameterMap().containsKey(E_MAIL)) {
			result.put("status", "failure");
			result.put("error", "Не указано имя пользователя!");
		} else if(!request.getParameterMap().containsKey("PASSWORD")) {
			result.put("status", "failure");
			result.put("error", "Не задан пароль!");
		} else {
			eMail = request.getParameter(E_MAIL);
			password = request.getParameter(PASSWORD);
			if(request.getParameterMap().containsKey(NAME)) {
				name = request.getParameter(NAME);
			}
			if(request.getParameterMap().containsKey(SURNAME)) {
				name = request.getParameter(SURNAME);
			}
			if(request.getParameterMap().containsKey(PHONE)) {
				name = request.getParameter(PHONE);
			}
			result = UserDAO.registerUser(name, surname, eMail, phone, password);
		}
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
