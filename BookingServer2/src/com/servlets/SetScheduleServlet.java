package com.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.ScheduleEntry;
import com.dao.VenuesDAO;
import com.google.gson.Gson;

/**
 * Servlet implementation class SetScheduleServlet
 * Example:
 * http://localhost:8080/BookingServer2/set_schedule?venueId=1&schedule=[{'dayId': 1, 'openTime': '12:00', 'closeTime': '23:00'}, {'dayId': 6, 'openTime': '14:00', 'closeTime': '2:00'}]
 */
@WebServlet("/set_schedule")
public class SetScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");	
		
		int venueId = Integer.valueOf(request.getParameter("venueId"));
		String sSchedule = request.getParameter("schedule");
		Gson gsonParser = new Gson();
		ScheduleEntry[] scheduleArray = gsonParser.fromJson(sSchedule, ScheduleEntry[].class);
		for(ScheduleEntry daySchedule: scheduleArray) {
			daySchedule.setVenueId(venueId);			
		}
		Map<String, Object> result = VenuesDAO.setVenueSchedule(venueId, scheduleArray);
		Gson resultGson = new Gson();
		String jsonResult = resultGson.toJson(result);	
		response.getWriter().write(jsonResult);
	}

}
