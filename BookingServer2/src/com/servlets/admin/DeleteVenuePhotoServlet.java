package com.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.VenuesDAO;

/**
 * Servlet implementation class DeleteVenuePhotoServlet
 */
@WebServlet("/delete_venue_photo")
public class DeleteVenuePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VENUE_ID = "venueId";
    private static final String PHOTO_ID = "photoId";
	
    public DeleteVenuePhotoServlet() {
        super();       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int photoId = Integer.valueOf(request.getParameter(PHOTO_ID));	
		int venueId = Integer.valueOf(request.getParameter(VENUE_ID));
		boolean isDeleted = VenuesDAO.deleteVenuePhoto(photoId);
		if(isDeleted) {
			ServletContext servletContext = getServletContext();
			RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/edit_venue_open?venueId=" +venueId);
			dispatcher.forward(request, response);
		}
	}

}
