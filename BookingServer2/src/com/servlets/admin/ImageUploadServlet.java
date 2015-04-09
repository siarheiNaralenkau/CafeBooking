package com.servlets.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.beans.Venue;
import com.constants.Consts;
import com.dao.VenuesDAO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@WebServlet("/upload_venue_photos")
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public ImageUploadServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        List<Map<String, String>> uploadedPhotos = new ArrayList<Map<String, String>>();
	        int venueId = 0;
	        for (FileItem item : items) {
	            if (item.isFormField()) {	
	            	String fieldName = item.getFieldName();
	            	String fieldValue = item.getString();
	            	if(fieldName.equals("venueId")) {
	            		venueId = Integer.valueOf(fieldValue);
	            	}
	            } else {
	                // Process form file field (input type="file").	                               
	                long fileSize = item.getSize();	                
	                String fileName = FilenameUtils.getName(item.getName());
	                System.out.println("File name: " + fileName + ", File size: " + String.valueOf(fileSize));
	                InputStream fileContent = item.getInputStream();
	                byte[] fileData = new byte[(int)fileSize];
	                fileContent.read(fileData);
	                System.out.println("File data array size: " + String.valueOf(fileData.length));
	                uploadedPhotos.add(uploadToImgur(fileData));
	            }
	        }
	        if(venueId != 0) {
	        	VenuesDAO.uploadVenuePhotos(venueId, uploadedPhotos);
	        }
	        returnToEdit(venueId, request, response);
	    } catch (FileUploadException e) {
	        throw new ServletException("Cannot parse multipart request.", e);
	    }
	}
	
	private static void returnToEdit(int venueId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Venue venue = VenuesDAO.getVenueForEdit(venueId);
		request.setAttribute("venue", venue);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_venue.jsp");
		dispatcher.forward(request, response);
	}
	
	private static Map<String, String> uploadToImgur(byte[] fileData) {		
		URL url;
		Map<String, String> newImage = new HashMap<String, String>();
		try {
			url = new URL("https://api.imgur.com/3/image");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String dataImage = Base64.encode(fileData);	    
			String data = URLEncoder.encode("image", "UTF8") + "=" + URLEncoder.encode(dataImage, "UTF-8");	
			
			conn.setDoOutput(true);
		    conn.setDoInput(true);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Authorization", "Client-ID " + Consts.IMGUR_CLIENT_ID);
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Content-Type",
		            "application/x-www-form-urlencoded");

		    conn.connect();
		    StringBuilder stb = new StringBuilder();
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();

		    // Get the response
		    BufferedReader rd = new BufferedReader(
		            new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        stb.append(line).append("\n");
		    }
		    wr.close();
		    rd.close();
		    
		    JsonElement root = new JsonParser().parse(stb.toString());
		    String link = root.getAsJsonObject().get("data").getAsJsonObject().get("link").getAsString();
		    String deleteHash = root.getAsJsonObject().get("data").getAsJsonObject().get("deletehash").getAsString();
		    System.out.println("Image link: " + link);
		    System.out.println("Delete hash: " + deleteHash);
		    newImage.put("imageUrl", link);
		    newImage.put("deleteHash", deleteHash);
		} catch (MalformedURLException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newImage;
	}
			

}
