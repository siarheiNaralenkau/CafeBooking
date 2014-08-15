<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update booking example</title>
</head>
<body>
	<form action="./update_booking" method="post">
		<input type="hidden" name="bookingId" value="10"/>
		<div>
			<label for="places">Places</label>
			<input type="text" name="places" id="places" value="3"/>
		</div>
		<div>
			<label for="bookingTime">Booking time</label>
			<input type="text" name="bookingTime" id="bookingTime" value="15-08-2014 19:00" placeholder="dd-MM-YYYY HH:mm"/>
		</div> 
		<div>
			<input type="submit" value="Update"/> 
		</div>
	</form>	
</body>
</html>