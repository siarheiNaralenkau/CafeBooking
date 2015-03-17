<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Бронирования пользователя</title>
	
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/ecmascript" src="js/jquery.jqGrid.min.js"></script>	
	<script type="text/ecmascript" src="js/grid.locale-en.js"></script>	
	<script type="text/javascript" src="js/user_bookings_unreg.js"></script>
	
	<!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/jquery-ui.min.css" />
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
</head>
<body>
	<span id="venueId" style="display: none"><%=request.getParameter("venueId") %></span>
	<span id="userName"><%=request.getParameter("userName") %></span>
	<table id="bookingsGrid"></table>
    <div id="bookingsGridPager"></div>
</body>
</html>   