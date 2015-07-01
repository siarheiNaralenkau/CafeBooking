<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Бронирования пользователя</title>
		
	<jsp:include page="jq-grid.jsp"></jsp:include>		
	<script type="text/javascript" src="js/user_bookings.js"></script>
		
</head>
<body>
	<div style="display: none">
		<span id="venueId"><%=request.getParameter("venueId") %></span>
		<span id="userId"><%=request.getParameter("userId") %></span>
		<span id="initialDateFrom"><%=request.getParameter("dateFrom") %></span>
		<span id="initialDateTo"><%=request.getParameter("dateTo") %></span>
	</div>
	<a href="./venue_stats_jq.jsp" title="К списку заведений" id="backBtn"><img src="images/back.png" style="width: 100px; height: 50px"/></a>
	<div style='margin-left: auto; margin-right: auto; width: 880px'>
		<table id="bookingsGrid"></table>
	    <div id="bookingsGridPager"></div>
    </div>
</body>
</html>   