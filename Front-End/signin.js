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
        data: JSON.stringify(loginDto),
        dataType: 'json',
        success: function(results, textStatus) {
		    debugger;
		    console.log("success : " + results);
		},
		error: function(xhr, status, error)
		{
		    console.log("error : " + xhr.responseText);                   
		}
        
    }).done(function (data) {
	    console.log("done in submit function");
	    console.log(data);
		showSuccessToast();
		localStorage.setItem('token', data.data);
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