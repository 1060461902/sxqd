$(document).ready(function(){
	window.studentid = window.location.href.split('=');
	var info = new Object();
	info.id = studentid[1];
    $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showRoad',
       async: false,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
            $('span#name').text(data.name);
			var obj = data.roadList;
			var length = obj.length;
            if(length==0||length==null)
            {
            $('div#myTabContent').append('<div><span style = "font-size:18px;position:relative;left:3%;top:25px;color:#c2c2c2"><i class="fa fa-circle-o fa-fw"></i>&nbsp;该学生暂无求职信息</span></div>');
            }
			for(var i=0;i<length;i++){
				//if (i==0){
			 $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item time-div" id="'+i+'" style="display: flex;align-items:flex-start;"><div class="text-center inline" style="width:250px"><img src="'+data.roadList[i].logo+'" class="img-rounded" width="100px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');
			 var action = data.roadList[i].action;
			 var length2 = action.length;
             for(var j = 0;j<length2;j++){
             	var logo ;
            	if(action[j].actionname== '收藏岗位')
             	{
                   logo = 'fa-star-o';
             	}
             	else if(action[j].actionname=='投递简历')
             	{
             		logo = 'fa-envelope-o';
             	}
             	else if(action[j].actionname=='简历通过')
             	{
             		logo = 'fa-check-square-o';
             	}
             	else if(action[j].actionname=='成功录用')
             	{
             		logo = 'fa-smile-o';
             	}
             	else if(action[j].actionname=='入岗实习')
             	{
             		logo = 'fa-desktop';
             	}
             	else if(action[j].actionname=='实习结业')
             	{
             		logo = 'fa-graduation-cap';
             	}
             	else if(action[j].actionname=='未被录用')
             	{
             		logo = 'fa-frown-o';
             	}
             	else
             	{
             		logo = 'fa-question';
             	}
             	$('div#'+i+'').append('<div style="width:40px;margin: auto 20px;z-index: 99" class="inline text-center"><div><h5>'+action[j].year+'</h5><span>'+action[j].month+'.'+action[j].day+'</span></div><br><div class="circle-area"><i class="fa '+logo+' fa-fw  fa-lg"></i></div><br><div id="status">'+action[j].actionname+'</div></div>')
             }
                //W }
             //     else{
             // $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" class="img-rounded" width="100px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');     	
             //     }
			}
     },
       error: function(){
        alert('服务端异常');
        }
    });
	// 	$.getJSON("js/json/student_table_jobs.json", function(data) {
	// 		var obj = data.roadList;
	// 		var length = obj.length;
	// 		for(var i=0;i<length;i++){
	// 			if (i==0){
	// 		 $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item time-div" id="'+i+'"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" class="img-rounded" width="100px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');
	// 		 var action = data.roadList[i].action;
	// 		 var length2 = action.length;
	// 		 $('div#0').append('<div class="text-center inline" style="margin-left: 60px;"><img src="images/u922.png" style="z-index: 0;position: absolute;top: 135px;left: 175px;width:70%"></div>')
 //             for(var j = 0;j<length2;j++){
 //             	$('div#0').append('<div style="width:50px;margin: auto 20px;z-index: 99" class="inline text-center"><div><h5>'+action[j].year+'</h5><span>'+action[j].month+'.'+action[j].day+'</span></div><br><div class="circle-area"><i class="fa fa-star-o fa-fw  fa-lg"></i></div><br><div id="status">'+action[j].adctionName+'</div></div>')
 //             }
 //                 }
 //                 else{
 //             $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" class="img-rounded" width="100px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');     	
 //                 }
	// 		}
	// });
	$('a#xsjl').attr("href",'student_table_details.html?id='+info.id+'')
    $("div#0").css('cursor','pointer');
	$("div#0").click(function(){
		location.href='./student_table_clock.html?id='+info.id+'';
	});
});