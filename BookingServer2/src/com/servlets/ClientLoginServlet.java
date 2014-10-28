package com.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.google.gson.Gson;

@WebServlet("/client_login")
public class ClientLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String USER_NAME = "username";
	private static final String PASSWORD = "password";
	
    public ClientLoginServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
				
		Map<String, Object> result = UserDAO.login(userName, password);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
