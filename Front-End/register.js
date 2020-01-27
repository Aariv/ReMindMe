function onRegister() {
	var email = $("#email").val();
	var password = $("#password").val();
	var confirmPassword = $("#confirmPassword").val();
	var agreement = $("#item_checkbox").val();
	//console.log(name);
	if(email === '') {
		showAddInfoToast();
		return;
	} else if(password === '') {
		showAddInfoToast();
		return;
	}
	var registerDto = {
	    	email: email,
	    	password: password,
	    	confirmPassword: confirmPassword
	}
    $.ajax({
        type: "POST",
		contentType: "application/json; charset=utf-8",
        url: 'http://localhost:3030/v1/api/register',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(registerDto)
        
    }).done(function (data) {
	    console.log("done in submit function");
	    console.log(data);
		showSuccessToast();
		$("#email").val("");
		$("#password").val("");
		$("#confirmPassword").val("");
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