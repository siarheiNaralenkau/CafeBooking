<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Set"%>
<%@page import="com.beans.Venue"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Venues List</title>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/edit_venues.js"></script>
	<link rel="stylesheet" type="text/css" href="css/edit_venues.css">
	
	
</head>
<body>
	<jsp:include page="logout.jsp"></jsp:include>
	<a href="add_venue.jsp" style="pointer-events: none">Добавить новое заведение</a>
	<div id="parent" style="width: 650px">
		<table><tr>
			<td>
				<div id="dCategories" style="float: left; width: 300px; height: 600px;">
					<ul>
					<%
						Map<String, List<Venue>> venues = (Map<String, List<Venue>>)request.getAttribute("venues");
						Set<String> categories = venues.keySet();
						for(String category: categories) {
							out.print("<li><input type=\"checkbox\" checked onclick=\"switchCategory(this)\" value=\"" + category + "\">" + category + "</li>");
						}
					%>
					</ul>
				</div>
			</td>
			<td>
				<div id="dVenues" style="height: 600px; overflow-y: auto; width: 700px">
					<table border="1" id="tVenues">
					<% 
						for(String category: categories) {
							List<Venue> cVenues = venues.get(category);
							for(Venue venue: cVenues) {
								out.print("<tr data-venueid=" + venue.getId() + " data-category=\"" + category + "\">");
								out.print("<td>" + venue.getName() + "</td>");
								out.print("<td><button onClick=\"editVenue(this)\" data-venueid=" + venue.getId() + ">Редактировать</button></td>");
								out.print("<td><button onClick=\"venueStats(this)\" data-venueid=" + venue.getId() + ">Статистика заказов</button></td>");
								out.print("</tr>");
							}
						}
					%>
					</table>
				</div>
			</td>
		</tr></table>
	</div>
</body>
</html>