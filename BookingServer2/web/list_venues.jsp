<%@page import="com.beans.Venue"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Venues List</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>Name</th>
				<th>Phone</th>
				<th>Address</th>
				<th>City</th>
				<th>Country</th>
				<th>Latitude</th>
				<th>Longitude</th>
				<th>Distance</th>
				<th>Has free places</th>
			<tr>			
		</thead>
		<tbody>
			<%
				List<Venue> venues = (List<Venue>)request.getAttribute("venues");
				for(Venue v: venues) {
					out.print("<tr>");
					out.print("<td>" + v.getName() + "</td>");
					out.print("<td>" + v.getPhone() + "</td>");
					out.print("<td>" + v.getAddress() + "</td>");
					out.print("<td>" + v.getCity() + "</td>");
					out.print("<td>" + v.getCountry() + "</td>");
					out.print("<td>" + v.getLat() + "</td>");
					out.print("<td>" + v.getLng() + "</td>");
					out.print("<td>" + new Double(v.getDistance()).intValue() + "</td>");
					out.print("<td>" + v.isHasFreeSeats() + "</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
	<%
	%>
</body>
</html>