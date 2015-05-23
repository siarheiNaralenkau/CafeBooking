
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.beans.Venue"%>
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Администрирование заведения</title>
</head>
<%
	Venue venue = (Venue)request.getAttribute("venue");
%>
<body>
	
	<div class="params" style="display: none">
		<span id="venueId"><%=venue.getId()%></span>
		<span id="venueName"><%=venue.getName()%></span>
	</div>
	<h3>Администрирование заведения <%=venue.getName()%></h3>
	
</body>
</html>