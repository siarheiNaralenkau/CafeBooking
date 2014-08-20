
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login page</title>
</head>
<body>
	<div id="authError" style="color: red">
	<% 
		Object error = request.getAttribute("errorMsg");
		if(error != null) {
			out.println(error);
		}
	%>
	</div>

	<form method="post" action="./edit_tables_login">
		<label for="userName">Admin name: </label>
		<input type="text" name="userName" id="userName"/>
		<label for="password">Password: </label>
		<input type="password" name="password" id="password"/>
		<input type="submit" value="Login"/>
	</form>
</body>
</html>