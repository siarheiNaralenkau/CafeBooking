function createUnregistredGrid() {
	$("#jqGridUnregistred").jqGrid({
	    colModel: [	        
	        {
				label: 'Имя пользователя',
	            name: 'name',
	            width: 120                         
	        },	        
	        {
	        	label: 'Телефон',
	        	name: 'phone',
	        	width: 120                    	
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
	        	name: 'moreInfo',
	        	label: '',
	        	width: 100,
	        	formatter: moreInfoUnregFormatter
	        }
	    ],
	
	    viewrecords: true, // show the current page, data rang and total records on the toolbar
	    width: 850,
	    height: 300,
	    rowNum: 30,
		datatype: 'local',
	    pager: "#jqGridUnregistredPager",
		caption: "Статистика по незарегистрированным пользователям за период: " + formatCaptionUnregistred()
	});
	
	var endDate = new Date();
	var startDate = new Date();
	startDate.setMonth(startDate.getMonth()-1);
	document.getElementById('dateFromUnreg').valueAsDate = startDate;
	document.getElementById('dateToUnreg').valueAsDate = endDate;
	
	fetchUnregistredData()
}
    		
function fetchUnregistredData() {
	var startDate = $("#dateFromUnreg").val();
	var endDate = $("#dateToUnreg").val();
	var url = "./VenuesStatsUnregJSON?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + $("#venueId").text();        		
	var gridArrayData = [];	 
	
	$.ajax({
		url: url,
		success: function(result) {
			console.log(result);
			for(var i = 0; i < result.length; i++) {
				var userItem = result[i];
				var userNameEncoded = encodeURIComponent(userItem.name);
		        gridArrayData.push({          	
		            name: userItem.name,            
		            phone: userItem.phone,            
		            bookingsCount: userItem.bookingsCount,
		            spentMoney: userItem.spentMoney,
		            moreInfo: "userName=" + userNameEncoded + "&venueId=" + $("#venueId").text()            
		        });     
			}
			$("#jqGridUnregistred").jqGrid('setGridParam', { data: gridArrayData});
			$("#jqGridUnregistred").trigger('reloadGrid');
		}
	});		
		
};

function moreInfoUnregFormatter(cellValue, options, rowObject) {        		
	return "<a href='./UserBookingsUnregServlet?" + cellValue + "'>Подробнее</a>";                
};

function formatCaptionUnregistred() {
	return "<label for='dateFromUnreg'>С</label><input type='date' id='dateFromUnreg' style='margin-left: 10px'/><label for='dateToUnreg' style='margin-left: 10px'>По</label><input type='date' id='dateToUnreg' style='margin-left: 10px'/>";
};

function dateFilterUnregChanged() {        		
	fetchUnregistredData();
};