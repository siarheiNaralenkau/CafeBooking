<%@page import="com.beans.VenuePhoto"%>
<%@page import="com.beans.Venue"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit venue</title>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/edit_venues.js"></script>
	<link rel="stylesheet" type="text/css" href="css/edit_venues.css">
</head>
<body>
	<%
		Venue venue = (Venue)request.getAttribute("venue");
	%>
	<div>
	<form method="post" action="./save_venue_changes">	
			
		<input type="hidden" name="venueId" value="<%=venue.getId()%>"/>
		<table>
			<thead>
				<tr>
					<th>О Заведении</th>
					<th>Место расположения</th>
					<th>Дополнительная информация</th>
					<th>Администрирование</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="field_div">
							<label for="name">Название заведения:</label>
							<input type="text" name="name" id="name" value="<%=venue.getName()%>">
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="address">Адрес:</label>
							<textarea name="address" id="address" rows=3 cols=40><%=venue.getAddress()%></textarea>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="tablesAmount">Количество столиков:</label>
							<input type="number" name="tablesAmount" id="tablesAmount" value="<%=venue.getTablesAmount()%>"/>
						</div>	
					</td>					
					<td>
						<div class="field_div">
							<label for="avgCheck">Средний чек:</label>
							<input type="text" name="avgCheck" id="avgCheck" value="<%=venue.getAvgPayment()%>"/>
						</div>					
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="category">Тип заведения:</label>
							<input type="text" name="category" id="category" value="<%=venue.getCategory()%>">
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="city">Город:</label>
							<input type="text" name="city" id="city" value="<%=venue.getCity()%>" disabled="disabled"/>
						</div>	
					</td>
					<td>
						<div class="field_div">
							<label for="openTime">Время открытия:</label>
							<input type="text" name="openTime" id="openTime" value="<%=venue.getOpenTime()%>"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="adminUser">Аккаунт администратора:</label>
							<input type="text" name="adminUser" id="adminUser" value="<%=venue.getAdminUser()%>"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="phone">Телефон:</label>
							<input type="text" name="phone" id="phone" value="<%=venue.getPhone()%>"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="country">Страна:</label>
							<input type="text" name="country" id="country" value="<%=venue.getCountry()%>" disabled="disabled"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="closeTime">Время закрытия:</label>
							<input type="text" name="closeTime" id="closeTime" value="<%=venue.getCloseTime()%>"/>
						</div>	
					</td>
					<td>
						<div class="field_div">
							<label for="adminPassword">Пароль администратора:</label>
							<input type="text" name="adminPassword" id="adminPassword" value="<%=venue.getAdminPassword()%>"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="cuisine">Тип кухни:</label>
							<input type="text" name="cuisine" id="cuisine" value="<%=venue.getCuisine()%>"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="lat">Геогр. широта:</label>
							<input type="text" name="lat" id="lat" value="<%=venue.getLat()%>" disabled="disabled"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="hasWiFi">Доступен WiFi:</label>
							<input type="checkbox" name="hasWiFi" id="hasWiFi" onclick="updateCheckBox(this)" value="<%=venue.isHasWifi()%>" <%=venue.isHasWifi() ? "checked='checked'" : ""%>/>
						</div>	
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="iconUrl">Эмблема заведения:</label>
							<input type="url" name="iconUrl" id="iconUrl" value="<%=venue.getIconUrl()%>"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="lng">Геогр. долгота:</label>
							<input type="text" name="lng" id="lng" value="<%=venue.getLng()%>" disabled="disabled"/>
						</div>	
					</td>
					<td>
						<div class="field_div">
							<label for="takeCreditCards">Оплата по кредитной карте:</label>
							<input type="checkbox" name="takeCreditCards" id="takeCreditCards" onclick="updateCheckBox(this)" value="<%=venue.isTakeCreditCards()%>" <%=venue.isTakeCreditCards() ? "checked='checked'" : ""%>/>
						</div>	
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="rating">Рейтинг:</label>		
							<input type="text" name="rating" id="rating" value="<%=venue.getRating()%>" disabled="disabled"/>
						</div>				
					</td>
					<td></td>
					<td>
						<div class="field_div">
							<label for="takeOutdoorsSeats">Места на улице:</label>
							<input type="checkbox" name="hasOutdoorsSeats" id="hasOutdoorsSeats" onclick="updateCheckBox(this)" value="<%=venue.isHasOutdoorsSeats()%>" <%=venue.isHasOutdoorsSeats() ? "checked='checked'" : ""%>/>
						</div>	
					</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="Сохранить">
	</form>
	</div>
	<div id="venuePhotos">
		<table>
			<tbody>
			<tr>
				<%
					List<VenuePhoto> photos = venue.getPhotos();
					if(photos != null && photos.size() > 0) {
					for(VenuePhoto photo: photos) {					
				%>
				<td width="100px" height="100px">
					<img width="100px" height="100px" src="<%=photo.getUrl()%>" data-id="<%=photo.getId()%>">
					<a href="./delete_venue_photo?photoId=<%=photo.getId()%>&venueId=<%=venue.getId()%>">Удалить</a>
				</td>
				<% } } %>
			</tr>
			</tbody>
		</table>
	</div>
</body>
</html>