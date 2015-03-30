package com.servlets.users;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bronimesto.mgr.BookingManager;
import com.dao.UserDAO;
import com.google.gson.Gson;


/**
 * Servlet implementation class RestorePasswordServlet
 */
@WebServlet("/restore_password")
public class RestorePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String E_MAIL = "email";
	
    public RestorePasswordServlet() {
        super();       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("application/json; charset=UTF-8");
		String email = request.getParameter(E_MAIL);
		Map<String, Object> opResult = UserDAO.getPassword(email);
		if("success".equals(opResult.get("status"))) {
			opResult.put("message", "Пароль отправлен на электронный ящик: " + email);
			String restoredPassword = (String)opResult.get("password");
			BookingManager.sendPasswordEmail(email, restoredPassword);
		} 
		Gson gson = new Gson();
		String jsonResult = gson.toJson(opResult);	
		response.getWriter().write(jsonResult);				        
	}
		
}
