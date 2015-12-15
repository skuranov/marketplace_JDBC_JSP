$(document).ready(function(){
	$("#loginForm").validate({
		
		rules:{
			login:{
				required: true,
				minlength: 6,
				maxlength: 16,
			},
			
			pswd:{
				required: true,
				minlength: 6,
				maxlength: 16,
			},
		},
	});
});