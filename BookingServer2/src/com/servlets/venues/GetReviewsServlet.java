package com.servlets.venues;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetReviewsServlet
 * Received the list of reviews for specified venue.
 * http://bronimesto.by:8080/BookingServer2/get_reviews_servlet?venueId=1
 */
@WebServlet("/get_reviews_servlet")
public class GetReviewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public GetReviewsServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		
		List<Map<String, Object>> result = VenuesDAO.getReviews(venueId);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
