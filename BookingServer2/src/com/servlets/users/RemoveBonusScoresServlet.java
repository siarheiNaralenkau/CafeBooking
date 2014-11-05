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
 * Servlet implementation class RemoveBonusScoresServlet
 * http://bronimesto.by:8080/BookingServer2/remove_bonus_scores?userId=3&scores=20
 */
@WebServlet("/remove_bonus_scores")
public class RemoveBonusScoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String SCORES = "scores";
	private static final String USER_ID = "userId";
	
    public RemoveBonusScoresServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		int userId = Integer.valueOf(request.getParameter(USER_ID));
		int scores = Integer.valueOf(request.getParameter(SCORES));
						
		Map<String, Object> result = UserDAO.removeScores(userId, scores);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);		
		response.getWriter().write(jsonResult);
	}
}
