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
 * Servlet implementation class DeleteVisitorBookingServlet
 * http://localhost:8080/BookingServer2/delete_visitor_booking?bookingId=2
 */
@WebServlet("/delete_visitor_booking")
public class DeleteVisitorBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String BOOKING_ID = "bookingId";
          
    public DeleteVisitorBookingServlet() {
        super();       
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = Integer.valueOf(request.getParameter(BOOKING_ID));
		Map<String, Object> result = BookingsDAO.deleteVisitorBooking(bookingId);
		
		response.setContentType("application/json; charset=UTF-8");		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
