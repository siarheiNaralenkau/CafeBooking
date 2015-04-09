function createRegistredGrid() {
	$("#jqGridRegistred").jqGrid({
	    colModel: [
	        {
				label: 'ID пользователя',
	            name: 'id',
	            width: 80                        
	        },
	        {
				label: 'Имя',
	            name: 'name',
	            width: 120                         
	        },
	        {
				label: 'Фамилия',
	            name: 'surname',
	            width: 120
	        },
	        {
	        	label: 'Телефон',
	        	name: 'phone',
	        	width: 120                    	
	        },
	        {
	        	label: 'Email',
	        	name: 'email',
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
	        	label: 'Начислено баллов',
	        	name: 'bonusScores',
	        	width: 150
	        },
	        {
	        	label: 'Претензий всего',
	        	name: 'claimsAll',
	        	width: 150
	        },
	        {
	        	label: 'Претензий активных',
	        	name: 'claimsActive',
	        	width: 150
	        },                    
	        {                    	
	        	name: 'moreInfo',
	        	width: 100,
	        	formatter: moreInfoFormatter
	        }
	    ],
	
	    viewrecords: true, // show the current page, data rang and total records on the toolbar
	    width: 1200,
	    height: 300,
	    rowNum: 30,
		datatype: 'local',
	    pager: "#jqGridRegistredPager",
		caption: "Статистика по зарегистрированным пользователям"
	});		
}
    		
function fetchRegistredData() {	
	var startDate = $("#dateFrom").val();
	var endDate = $("#dateTo").val();
	var url = "./VenueStatsRegJSON?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + $("#venueId").text();        				
	var gridArrayData = [];    
	
	$.ajax({
		url: url,
		success: function(result) {			
			for(var i = 0; i < result.length; i++) {
				var userItem = result[i];
				gridArrayData.push({          	
					id: userItem.id,
		            name: userItem.name,
		            surname: userItem.surname,
		            phone: userItem.phone,
		            email: userItem.email,
		            bookingsCount: userItem.bookingsCount,
		            spentMoney: userItem.spentMoney,
		            bonusScores: userItem.bonusScores,
		            claimsAll: userItem.claimsAll,
		            claimsActive: userItem.claimsActive,
		            moreInfo: userItem.id            
		        });     
			}
			$("#jqGridRegistred").jqGrid('setGridParam', { data: gridArrayData});
			$("#jqGridRegistred").trigger('reloadGrid');
		}
	});				
};

function moreInfoFormatter(cellValue, options, rowObject) {      
	var dateFrom = $("#dateFrom").val();
	var dateTo = $("#dateTo").val();
	return "<a href='./user_bookings.jsp?userId=" + cellValue + "&venueId=" + $("#venueId").text() + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "'>Подробнее</a>";                
};
