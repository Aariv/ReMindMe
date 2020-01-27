function onSignIn(e) {
	var username = $("#username").val();
	var password = $("#password").val();
	var rememberMe = $("#item_checkbox").val();
	//console.log(name);
	if(username === '') {
		showAddInfoToast();
		return;
	} else if(password === '') {
		showAddInfoToast();
		return;
	}
	var loginDto = {
	    	username: username,
	    	password: password
	    	//rememberMe: feedback
	}
    $.ajax({
        type: "POST",
		contentType: "application/json; charset=utf-8",
        url: 'http://localhost:3030/v1/api/authenticate',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(loginDto)
        
    }).done(function (data) {
	    console.log("done in submit function");
	    console.log(data);
		showSuccessToast();
		$("#username").val("");
		$("#password").val("");
		$("#rememberMe").val("");
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