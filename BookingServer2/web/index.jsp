<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="./venues_list?lat=52.405348&lng=30.920542&responseType=json">Get Venues JSON</a>
	<a href="./venues_list?lat=52.405348&lng=30.920542&responseType=jsp">Get Venues JSP</a>
	<a href="book_place_example.jsp">Book venue</a>
	<a href="./admin_servlet?mode=editVenues">Редактировать заведения</a>
	<a href="./venue_admin">Администрирование заведения</a>
	<a href="./venues_by_category_jq.jsp">Статистика заказов</a>
</body>
</html>