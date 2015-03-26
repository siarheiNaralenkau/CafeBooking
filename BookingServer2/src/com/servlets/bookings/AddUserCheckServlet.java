package com.servlets.bookings;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bronimesto.mgr.BookingManager;
import com.dao.BookingsDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddUserCheckServlet
 * http://bronimesto.by:8080/BookingServer2/add_user_check?spentMoney=300000&bookingId=5
 */
@WebServlet("/add_user_check")
public class AddUserCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String SPENT_MONEY = "spentMoney";
	private static final String BOOKING_ID = "bookingId";
	
    public AddUserCheckServlet() {
        super();        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		int spentMoney = Integer.valueOf(request.getParameter(SPENT_MONEY));
		int bookingId = Integer.valueOf(request.getParameter(BOOKING_ID));
		Map<String, Object> result = BookingsDAO.setVisitorSpent(spentMoney, bookingId);
		BookingManager.notifyBookingSpentDefinedVisitor(bookingId);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);					
	}

}
