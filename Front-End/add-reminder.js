
function onSubmit() {
	var name = $("#name").val();
	var referenceLink = $("#referenceLink").val();
	var feedback = $("#feedback").val();
	console.log(name);
	var problem = {
	    	name: name,
	    	number: referenceLink,
	    	feedback: feedback
	}
	    $.ajax({
	        type: "POST",
		contentType: "application/json; charset=utf-8",
	        url: 'http://localhost:3030/v1/reminders',
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(problem)
	        
	    }).done(function (data) {
		    console.log("done in submit function");
		showSuccessToast();
		$("#name").val("");
		$("#referenceLink").val("");
		$("#feedback").val("");
	    }).fail(function (jqXHR, textStatus, errorThrown) {
		   console.log("error in submit function");
			showSuccessToast();
	    });
}
