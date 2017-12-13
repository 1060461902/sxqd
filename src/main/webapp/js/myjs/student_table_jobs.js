$(document).ready(function(){
	window.studentid = window.location.href.split('=');
	var info = new Object();
	info.id = studentid[1];
		$.getJSON("js/json/student_table_jobs.json", function(data) {
			var obj = data.roadList;
			var length = obj.length;
			for(var i=0;i<length;i++){
				if (i==0){
			 $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item time-div" id="'+i+'"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" class="img-rounded" width="100px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');
			 var action = data.roadList[i].action;
			 var length2 = action.length;
			 $('div#0').append('<div class="text-center inline" style="margin-left: 60px;"><img src="images/u922.png" style="z-index: 0;position: absolute;top: 135px;left: 175px;width:70%"></div>')
             for(var j = 0;j<length2;j++){
             	$('div#0').append('<div style="width:50px;margin: auto 20px;z-index: 99" class="inline text-center"><div><h5>'+action[j].year+'</h5><span>'+action[j].month+'.'+action[j].day+'</span></div><br><div class="circle-area"><i class="fa fa-star-o fa-fw  fa-lg"></i></div><br><div id="status">'+action[j].adctionName+'</div></div>')
             }
                 }
                 else{
             $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" class="img-rounded" width="100px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');     	
                 }
			}
	});
	$('a#xsjl').attr("href",'student_table_details.html?id='+info.id+'')
	$(".time-div").click(function(){
		location.href='./student_table_clock.html?id='+info.id+'';
	});
});