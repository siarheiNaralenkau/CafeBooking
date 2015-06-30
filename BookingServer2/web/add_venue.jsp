<%@page import="com.beans.VenuePhoto"%>
<%@page import="com.beans.Venue"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add new venue</title>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/edit_venues.js"></script>
	<link rel="stylesheet" type="text/css" href="css/edit_venues.css">
	
	<style type="text/css">
		.fileform {
			background-color: #FFFFFF;
			border: 1px solid #CCCCCC;
			border-radius: 2px;
			cursor: pointer;
			height: 26px;
			overflow: hidden;
			padding: 2px;
			position: relative;
			text-align: left;
			vertical-align: middle;
			width: 230px;
		}

		.fileform .selectbutton { 
		    background-color: #A2A3A3;
		    border: 1px solid #939494;
		    border-radius: 2px;
		    color: #FFFFFF;
		    float: right;
		    font-size: 16px;
		    height: 20px;
		    line-height: 20px;
		    overflow: hidden;
		    padding: 2px 6px;
		    text-align: center;
		    vertical-align: middle;
		    width: 50px;
		}

		.fileform #upload{
		    position:absolute; 
		    top:0; 
		    left:0; 
		    width:100%; 
		    -moz-opacity: 0; 
		    filter: alpha(opacity=0); 
		    opacity: 0; 
		    font-size: 150px; 
		    height: 30px; 
		    z-index:20;
		}

		.thumb {
			height: 100px;
			width: 100px;
			border: 1px solid #000;
			margin: 10px 5px 0 0;
		}

		.previewDiv {
			margin-top: 10px;
			margin-bottom: 10px;				
			display: inline-block;							
		}
	</style>
	
	<script type="text/javascript">
		function handleFileSelect(evt) {
			var files = evt.target.files;
			var output = [];
			document.getElementById("previewDiv").innerHTML = '';
			for(var i = 0, f; f = files[i]; i++) {
				if(!f.type.match('image.*')) {
					continue;
				}

				var reader = new FileReader();

				reader.onload = (function(theFile) {
					return function(e) {							
						var imgPreview = ['<img class="thumb" src="', e.target.result, '" title="', theFile.name, '"/>'].join('');
						document.getElementById("previewDiv").innerHTML = document.getElementById("previewDiv").innerHTML + imgPreview;
					}
				})(f);

				reader.readAsDataURL(f);
			}
		};				
	</script>
</head>
<body>	
	<div>
	<form method="post" action="./save_new_venue">						
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
							<input type="text" name="name" id="name" value="<%=request.getParameterMap().containsKey("name") ? request.getParameter("name") : ""%>">
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="address">Адрес:</label>
							<textarea name="address" id="address" rows=3 cols=40></textarea>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="tablesAmount">Количество столиков:</label>
							<input type="number" name="tablesAmount" id="tablesAmount" value=""/>
						</div>	
					</td>					
					<td>
						<div class="field_div">
							<label for="avgCheck">Средний чек:</label>
							<input type="text" name="avgCheck" id="avgCheck" value=""/>
						</div>					
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="category">Тип заведения:</label>
							<input type="text" name="category" id="category" value="">
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="city">Город:</label>
							<input type="text" name="city" id="city" value="Гомель"/>
						</div>	
					</td>
					<td>
						<div class="field_div">
							<label for="openTime">Время открытия:</label>
							<input type="text" name="openTime" id="openTime" value="" placeholder="10:00"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="adminUser">Аккаунт администратора:</label>
							<input type="text" name="adminUser" id="adminUser" value="" placeholder="admin"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="phone">Телефон:</label>
							<input type="text" name="phone" id="phone" value=""/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="country">Страна:</label>
							<input type="text" name="country" id="country" value="Беларусь"/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="closeTime">Время закрытия:</label>
							<input type="text" name="closeTime" id="closeTime" value="" placeholder="22:00"/>
						</div>	
					</td>
					<td>
						<div class="field_div">
							<label for="adminPassword">Пароль администратора:</label>
							<input type="text" name="adminPassword" id="adminPassword" value=""/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="cuisine">Тип кухни:</label>
							<input type="text" name="cuisine" id="cuisine" value=""/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="lat">Геогр. широта:</label>
							<input type="text" name="lat" id="lat" value=""/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="hasWiFi">Доступен WiFi:</label>
							<input type="checkbox" name="hasWiFi" id="hasWiFi" onclick="updateCheckBox(this)"/>
						</div>	
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="iconUrl">Эмблема заведения:</label>
							<input type="hidden" name="iconUrl" id="iconUrl" value=""/>
							<img id="venueIcon" width="100px" height="100px" src=""/>
						</div>
					</td>
					<td>
						<div class="field_div">
							<label for="lng">Геогр. долгота:</label>
							<input type="text" name="lng" id="lng" value=""/>
						</div>	
					</td>
					<td>
						<div class="field_div">
							<label for="takeCreditCards">Оплата по кредитной карте:</label>
							<input type="checkbox" name="takeCreditCards" id="takeCreditCards" onclick="updateCheckBox(this)"/>
						</div>	
					</td>
				</tr>
				<tr>
					<td>
						<div class="field_div">
							<label for="rating">Рейтинг:</label>		
							<input type="text" name="rating" id="rating" value=""/>
						</div>				
					</td>
					<td></td>
					<td>
						<div class="field_div">
							<label for="takeOutdoorsSeats">Места на улице:</label>
							<input type="checkbox" name="hasOutdoorsSeats" id="hasOutdoorsSeats" onclick="updateCheckBox(this)"/>
						</div>	
					</td>
				</tr>
			</tbody>
		</table>		
		<input type="submit" value="Сохранить изменения">
	</form>
	</div>		
</body>
</html>