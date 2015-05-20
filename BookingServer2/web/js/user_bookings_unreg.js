$(document).ready(function () {        	
        	
	$("#bookingsGrid").jqGrid({
        colModel: [
            {
            	label: 'Booking ID',
            	name: 'bookingId',
            	hidden: true
            },
            {
				label: 'Дата создания',
                name: 'date_created',
                width: 200                        
            },
            {
				label: 'Дата бронирования',
                name: 'date_booking',
                width: 200                
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
            	label: 'Пожелания к брони',
            	name: 'notes',
            	width: 250
            }
        ],

        viewrecords: true,
        width: 1000,
        height: 500,
        rowNum: 30,
		datatype: 'local',
        pager: "#bookingsGridPager",
		caption: formatCaption()
    });
	
	// Set default start and end dates.
	var startDate = $("#initialDateFrom").text();
	var endDate = $("#initialDateTo").text();
	
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
		var url = "./VenueStatsForUnregUser?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + $("#venueId").text() + "&bookingId=" + $("#bookingId").text();        		
		var gridArrayData = [];
		$.ajax({
			url: url,
			success: function(result) {	  				
				$("#userName").text(result[0].userName);				
    			for(var i = 1; i < result.length; i++) {
    				var bookingItem = result[i];
                    gridArrayData.push({   
                    	bookingId: bookingItem.id,
                    	date_created: bookingItem.date_created,
                    	date_booking: bookingItem.date_booking,
                    	venue_sum: bookingItem.venue_sum,
                    	user_sum: bookingItem.user_sum,
                    	notes: bookingItem.notes                   
                    });     
    			}
    			$("#bookingsGrid").jqGrid('clearGridData');
    			$("#bookingsGrid").jqGrid('setGridParam', { data: gridArrayData});
    			$("#bookingsGrid").trigger('reloadGrid');
			}
		});
	};		
	
	function formatCaption() {
		var caption = "Бронирования пользователя \"" + "<span id='userName'></span>" + "\" за период: ";
		caption += "<label for='dateFrom'>С</label><input type='text' id='dateFrom' style='margin-left: 10px'/><label for='dateTo' style='margin-left: 10px'>По</label><input type='text' id='dateTo' style='margin-left: 10px'/>";
		return caption;
	};
	
	function dateFilterChanged() {        		
		fetchGridData();
	};
		
});
