package com.servlets.bookings;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookingsDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class SetBookingSpendServlet
 * http://bronimesto.by:8080/BookingServer2/set_booking_spend?bookingId=31&spentMoney=350000
 */
@WebServlet("/set_booking_spend")
public class SetBookingSpendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String SPENT_MONEY = "spentMoney";
	private static final String BOOKING_ID = "bookingId";
    
    public SetBookingSpendServlet() {
        super();    
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		int spentMoney = Integer.valueOf(request.getParameter(SPENT_MONEY));
		int bookingId = Integer.valueOf(request.getParameter(BOOKING_ID));
		
		Map<String, Object> result = BookingsDAO.setBookingSpent(spentMoney, bookingId);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
