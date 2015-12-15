var my_ajax_req ={ // создаем экземпляр объекта
 
// задаем свойства объекта
updInterval: 10000, // 10 сек. - интервал времени между запросами
url: 'getTable', // скрипт который должен отвечать на Ajax запросы
init: function(){
    var self = my_ajax_req;
    setInterval($.proxy(my_ajax_req.requestData, self), self.updInterval);
},
  
requestData: function(){
    var self = my_ajax_req;
      
    // ajax запрос посредством JQuery
    $.ajax({
        url: self.url,
        type: 'GET',
        dataType: 'json',
        success: function(data){ self.update(data) },
        error: function(data){ self.error(data) },
    });
},
      
// метод принимает ответ на Ajax запрос
update: function(Data){
    var self = my_ajax_req;
    console.log(Data);
    // тут можно дописать логику после получения данных
},
 
// метод для обработки ошибок
error: function(Data){
    var self = my_ajax_req;
    console.log('Failed to get data');
},
}

my_ajax_req.init();


var tabElem=document.createElement("table");
var newRow=tabElem.insertRow(0);
var caption = tabElem.createCaption();
caption.innerHTML = "<b>Items</b>";
var i;
document.body.appendChild(tabElem);

var newCell = newRow.insertCell(0);
newCell.width="200";
newCell.innerHTML="<b>Title</b>";

newCell = newRow.insertCell(1);
newCell.width="200";
newCell.innerHTML="<b>Description</b>";

newCell = newRow.insertCell(2);
newCell.width="200";
newCell.innerHTML="<b>Seller</b>";

newCell = newRow.insertCell(3);
newCell.width="200";
newCell.innerHTML="<b>Start price</b>";

newCell = newRow.insertCell(4);
newCell.width="200";
newCell.innerHTML="<b>Bid inc</b>";

newCell = newRow.insertCell(5);
newCell.width="200";
newCell.innerHTML="<b>Best offer</b>";

newCell = newRow.insertCell(6);
newCell.width="200";
newCell.innerHTML="<b>Bidder</b>";

newCell = newRow.insertCell(7);
newCell.width="200";
newCell.innerHTML="<b>Stop date</b>";

newCell = newRow.insertCell(8);
newCell.width="200";
newCell.innerHTML="<b>Action</b>";



// var request = getHTTPObject();
// if (request) {
	// request.onreadystatechange = doSomething;
	// request.open("POST", "file.ext", true);
	// request.setRequestHeader("Content-Type", "application/
	// x-www-form-urlencoded");
	// request.send("name=Message’s+sender&message=Hello+world");
// }

// var request = getHTTPObject();
// if (request) {
	// request.onreadystatechange = doSomething;
	// request.open("GET", "file.ext", true);
	// request.send(null);
// }


// function displayResponse(request) {
// if (request.readyState == 4) {
// if (request.status == 200 || request.status == 304) {
// //обработка request.responseText или request.responseXML
// }
// }
// }


// //json
// function entry() {
	// var company = 'ООО “Веники трейд”';
	// var name = 'Кошкин Т.Я.';
	// var email = 'koshkin@veniki.com';
	// var phone = '53-10-20';
// }
// var entry = function(){
	// this.company = 'ООО “Веники трейд”';
	// this.name = 'Кошкин Т.Я.';
	// this.email = 'koshkin@veniki.com';
	// this.phone = '53-10-20';
// }
// var entry ={
	// “company”:'ООО “Веники трейд”',
	// “name”:'Кошкин',
	// “email”:'koshkin@veniki.com',
	// “phone”:'53-10-20'}
// {“entry”{
	// “company”:'ООО “Веники трейд”',
	// “name”:'Кошкин',
	// “email”:'koshkin@veniki.com',
	// “phone”:'53-10-20'
// }
// }
// // обработка 

// var data = eval('('+request.responseText+')');

// var company	= data.entry.company;
// var name		= data.entry.name;
// var email		= data.entry.email;
// var phone		= data.entry.phone;



	
/*	window.setInterval((function()
      {                 
        $.get('http://192.168.0.82:4567/ajax/2/2-09.04.2010.js', {size : mass.length}, function()
        {   
          if (is_data != -1)
          {
            var old_l = mass.length;
            var Elem=document.getElementById('data_table');
            for(var j = 0; j < mas2.length; j += 1)
            {
              mass[old_l+j] = mas2[j];
              /* формирую таблицу *
              var newRow=Elem.insertRow(0);
              var newCell = newRow.insertCell(0);
              newCell.className = "td1";
              newCell.innerText= mass[old_l+j][0];
              var newCell = newRow.insertCell(-1);
              newCell.innerText= mass[old_l+j][1];
            }
          }
        }, "script");
      }),5000)*/












