$(document).ready(function () {        	
        	
	$("#bookingsGrid").jqGrid({
        colModel: [
            {
				label: 'Дата',
                name: 'date',
                width: 100                        
            },
            {
				label: 'Время',
                name: 'time',
                width: 100                
            },
            {
				label: 'Сумма ресторана',
                name: 'venue_sum',
                width: 150
            },
            {
            	label: 'Сумма пользователя',
            	name: 'user_sum',
            	width: 150
            },
            {
            	label: 'Начислено баллов',
            	name: 'bonus_scores',
            	width: 150
            },
            {
            	label: 'Пожелания к брони',
            	name: 'notes',
            	width: 250
            }
        ],

        viewrecords: true,
        width: 880,
        height: 500,
        rowNum: 30,
		datatype: 'local',
        pager: "#bookingsGridPager",
		caption: "Бронирования пользователя за период: " + formatCaption()
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
		var url = "./VenueStatsForUser?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + $("#venueId").text() + "&userId=" + $("#userId").text();        		
		var gridArrayData = [];
		$.ajax({
			url: url,
			success: function(result) {	        			
    			for(var i = 0; i < result.length; i++) {
    				var bookingItem = result[i];
                    gridArrayData.push({                                
                    	date: bookingItem.date,
                    	time: bookingItem.time,
                    	venue_sum: bookingItem.venue_sum,
                    	user_sum: bookingItem.user_sum,
                    	bonus_scores: bookingItem.bonus_scores,
                        notes: bookingItem.notes                   
                    });     
    			}
    			$("#bookingsGrid").jqGrid('setGridParam', { data: gridArrayData});
    			$("#bookingsGrid").trigger('reloadGrid');
			}
		});
	};
	
	$("#dateFrom").change(dateFilterChanged);
	$("#dateTo").change(dateFilterChanged);
	
	function moreInfoFormatter(cellValue, options, rowObject) {        		
    	return "<a href='./venue_stats_jq.jsp?venueId=" + cellValue + "'>Подробнее</a>";                
	};
	
	function formatCaption() {
		return "<label for='dateFrom'>С</label><input type='date' id='dateFrom'/><label for='dateTo'>По</label><input type='date' id='dateTo'/>";
	};
	
	function dateFilterChanged() {        		
		fetchGridData();
	};
});