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

/**
 * Servlet implementation class CreateReviewServlet
 */
@WebServlet("/create_review")
public class CreateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String VENUE_ID = "venueId";
	private static final String USER_ID = "userId";
	private static final String MARK_FOOD = "markFood";
	private static final String MARK_SERVICE = "markService";
	private static final String MARK_ATMOSPHERE = "markAtmosphere";
	private static final String MARK_PRICE_QUALITY = "markPriceQuality";
	private static final String COMMENTS_GOOD = "commentsGood";
	private static final String COMMENTS_BAD = "commentsBad";
	
    public CreateReviewServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		String comentsGood = "";
		String comentsBad = "";
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		int userId = Integer.valueOf(request.getParameter(USER_ID));
		float markFood = Float.valueOf(request.getParameter(MARK_FOOD));
		float markService = Float.valueOf(request.getParameter(MARK_SERVICE));
		float markAtmosphere = Float.valueOf(request.getParameter(MARK_ATMOSPHERE));
		float markPriceQuality = Float.valueOf(request.getParameter(MARK_PRICE_QUALITY));
		if(request.getParameterMap().containsKey(COMMENTS_GOOD)) {
			comentsGood = request.getParameter(COMMENTS_GOOD);
		}
		if(request.getParameterMap().containsKey(COMMENTS_BAD)) {
			comentsBad = request.getParameter(COMMENTS_BAD);
		}
		
		Map<String, Object> result = UserDAO.createReview(venueId, userId, markFood, markService, markAtmosphere, markPriceQuality,
				comentsGood, comentsBad);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
