<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Venues List</title>
	<jsp:include page="jq-grid.jsp"></jsp:include>
</head>
<body>
	<div style='margin-left: auto; margin-right: auto; width: 920px'>
		<table id="jqGrid"></table>
    	<div id="jqGridPager"></div>    
    </div>
    
    <script type="text/javascript">                 
        $(document).ready(function () {        	
        	
        	$("#jqGrid").jqGrid({
                colModel: [
                    {
						label: 'Заведение',
                        name: 'name',
                        width: 150                        
                    },
                    {
						label: 'Успешных посещений',
                        name: 'successVisits',
                        width: 170, 
                        formatter: 'number'
                    },
                    {
						label: 'Процент непришедших',
                        name: 'percentUnvisited',
                        width: 170
                    },
                    {
                    	label: 'Сумма по чекам',
                    	name: 'checkSum',
                    	width: 150,
                    	formatter: 'number'
                    },
                    {
                    	label: 'Долг заведения',
                    	name: 'venueDebt',
                    	width: 150,
                    	formatter: 'number'
                    },
                    {
                    	label: '',
                    	name: 'moreInfo',
                    	width: 100,
                    	formatter: moreInfoFormatter
                    }
                ],

                viewrecords: true, // show the current page, data rang and total records on the toolbar
                width: 920,
                height: 500,
                rowNum: 30,
				datatype: 'local',
                pager: "#jqGridPager",
				caption: "Статистика по заведениям за период: " + formatCaption()
            });
        	
        	// Set default start and end dates.
        	var endDate = new Date();
        	var startDate = new Date();
        	startDate.setMonth(startDate.getMonth()-1);
        	document.getElementById('dateFrom').valueAsDate = startDate;
        	document.getElementById('dateTo').valueAsDate = endDate;
        	
        	fetchGridData();
        	
        	function fetchGridData() {
        		var startDate = $("#dateFrom").val();
        		var endDate = $("#dateTo").val();
        		var url = "./VenuesStatsJSON?startDate=" + startDate + "&endDate=" + endDate;        		
        		var gridArrayData = [];
        		$.ajax({
       				url: url,
       				success: function(result) {	        			
	        			for(var i = 0; i < result.length; i++) {
	        				var venueItem = result[i];
                            gridArrayData.push({                                
                                name: venueItem.name,
                                successVisits: venueItem.successVisits,
                                percentUnvisited: venueItem.percentUnvisited,
                                checkSum: venueItem.checkSum,
                                venueDebt: venueItem.venueDebt,
                                moreInfo: venueItem.id
                            });     
	        			}
	        			$("#jqGrid").jqGrid('setGridParam', { data: gridArrayData});
	        			$("#jqGrid").trigger('reloadGrid');
       				}
        		});
        	};
        	
        	$("#dateFrom").change(dateFilterChanged);
        	$("#dateTo").change(dateFilterChanged);
        	
        	function moreInfoFormatter(cellValue, options, rowObject) {        		
            	return "<a href='./venue_stats_jq.jsp?venueId=" + cellValue + "'>Подробнее</a>";                
        	};
        	
        	function formatCaption() {
        		return "<label for='dateFrom'>С</label><input type='date' id='dateFrom' style='margin-left: 10px'/><label for='dateTo' style='margin-left: 10px'>По</label><input type='date' id='dateTo' style='margin-left: 10px'/>";
        	};
        	
        	function dateFilterChanged() {        		
        		fetchGridData();
        	};
        });
    </script>
</body>
</html>