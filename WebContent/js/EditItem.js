function disableBidInc(){
	var isChecked=document.getElementById("buyItNow").checked;
	//console.log(isChecked);
	if (isChecked){
		document.getElementById('bidInc').disabled=true;
		document.getElementById('timeLeft').required=false;
	}
	else{
		document.getElementById('bidInc').disabled=false;
		document.getElementById('timeLeft').required=true;
	}	
}

$(document).ready(function(){


	$.validator.addMethod('time', function (value) { 
    return /^[0-9]*:[0-5][0-9]$/.test(value); 
}, 'Please enter a valid time (HH:mm).');
	
	$("#editItemForm").validate({
		rules:{
			startPrice:{
				number: true,
			},
			bidInc:{
				number: true,
			},
			timeLeft:{
				time: true,	
			}
		},
	});
});