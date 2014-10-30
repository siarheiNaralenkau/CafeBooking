<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login page</title>
</head>
<body>
	<h4>Введите логин и пароль администратора для редактирования заведений:</h4>
	<form method="POST" action="./admin_servlet">
		<div>
			<label for="adminLogin">Логин:</label>
			<input type = "text" name="adminLogin" id="adminLogin">
		</div>
		<div>
			<label for="adminPassword">Пароль:</label>
			<input type = "password" name="adminPassword" id="adminPassword">
		</div>
		<input type="submit" value="Войти как администратор">
	</form>
</body>
</html>