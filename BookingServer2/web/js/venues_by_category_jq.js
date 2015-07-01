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
        rowNum: 200,
		datatype: 'local',
        pager: "#jqGridPager",
		caption: "Статистика по заведениям за период: " + formatCaption()
    });
	
	// Set default start and end dates.
	var paramFrom = $("#dateFromParam").text();
	var paramTo = $("#dateToParam").text();
	var startDate, endDate;
	
	if(!paramFrom) {
		startDate = new Date();
		startDate.setMonth(startDate.getMonth()-1);
	} else {
		startDate = new Date(paramFrom);
	}
	
	if(!paramTo) {
		endDate = new Date();
	} else {
		endDate = new Date(paramTo);
	}				        
	
	$("#dateFrom").datepicker({dateFormat: "yy-mm-dd"});
	$("#dateTo").datepicker({dateFormat: "yy-mm-dd"});
	
	$("#dateFrom").datepicker("setDate", startDate);
	$("#dateTo").datepicker("setDate", endDate);
	
	$("#dateFrom").change(dateFilterChanged);
	$("#dateTo").change(dateFilterChanged);
	
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
	
	function moreInfoFormatter(cellValue, options, rowObject) {
		var dateFrom = $("#dateFrom").val();
		var dateTo = $("#dateTo").val();
    	return "<a href='./venue_stats_jq.jsp?venueId=" + cellValue + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "'>Подробнее</a>";              
	};
	
	function formatCaption() {
		return "<label for='dateFrom'>С</label><input type='text' id='dateFrom' style='margin-left: 10px'/><label for='dateTo' style='margin-left: 10px'>По</label><input type='text' id='dateTo' style='margin-left: 10px'/>";
	};
	
	function dateFilterChanged() {        		
		fetchGridData();
	};
});