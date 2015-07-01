<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Venues List</title>
	<jsp:include page="jq-grid.jsp"></jsp:include>
	<script type="text/javascript" src=js/venues_by_category_jq.js></script>
</head>
<body>
	<div style="display: none">		
		<span id="dateFromParam" style="display: none"><%=request.getParameter("dateFrom") %></span>
		<span id="dateToParam" style="display: none"><%=request.getParameter("dateTo") %></span>
	</div>

	<div style='margin-left: auto; margin-right: auto; width: 920px'>
		<table id="jqGrid"></table>
    	<div id="jqGridPager"></div>    
    </div>        
</body>
</html>