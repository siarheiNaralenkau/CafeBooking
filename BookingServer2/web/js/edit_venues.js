function switchCategory(categoryElem) {
	var checked = categoryElem.checked;
	var categoryName = categoryElem.value;
	var venuesTable = $("#tVenues");
	var rows = venuesTable[0].childNodes[1].childNodes;
	if(checked == true) {
		for(var i = 0; i < rows.length; i++) {
			var vCategory = $(rows[i]).attr("data-category");
			if(vCategory == categoryName) {
				$(rows[i]).show();				
			}
		}
	} else {
		for(var i = 0; i < rows.length; i++) {
			var vCategory = $(rows[i]).attr("data-category");
			if(vCategory == categoryName) {
				$(rows[i]).hide();
			}
		}
	}			
};

function editVenue(el) {
	var venueId = $(el).attr("data-venueid");
	window.location = "edit_venue_open?venueId=" + venueId;
};

function venueStats(el) {
	var venueId = $(el).attr("data-venueid");
	window.location = "venue_stats?venueId=" + venueId;
};

function updateCheckBox(el) {
	var checked = el.checked;
	if(checked) {
		el.value = "true";
	} else {
		el.value = "false";
	}
};

function setVenueIcon(el) {
	var img = el.parentNode.childNodes[1];
	var newIconUrl = $(img).attr("src");
	$("#iconUrl").val(newIconUrl);
	$("#venueIcon").attr("src", newIconUrl);
}