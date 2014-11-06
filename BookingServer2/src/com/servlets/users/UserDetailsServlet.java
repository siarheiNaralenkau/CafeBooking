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

@WebServlet("/user_details")
public class UserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ID = "userId";
	
    public UserDetailsServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		int userId = Integer.valueOf(request.getParameter(USER_ID));
		Map<String, Object> userDetails = UserDAO.getUserDetails(userId);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(userDetails);			
		response.getWriter().write(jsonResult);
	}

}
