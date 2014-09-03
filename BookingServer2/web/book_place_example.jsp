<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Booking venues example</title>
</head>
<body>
	<form name="bookVenue" method="post" action="./book_place">
		<div>
			<label for="venueId">Заведение</label>
			<select name="venueId" id="venueId">
				<option value="1">Cinema Cafe</option>
				<option value="2">ФаСоль</option>
				<option value="3">Итальянская кухня</option>
			</select>
		</div>
		<div>
			<label for="visitorName">Контактное имя</label>
			<input type="text" id="visitorName" name="visitorName"/>
		</div>
		<div>
			<label for="visitorPhone">Телефон</label>
			<input type="text" id="visitorPhone" name="visitorPhone"/>
		</div>
		<div>
			<label for="visitorPhone">Время бронирования</label>
			<input type="text" id="bookingTime" name="bookingTime" placeholder="dd-MM-YYYY HH:mm"/>
		</div>
		<div>
			<label for="visitorPhone">Кол-во мест</label>
			<input type="text" id="places" name="places"/>
		</div>
		<div>
			<label for="notes">Замечания</label>
			<textarea rows="5" cols="60" id="notes" name="notes"></textarea>
		</div>
		<div>
			<label for="tableNumber">№ столика</label>
			<input type="text" id="tableNumber" name="tableNumber"/>
		</div>
		<div>
			<input type="submit" value="Забронировать"/> 
		</div>
	</form>
</body>
</html>