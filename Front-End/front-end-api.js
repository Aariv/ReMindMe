var myMap = new Map(); 
myMap.set('local', 'http://localhost:3030/v1/reminders');
myMap.set('server', 'http://ec2-54-91-228-178.compute-1.amazonaws.com:3030/v1/reminders');
myMap.set('localLogin', 'http://localhost:3030/v1/api/authenticate');
myMap.set('serverLogin', 'http://ec2-54-91-228-178.compute-1.amazonaws.com:3030/v1/api/authenticate');
myMap.set('localRegister', 'http://localhost:3030/v1/api/register');
myMap.set('serverRegister', 'http://ec2-54-91-228-178.compute-1.amazonaws.com:3030/v1/api/register');

var server = false;

var instance = (server) ? myMap.get('server') : myMap.get('local');
var authInstance = (server) ? myMap.get('serverLogin') : myMap.get('localLogin');
var signUpInstance = (server) ? myMap.get('serverRegister') : myMap.get('localRegister');
var dummyToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcml2QGdtYWlsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE1ODAyMTc1MDN9.NZtX_Ipw6sUxymBq7KH2PQURE1yAXKN0U9pyHFT-3BHo49mPdUxHFZc-FkOYBeFpfynNO48p0w6MLhMxOfauTw';
var token = localStorage.getItem('token') != null ? localStorage.getItem('token') : dummyToken;
$(document).ready(function() {
		onLoadTodayItems();
		onLoadAllReminders();
		$( "#signIn" ).click(function(e) {
            e.preventDefault();
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
                  contentType: "application/json; charset=utf-8",
                  data: JSON.stringify(loginDto),
                  timeout: 1000,
                  type: 'POST',
                  url: authInstance
            }).done(function(data, textStatus, jqXHR) {
                console.log(data.data);

              localStorage.setItem('token', data.data);
              window.location = 'dashboard.html';
            }).fail(function(jqXHR, textStatus, errorThrown) {
              alert('Booh! Wrong credentials, try again!');
            });
        });

        $('#signUp').click(function(e) {
        	e.preventDefault();
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
		        url: signUpInstance,
		        contentType: "application/json; charset=utf-8",
		        data: JSON.stringify(registerDto)
		        
		    }).done(function (data) {
			    console.log("done in submit function");
			    console.log(data);
				showSuccessToast();
				$("#email").val("");
				$("#password").val("");
				$("#confirmPassword").val("");
				window.location = '../index.html';
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
        });

		$("#addDetail").click(function(e) {
			var id = $("#problemId").val();
            var revised = $("#revised").val();
            var feedback = $("#feedback").val();
            //console.log(name);
            if(revised === '') {
                showAddInfoToast();
                //$("#feedback").val("");
                return;
            } else if(feedback === '') {
                showAddInfoToast();
                //$("#referenceLink").val("");
                return;
            }
            var reminderFeedback = {
                    id: parseInt(id),
                    revised: (revised == 'on'),
                    feedback: feedback
            }
            $.ajax({
                type: "POST",
                headers: {"Authorization": 'Bearer ' + token},
                url: instance + '/revisedReminder',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(reminderFeedback)
                
            }).done(function (data) {
                console.log("done in submit function");
                showSuccessToast();
                window.href.location = "pages/table-datatable.html";
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
		});

        $('#logout').click(function(e) {
            localStorage.setItem('token', '');
        });

		$( "#addReminder" ).click(function(e) {
                e.preventDefault();
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

                $.ajax({
                    type: "POST",
                    url: instance,
                    contentType: "application/json; charset=utf-8",
                    headers: {"Authorization": 'Bearer ' + token},
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
            });
});

function onLoadTodayItems() {

    $.ajax({
        url: instance,
        headers: {"Authorization": 'Bearer ' + token}
    }).then(function(data) {
        var data = data.data;
        var count = 1;
        for(var i = 0; i < data.length; i++) {
            var record = data[i];
            var row = '<tr onclick="gotoDetailPage('+record.problemId+')">' +
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

function onLoadAllReminders() {
		$.ajax({
	        url: instance + "/allConcepts",
	        headers: {"Authorization": 'Bearer ' + token}
        }).then(function(data) {
            var data = data.data;
            var count = 1;
            for(var i = 0; i < data.length; i++) {
                var record = data[i];
                var row = '<tr>' +
                    '<td>'+ count++ +'</td>' +
                    '<td>'+ record.problem +'</td>' +
                    '<td><a target="_blank" href='+record.referenceLink+'>'+record.referenceLink+'</td>' +
                    '<td>'+ record.date +'</td>' +
                    '</tr>';
                $("#allReminders tbody").append(row);
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