$(document).ready(function(){

	$("#registrationForm").validate({
		rules:{
			fullName:{
				minlength: 6,
				maxlength: 30,
			},
			
			billingAdress:{
				minlength: 10,
				maxlength: 50,
			},
			
			password:{
				minlength: 6,
				maxlength: 20, 
			},
			
			passwordConfirm:{
				minlength: 6,
				maxlength: 20,
				equalTo: "#pswd"
			},
		},
	});
	
	$('#loginForm').submit(function(){
		$.post('http://example.com/upload', function() {//тут должен быть адрес сервера, куда отсылаем данные
		  window.location = 'http://google.com';//тут должен быть адрес странички, куда редиректим
		});
		return false;
	});

});