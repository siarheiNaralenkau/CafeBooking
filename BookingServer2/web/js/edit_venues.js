function switchCategory(categoryElem) {
	var checked = categoryElem.checked;
	var categoryName = categoryElem.value;
	var venuesTable = $("#tVenues");
	var rows = venuesTable[0].childNodes[1].childNodes;
	if (checked == true) {
		for (var i = 0; i < rows.length; i++) {
			var vCategory = $(rows[i]).attr("data-category");
			if (vCategory == categoryName) {
				$(rows[i]).show();
			}
		}
	} else {
		for (var i = 0; i < rows.length; i++) {
			var vCategory = $(rows[i]).attr("data-category");
			if (vCategory == categoryName) {
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
	var curDate = new Date();
	var startDate = new Date();
	var curDay = startDate.getDate(); 
	startDate.setDate(curDay - 30);
	var dateFrom = formatDate(startDate);
	var dateTo = formatDate(curDate);
	window.location = "venue_stats_jq.jsp?venueId=" + venueId + "&dateFrom="
			+ dateFrom + "&dateTo=" + dateTo + "&backUrl=venues_by_category.jsp";
};

function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
};

function updateCheckBox(el) {
	var checked = el.checked;
	if (checked) {
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