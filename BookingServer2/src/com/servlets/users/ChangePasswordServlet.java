package com.servlets.users;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/change_password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String E_MAIL = "email";
	private static final String NEW_PASSWORD = "newPassword";
	
    public ChangePasswordServlet() {
        super();       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		String email = request.getParameter(E_MAIL);
		String newPassword = request.getParameter(NEW_PASSWORD);
		Map<String, Object> result = UserDAO.changePassword(email, newPassword);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);		
		response.getWriter().write(jsonResult);
	}

}
