<%@page import="java.util.Map"%>
<%@page import="com.beans.VenuePhoto"%>
<%@page import="com.beans.Venue"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Venue booking statisticas</title>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/edit_venues.js"></script>
	<link rel="stylesheet" type="text/css" href="css/edit_venues.css">
</head>
<body>
	<% 
		Venue venue = (Venue)request.getAttribute("venue");
		Map<String, Object> bookingStats = (Map<String, Object>)request.getAttribute("bookingStats");
		List<Map<String, Object>> unregUsersBookings = (List<Map<String, Object>>)bookingStats.get("bookingsByUnregUsers");
		List<Map<String, Object>> regUsersBookings = (List<Map<String, Object>>)bookingStats.get("bookingsByRegUsers");
	%>
	<jsp:include page="logout.jsp"></jsp:include>
	<h4>Статистика заказов по заведению<b>"<%=venue.getName()%>"</b></h4>
	<ul>
		<li>Всего бронирований: <%=bookingStats.get("bookingsCreated") %></li>
		<li>Ожидают утверждения: <%=bookingStats.get("bookingsPending") %></li>
		<li>Утверждено ресторатором: <%=bookingStats.get("bookingsApproved") %></li>
		<li>Отменено ресторатором: <%=bookingStats.get("bookingsRejected") %></li>
		<li>Отменено посетителем: <%=bookingStats.get("bookingsCancelled") %></li>
		<li>Посетитель не пришел: <%=bookingStats.get("bookingsExpired") %></li>
		<li>Бронирование и заказ выполнены: <%=bookingStats.get("bookingsCompleted") %></li>
		<li>---
		<li>Минимальный чек: <%=bookingStats.get("minCheck") %></li>
		<li>Максимальный чек: <%=bookingStats.get("maxCheck") %></li>
		<li>Средний чек: <%=bookingStats.get("avgCheck") %></li>
	</ul>
	<h4>Статистика по зарегистрированным пользователям</h4>
	<table border="1">
		<tr>
			<th>ID пользователя</th>
			<th>Имя пользователя</th>
			<th>Количество посещений</th>
			<th>Потрачено денег</th>
		</tr>
		<% for(Map<String, Object> userBookings : regUsersBookings) { %>
			<tr>
				<td><%=userBookings.get("userId") %>
				<td><%=userBookings.get("userName") %>
				<td><%=userBookings.get("visits") %>
				<td><%=userBookings.get("spent_sum") %>
			</tr>
		<% } %>
	</table>
	<h4>Статистика по незарегистрированным пользователям</h4>
	<table border=1>
		<tr>			
			<th>Контактное имя пользователя</th>
			<th>Количество посещений</th>
			<th>Потрачено денег</th>
		</tr>
		<% for(Map<String, Object> userBookings : unregUsersBookings) { %>
			<tr>				
				<td><%=userBookings.get("userName") %>
				<td><%=userBookings.get("visits") %>
				<td><%=userBookings.get("spent_sum") %>
			</tr>
		<% } %>
	</table>
</body>
</html>