
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Администрирование заведения(Вход)</title>
	<style>
		.mainDiv {
			display: block; 
			width: 600px; 
			margin: auto; 
			position: absolute; 
			top: 0; 
			left: 0; 
			bottom: 0; 
			right: 0; 
			height: 200px; 
			border: 2px solid black; 
			background-color: #336699	
		}
		.inputDiv {
			padding-left: 10px;
			margin-top: 10px; 
			margin-bottom: 10px;
			display: inline-block;
			width: 90%			
		}
		.inputLabel {
			float: left;
			width: 250px;
		}
		.inputField {
			float: right;
			width: 250px;		
		}
		.loginBtn {
			display: block;
			width: 25%;
			margin: 0 auto;
		}
	</style>
</head>
<body>
	<div id="authError" style="color: red">
	<% 
		Object error = request.getAttribute("errorMsg");
		if(error != null) {
			out.println(error);
		}
		List<Map<String, Object>> venues = (List<Map<String, Object>>)request.getAttribute("venuesList"); 
	%>
	</div>
	
	<div class="mainDiv">
		<form method="post" action="./venue_admin_login">
			<div class="inputDiv">
				<label class="inputLabel" for="venueId">Выберите заведение: </label>
				<select class="inputField" name="venueId" id="venueId">
					<%
						for(Map<String, Object> venue : venues) {
					%>
						<option value='<%=venue.get("id")%>'><%=venue.get("name")%></option>
					<% } %>
									
				</select>
			</div>
			<div class="inputDiv">
				<label class="inputLabel" for="userName">Логин администратора заведения: </label>
				<input class="inputField" type="text" name="vAdminLogin" id="userName"/>
			</div>
			<div class="inputDiv">
				<label class="inputLabel" for="password">Пароль: </label>
				<input class="inputField" type="password" name="vAdminPassword" id="password"/>
			</div>
			<div class="inputDiv">
				<input class="loginBtn" type="submit" value="Войти"/>
			</div>
		</form>
	</div>
</body>
</html>