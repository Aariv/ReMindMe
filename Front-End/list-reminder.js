function goToDetailPage(id) {
	console.log(id);
	window.location.href = "detail.html?id="id;	
}

function listReminders() {
$.ajax({
	url: "http://localhost:3030/v1/reminders"
    }).then(function(data) {
       	var data = data.data;
       	var count = 1;
       	for(var i = 0; i < data.length; i++) {
       		var record = data[i];
       		var row = '<tr>' +
       			'<td>'+ count++ +'</td>' +
       			'<td>'+ record.problem +'</td>' +
       			'<td>'+ record.referenceLink +'</td>' +
       			'</tr>';
       		$("#reminders tbody").append(row);
       	}
    }).done(function (data) {
	    //selectText('Id', data.country);
	    console.log("done function in init");
	    //init();
	}).fail(function (jqXHR, textStatus, errorThrown) {
	    //var defaultOption = 'US'
	    //selectDropdownByText('Id', defaultOption);
	    //alert("Server is not reachable.")
	    console.log(errorThrown);
	});
}
