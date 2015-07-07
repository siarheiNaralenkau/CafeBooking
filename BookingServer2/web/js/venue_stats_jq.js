$(document).ready(function() {	
	
	$.datepicker.setDefaults(
	  $.extend(	    
	    $.datepicker.regional['ru'],
	    {'dateFormat':'dd-mm-yy'}
	  )
	);
	
	$("#dateFrom").datepicker({dateFormat: "yy-mm-dd"});
	$("#dateTo").datepicker({dateFormat: "yy-mm-dd"});
	
	var startDate = $("#initialDateFrom").text();
	var endDate = $("#initialDateTo").text();	
	
	$("#dateFrom").datepicker("setDate", startDate);
	$("#dateTo").datepicker("setDate", endDate);
	
	$("#dateFrom").change(dateFilterChanged);
	$("#dateTo").change(dateFilterChanged);
	
	fetchVenueData();
		
	createRegistredGrid();
	fetchRegistredData();
	
	// Get data for unregistred users.
	createUnregistredGrid();
	fetchUnregistredData();
	
	function fetchVenueData() {
		var startDate = $("#dateFrom").val();
		var endDate = $("#dateTo").val();
		var venueId = $("#venueId").text()
		
		var url = "./venue_stats?startDate=" + startDate + "&endDate=" + endDate + "&venueId=" + venueId;
		$.ajax({
			url: url,
			success: function(result) {				 				
				$("#bookingsApproved").text(result.bookingStats.bookingsApproved);
				$("#bookingsCompleted").text(result.bookingStats.bookingsCompleted);
				$("#bookingsRejected").text(result.bookingStats.bookingsRejected);
				$("#bookingsCancelled").text(result.bookingStats.bookingsCancelled);
				$("#bookingsPending").text(result.bookingStats.bookingsPending);
				$("#bookingsExpired").text(result.bookingStats.bookingsExpired);
				$("#bookingsAll").text(result.bookingStats.bookingsCreated);
				$("#percentUnvisited").text(result.bookingStats.percentUnvisited)
				$("#maxCheck").text(result.bookingStats.maxCheck);
				$("#minCheck").text(result.bookingStats.minCheck);
				$("#avgCheck").text(result.bookingStats.avgCheck);
				$("#sumSpent").text(result.bookingStats.sumSpent);
				$("#dept5").text(result.bookingStats.dept5);
				$("#venueName").text(result.venue.name); 								
			}
		});
		
		formatBackLink();
	};		
	
	function dateFilterChanged() {
		fetchVenueData();
		fetchRegistredData();
		fetchUnregistredData();
					
		formatBackLink();
	};
	
	// Update the back link with selected dates.
	function formatBackLink() {
		var singleVenueAdmin = $("#singleVenueAdmin").text();
		if(singleVenueAdmin === "null") {
			$("#backBtn").attr("href", "./venues_by_category_jq.jsp?dateFrom=" +  $("#dateFrom").val() + "&dateTo=" + $("#dateTo").val());
		}
	};
});