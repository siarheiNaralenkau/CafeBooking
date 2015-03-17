<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Статистика заказов по заведению</title>		
		
	<jsp:include page="jq-grid.jsp"></jsp:include>
	
	<script type="text/javascript" src="js/venue_stats_jq.js"></script>
	<script type="text/javascript" src="js/bookingsForRegistred.js"></script>
	<script type="text/javascript" src="js/bookingsForUnregistred.js"></script>	   
</head>

<body>
	<span id="venueId" style="display: none"><%=request.getParameter("venueId") %></span>
	<h4>Статистика заказов по заведению <b id="venueName"></b> за период:</h4>
	<label for='dateFrom'>С</label>
	<input type='date' id='dateFrom'/>
	<label for='dateTo'>По</label>
	<input type='date' id='dateTo'/>
	<ul>
		<li>
			<span>Подтверждено ресторатором:</span>
			<span id="bookingsApproved"></span>
		</li>
		<li>
			<span>Успешных посещений:</span>
			<span id="bookingsCompleted"></span>
		</li>
		<li>
			<span>Отменено ресторатором:</span>
			<span id="bookingsRejected"></span>
		</li>
		<li>
			<span>Отменено посетителем:</span>
			<span id="bookingsCancelled"></span>
		</li>
			<span>Пользователь не пришел:</span>
			<span id="bookingsExpired"></span>			
		<li>
			<span>Ожидают утверждения:</span>
			<span id="bookingsPending"></span>
		</li>
		<li>
			<span>Всего бронирований:</span>
			<span id="bookingsAll"></span>
		</li>
		<li>
			<span>Процент непришедших:</span>
			<span id='percentUnvisited'></span>
		</li>
		<li>
		-------------------------
		</li>
		<li>
			<span>Минимальный чек:</span>
			<span id="minCheck"></span>
		</li>
		<li>
			<span>Максимальный чек:</span>
			<span id="maxCheck"></span>
		</li>
		<li>
			<span>Максимальный чек:</span>
			<span id="avgCheck"></span>
		</li>		
	</ul>
	
	<div style="margin-top: 10px">
		<table id="jqGridRegistred"></table>
	    <div id="jqGridRegistredPager"></div>
    </div>
    <div style="margin-top: 20px">
	    <table id="jqGridUnregistred"></table>
	    <div id="jqGridUnregistredPager"></div>
    </div>
</body>
</html>