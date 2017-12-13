$(document).ready(function(){
	window.studentid = window.location.href.split('=');
	var info = new Object();
	info.id = studentid[1];
    $('a#xsjl').attr("href",'student_table_details.html?id='+info.id+'');
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showRoad',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
            var obj = data.roadList;
            var length = obj.length;
            for(var i=0;i<length;i++){
             if (i==0){
             $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item time-div" id="'+i+'"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" width="80px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');
             var action = data.roadList[i].action;
             var length2 = action.length;
             var logo;
             $('div#0').append('<div class="text-center inline" style="margin-left: 60px;"><img src="images/u922.png" style="z-index: 0;position: absolute;top: 120px;left: 175px;width:80%"></div>')
             for(var j = 0;j<length2;j++){
                if(action[j].actionName=='收藏岗位')
                    logo = 'fa-star-o';
                else if(action[j].actionName=='投递简历')
                    logo = 'fa-envelope-o';
                else if(action[j].actionName=='简历通过')
                    logo = 'fa-check-square-o';
                else if(action[j].actionName=='成功录用')
                    logo = 'fa-smile-o';
                else if(action[j].actionName=='入岗实习')
                    logo = 'fa-desktop';
                else if(action[j].actionName=='实习结业')
                    logo = 'fa-graduation-cap';
                else if(action[j].actionName=='未被录用')
                    logo = 'fa-meh-o';
                $('div#0').append('<div style="width:50px;margin: auto 20px;z-index: 99" class="inline text-center"><div><h5>'+action[j].year+'</h5><span>'+action[j].month+'.'+action[j].day+'</span></div><br><div class="circle-area"><i class="fa '+logo+' fa-fw  fa-lg"></i></div><br><div class="actionName">'+action[j].actionName+'</div></div>')
             }
                 }
             else{
             $('div#myTabContent').append('<div class="templatemo-content-widget white-bg list-group-item" id="'+i+'"><div class="text-center inline"><img src="'+data.roadList[i].logo+'" width="80px;"><br><br><h5 id="company">'+data.roadList[i].companyName+'</h5><h5 id="recruit">'+data.roadList[i].recruitmentName+'</h5></div></div>');
             var action = data.roadList[i].action;
             var length2 = action.length;
             var logo;
             $('div#'+i+'').append('<div class="text-center inline" style="margin-left: 60px;"><img src="images/u922.png" style="z-index: 0;position: absolute;top: 120px;left: 175px;width:80%"></div>')
             for(var j = 0;j<length2;j++){
                if(action[j].actionName=='收藏岗位')
                    logo = 'fa-star-o';
                else if(action[j].actionName=='投递简历')
                    logo = 'fa-envelope-o';
                else if(action[j].actionName=='简历通过')
                    logo = 'fa-check-square-o';
                else if(action[j].actionName=='成功录用')
                    logo = 'fa-smile-o';
                else if(action[j].actionName=='入岗实习')
                    logo = 'fa-desktop';
                else if(action[j].actionName=='实习结业')
                    logo = 'fa-graduation-cap';
                else if(action[j].actionName=='未被录用')
                    logo = 'fa-meh-o';
                $('div#'+i+'').append('<div style="width:50px;margin: auto 20px;z-index: 99" class="inline text-center"><div><h5>'+action[j].year+'</h5><span>'+action[j].month+'.'+action[j].day+'</span></div><br><div class="circle-area"><i class="fa '+logo+' fa-fw  fa-lg"></i></div><br><div class="actionName">'+action[j].actionName+'</div></div>')
             }  
                 }
            }//for
    $(".time-div").click(function(){
        location.href='./student_table_clock.html?id='+info.id+'';
    }); 
     },
       error: function(){
        alert('服务端异常');
        }
    });    
	// 	$.getJSON("js/json/student_table_jobs.json", function(data) {
           
	// });//getjson
});
//收藏岗位 fa-star-o
//投递简历 fa-envelope-o
//简历通过 fa-check-square-o
//成功录用 fa-smile-o
//入岗实习 fa-desktop
//实习结业 fa-graduation-cap
//未被录用 fa-meh-o