<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Бронирования пользователя</title>
	
	<jsp:include page="jq-grid.jsp"></jsp:include>		
	<script type="text/javascript" src="js/user_bookings_unreg.js"></script>		
</head>
<body>
	<span id="venueId" style="display: none"><%=request.getParameter("venueId") %></span>
	<span id="bookingId" style="display: none"><%=request.getParameter("bookingId") %></span>
	<div style='margin-left: auto; margin-right: auto; width: 880px'>
		<table id="bookingsGrid"></table>
    	<div id="bookingsGridPager"></div>
    </div>
</body>
</html>   