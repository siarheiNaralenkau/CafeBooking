<%@page import="com.beans.Venue"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit venue</title>
	<link rel="stylesheet" type="text/css" href="css/edit_venues.css">
</head>
<body>
	<%
		Venue venue = (Venue)request.getAttribute("venue");
	%>
	<form method="post" action="./update_venue">	
			
		<input type="hidden" name="venueId" value="<%=venue.getId()%>"/>
		
		<div>
			<label for="rating">Рейтинг:</label>		
			<input type="text" name="rating" id="rating" value="<%=venue.getRating()%>" disabled="disabled"/></div>
		<div>
			<label for="name">Название заведения:</label>
			<input type="text" name="name" id="name" value="<%=venue.getName()%>">
		</div>
		<div>
			<label for="phone">Телефон:</label>
			<input type="text" name="phone" id="phone" value="<%=venue.getPhone()%>"/>
		</div>
		<div>
			<label for="address">Адрес:</label>
			<textarea name="address" id="address" rows=6 cols=50><%=venue.getAddress()%></textarea>
		</div>
		<div>
			<label for="city">Город:</label>
			<input type="text" name="city" id="city" value="<%=venue.getCity()%>" disabled="disabled"/>
		</div>
		<div>
			<label for="country">Страна:</label>
			<input type="text" name="country" id="country" value="<%=venue.getCountry()%>" disabled="disabled"/>
		</div>
		<div>
			<label for="lat">Геогр. широта:</label>
			<input type="text" name="lat" id="lat" value="<%=venue.getLat()%>" disabled="disabled"/>
		</div>
		<div>
			<label for="lng">Геогр. долгота:</label>
			<input type="text" name="lng" id="lng" value="<%=venue.getLng()%>" disabled="disabled"/>
		</div>
		<div>
			<label for="hasFreeSeats">Есть свободные места:</label>
			<input type="checkbox" name="hasFreeSeats" id="hasFreeSeats" value="<%=venue.isHasFreeSeats()%>" <%=venue.isHasFreeSeats() ? "checked='checked'" : ""%>/>
		</div>
		<div>
			<label for="adminUser">Аккаунт администратора:</label>
			<input type="text" name="adminUser" id="adminUser" value="<%=venue.getAdminUser()%>"/>
		</div>
		<div>
			<label for="tablesAmount">Количество столиков:</label>
			<input type="number" name="tablesAmount" id="tablesAmount" value="<%=venue.getTablesAmount()%>"/>
		</div>
		<div>
			<label for="iconUrl">Эмблема заведения:</label>
			<input type="url" name="iconUrl" id="iconUrl" value="<%=venue.getIconUrl()%>"/>
		</div>
		<div>
			<label for="openTime">Время открытия:</label>
			<input type="text" name="openTime" id="openTime" value="<%=venue.getOpenTime()%>"/>
		</div>
		<div>
			<label for="closeTime">Время закрытия:</label>
			<input type="text" name="closeTime" id="closeTime" value="<%=venue.getCloseTime()%>"/>
		</div>
		<div>
			<label for="cuisine">Тип кухни:</label>
			<input type="text" name="cuisine" id="cuisine" value="<%=venue.getCuisine()%>"/>
		</div>
		<div>
			<label for="hasWiFi">Доступен WiFi:</label>
			<input type="checkbox" name="hasWiFi" id="hasWiFi" value="<%=venue.isHasWifi()%>" <%=venue.isHasWifi() ? "checked='checked'" : ""%>/>
		</div>
		<div>
			<label for="takeCreditCards">Оплата по кредитной карте:</label>
			<input type="checkbox" name="takeCreditCards" id="takeCreditCards" value="<%=venue.isTakeCreditCards()%>" <%=venue.isTakeCreditCards() ? "checked='checked'" : ""%>/>
		</div>
		<div>
			<label for="takeOutdoorsSeats">Места на улице:</label>
			<input type="checkbox" name="takeOutdoorsSeats" id="takeOutdoorsSeats" value="<%=venue.isHasOutdoorsSeats()%>" <%=venue.isHasOutdoorsSeats() ? "checked='checked'" : ""%>/>
		</div>
		
		<input type="submit" value="Сохранить">
	</form>
</body>
</html>