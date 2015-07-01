function createUnregistredGrid() {
	$("#jqGridUnregistred").jqGrid({
	    colModel: [	        
	        {
				label: 'Имя посетителя',
	            name: 'name',
	            width: 120                         
	        },
	        {
				label: 'Фамилия посетителя',
	            name: 'surname',
	            width: 150                         
	        },
	        {
	        	label: 'Телефон',
	        	name: 'phone',
	        	width: 120                    	
	        },
	        {
	        	label: 'E-Mail',
	        	name: 'email',
	        	width: 150
	        },
	        {
	        	label: 'Количество броней',
	        	name: 'bookingsCount',
	        	width: 150
	        },
	        {
	        	label: 'Потрачено денег',
	        	name: 'spentMoney',
	        	width: 150
	        },	      
	        {
	        	label: 'Долг (5%)',
	        	name: 'dept',
	        	width: 120	
	        },
	        {                    	
	        	name: 'moreInfo',
	        	label: '',
	        	width: 100,
	        	formatter: moreInfoUnregFormatter
	        }
	    ],
	
	    viewrecords: true, // show the current page, data rang and total records on the toolbar
	    width: 1000,
	    height: 300,
	    rowNum: 30,
		datatype: 'local',
	    pager: "#jqGridUnregistredPager",
		caption: "Статистика по незарегистрированным пользователям"
	});				
};
    		
function fetchUnregistredData() {
	var startDate = $("#dateFrom").val();
	var endDate = $("#dateTo").val();
	var url = "./VenuesStatsUnregJSON?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + $("#venueId").text();        		
	var gridArrayData = [];	 
	
	$.ajax({
		url: url,
		success: function(result) {
			console.log(result);
			for(var i = 0; i < result.length; i++) {
				var userItem = result[i];
				console.log(userItem);
				gridArrayData.push({          	
		            name: userItem.name, 
		            surname: userItem.surname,
		            phone: userItem.phone,
		            email: userItem.email,
		            bookingsCount: userItem.bookingsCount,
		            spentMoney: userItem.spentMoney,
		            dept: userItem.dept,
		            moreInfo: "bookingId=" + userItem.id + "&venueId=" + $("#venueId").text()            
		        });     
			}
			$("#jqGridUnregistred").jqGrid('clearGridData');
			$("#jqGridUnregistred").jqGrid('setGridParam', { data: gridArrayData});
			$("#jqGridUnregistred").trigger('reloadGrid');
		}
	});			
};

function moreInfoUnregFormatter(cellValue, options, rowObject) {
	var dateFrom = $("#dateFrom").val();
	var dateTo = $("#dateTo").val();
	return "<a href='./user_bookings_unreg.jsp?" + cellValue + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "'>Подробнее</a>";                
};