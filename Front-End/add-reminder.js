
function onSubmit(e) {
	var name = $("#name").val();
	var referenceLink = $("#referenceLink").val();
	var feedback = $("#feedback").val();
	//console.log(name);
	if(name === '') {
		showAddInfoToast();
		$("#name").val("");
		return;
	} else if(referenceLink === '') {
		showAddInfoToast();
		$("#referenceLink").val("");
		return;
	}
	var problem = {
	    	name: name,
	    	referenceLink: referenceLink,
	    	feedback: feedback
	}
		var dummyToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcml2QGdtYWlsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE1ODAyMTc1MDN9.NZtX_Ipw6sUxymBq7KH2PQURE1yAXKN0U9pyHFT-3BHo49mPdUxHFZc-FkOYBeFpfynNO48p0w6MLhMxOfauTw';
        var token = localStorage.getItem('token') != null ? localStorage.getItem('token') : dummyToken;
	    $.ajax({
	        type: "POST",
	        url: 'http://localhost:3030/v1/reminders',
	        contentType: "application/json; charset=utf-8",
	        headers: {"Authorization": 'Bearer ' + token}
	        data: JSON.stringify(problem)
	        
	    }).done(function (data) {
		    console.log("done in submit function");
		showSuccessToast();
		$("#name").val("");
		$("#referenceLink").val("");
		$("#feedback").val("");
	    }).fail(function (jqXHR, textStatus, errorThrown) {
		   console.log("error in submit function");
		   var status = jqXHR.status;
		   if(status === 500) {
			console.log(status);
			showInteralDangerToast();
		   } else {
			showDangerToast();
		   }
		   console.log(status);
	    });
}
