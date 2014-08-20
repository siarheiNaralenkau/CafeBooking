<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="com.beans.Venue"%>
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Adding tables</title>
</head>
<body>
	<form>
		<select id="venue">
			<%
				List<Venue> venues = (List<Venue>)request.getAttribute("venues");
				for(Venue v : venues) {
					out.println("<option value=" + v.getUniqueId() + ">" + v.getName() + "</option>");
				}
			%>
		</select>
		<input type="submit" value="Сохранить"/>
	</form>
</body>
</html>