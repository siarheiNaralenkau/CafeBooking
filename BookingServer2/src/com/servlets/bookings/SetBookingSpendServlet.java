package com.servlets.bookings;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Booking;
import com.bronimesto.mgr.BookingManager;
import com.constants.Consts;
import com.dao.BookingsDAO;
import com.dao.UserDAO;
import com.dao.VenuesDAO;
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
		if("success".equals(result.get("status"))) {
			Booking booking = VenuesDAO.getBookingById(bookingId);
			// Add bonus scores only for registred users.
			if(booking.getUserId() > 0) {
				UserDAO.addScores(booking.getUserId(), booking.getVenueId(), spentMoney/Consts.BONUS_EXCHANGE_SCORE);
			}
			
			// Send push notification to client.
			BookingManager.notifyBookingSpentDefinedAdmin(bookingId);
		}			
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
