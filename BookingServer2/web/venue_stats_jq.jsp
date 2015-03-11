<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Статистика заказов по заведению</title>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/edit_venues.js"></script>
	<script type="text/ecmascript" src="js/jquery.jqGrid.min.js"></script>	
	<script type="text/ecmascript" src="js/grid.locale-en.js"></script>
	<script type="text/javascript" src="js/bookingsForRegistred.js"></script>
	<script type="text/javascript" src="js/bookingsForUnregistred.js"></script>
	<link rel="stylesheet" type="text/css" href="css/edit_venues.css">
	<!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/jquery-ui.min.css" />
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
    
    <script type="text/javascript">
    	$(document).ready(function() {
    		// Set default start and end dates.
        	var endDate = new Date();
        	var startDate = new Date();
        	startDate.setMonth(startDate.getMonth()-1);
        	document.getElementById('dateFrom').valueAsDate = startDate;
        	document.getElementById('dateTo').valueAsDate = endDate;        	        	
    		
    		var startDate = $("#dateFrom").val();
    		var endDate = $("#dateTo").val();
    		var venueId = $("#venueId").text()
    		
    		var url = "./venue_stats?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + venueId;
    		$.ajax({
    			url: url,
    			success: function(result) {
    				console.log(result);    				
    				$("#bookingsApproved").text(result.bookingStats.bookingsApproved);
    				$("#bookingsCompleted").text(result.bookingStats.bookingsCompleted);
    				$("#bookingsRejected").text(result.bookingStats.bookingsRejected);
    				$("#bookingsCancelled").text(result.bookingStats.bookingsCancelled);
    				$("#bookingsPending").text(result.bookingStats.bookingsPending);
    				$("#bookingsExpired").text(result.bookingStats.bookingsExpired);
    				$("#bookingsAll").text(result.bookingStats.bookingsCreated);
    				$("#percentUnvisited").text(result.bookingStats.percentUnvisited)
    				$("#maxCheck").text(result.bookingStats.maxCheck);
    				$("#minCheck").text(result.bookingStats.minCheck);
    				$("#avgCheck").text(result.bookingStats.avgCheck);
    				$("#venueName").text(result.venue.name); 
    				
    				createRegistredGrid();
    				fetchRegistredData(result.bookingsRegistred);
    				
    				// Get data for unregistred users.
    	    		createUnregistredGrid();
    			}
    		});    		    		
    	});
    </script>
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
	
	<table id="jqGridRegistred"></table>
    <div id="jqGridRegistredPager"></div>
    
    <table id="jqGridUnregistred"></table>
    <div id="jqGridUnregistredPager"></div>
</body>
</html>