$(document).ready(function() {	
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
				$("#venueName").text(result.venue.name); 								
			}
		});
	};		
	
	function dateFilterChanged() {
		fetchVenueData();
		fetchRegistredData();
		fetchUnregistredData();
	};
});