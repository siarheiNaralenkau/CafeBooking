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
		caption: "Статистика по зарегистрированным пользователям за период: " + formatCaptionRegistred()
	});
	
	var endDate = new Date();
	var startDate = new Date();
	startDate.setMonth(startDate.getMonth()-1);
	
	$("#dateFromReg").datepicker({dateFormat: "yy-mm-dd"});
	$("#dateToReg").datepicker({dateFormat: "yy-mm-dd"});
	
	$("#dateFromReg").datepicker("setDate", startDate);
	$("#dateToReg").datepicker("setDate", endDate);
	
	$("#dateFromReg").change(dateFilterChanged);
	$("#dateToReg").change(dateFilterChanged);
}
    		
function fetchRegistredData() {	
	var startDate = $("#dateFromReg").val();
	var endDate = $("#dateToReg").val();
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
	return "<a href='./user_bookings.jsp?userId=" + cellValue + "&venueId=" + $("#venueId").text() + "'>Подробнее</a>"; ;                
};

function formatCaptionRegistred() {
	return "<label for='dateFromReg'>С</label><input type='text' id='dateFromReg' style='margin-left: 10px'/><label for='dateToReg' style='margin-left: 10px'>По</label><input type='text' id='dateToReg' style='margin-left: 10px'/>";
};

function dateFilterChanged() {        		
	fetchRegistredData();
};
