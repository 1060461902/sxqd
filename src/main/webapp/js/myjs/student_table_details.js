$(document).ready(function(){  
	//alert(window.location.href);
    //window.studentid = window.location.href;
    window.studentid = window.location.href.split('=');
	var info = new Object();
	info.id = studentid[1];
	$('a#qzzl').attr("href",'student_table_jobs.html?id='+info.id+'');
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showStudentDetail',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
		var obj =data.student;
		$('h4#nickName').text(obj.nickname);
		$('table#student_details tr:eq(0) td').text(obj.nickname);
		$('span#userName').text(obj.username);
		$('table#student_details tr:eq(1) td').text(obj.username);
		if(obj.sex==false||obj.sex==0||obj.sex=='0'){
		$('span#sex').text('男');
		$("button#male").css("background-color","#09C");
		window.sex =false;	
		}
	    else if(obj.basicInfo.sex==true||obj.basicInfo.sex==1||obj.basicInfo.sex=='1'){
	    $('span#sex').text('女');
	    $("button#male").css("background-color","pink");
	    window.sex =true;
	    }	
		$('span#nation').text(obj.national);
		$('span#birthDate').text(obj.birthdate);
		$('table#student_details tr:eq(5) input').val(obj.birthdate);
		$('span#phone').text(obj.phone);
		$('table#student_details tr:eq(6) input').val(obj.phone);
		$('span#email').text(obj.email);
		$('table#student_details tr:eq(7) input').val(obj.email);
		$('img').attr('src',obj.photo);
		//英语水平
        $('h4#english').text(obj.english);	
		//项目经历
		var obj2 = data.ProjectList;
		var length2 = obj2.length;
		for(var n=0;n<length2;n++)
		{
	   $('li.list-group-item:eq(1)').append('<div><span class="time float-right"></span><br><h4 id="projectName"></h4><h4 id="identity"></h4><h4 id="instruction"></h4></div><br>');
		// $('li.list-group-item span.time:eq(0)').text(obj.pStartTime+'------'+obj.pEndTime);
		$('li.list-group-item:eq(1) div:eq('+n+') h4#projectName').text(obj2[n].Project.projectName);
		$('li.list-group-item:eq(1) div:eq('+n+') h4#identity').text(obj2[n].Project.identity);
		$('li.list-group-item:eq(1) div:eq('+n+') h4#instruction').text(obj2[n].Project.instruction);
		$('li.list-group-item:eq(1) div:eq('+n+') span.time').text(obj2[n].Project.startTime+'------'+obj2[n].Project.endTime);
	    }
		//所获荣誉
		var obj3 = data.HonorList;
		var length3 = obj3.length;
		for(var n=0;n<length3;n++)
		{
	   $('li.list-group-item:eq(2)').append('<div><span class="time float-right"></span><h4 id="honorName"></h4><h4 id="instruction"></h4></div><br>');
		// $('li.list-group-item span.time:eq(0)').text(obj.pStartTime+'------'+obj.pEndTime);
		$('li.list-group-item:eq(2) div:eq('+n+') h4#honorName').text(obj3[n].Honor.honorName);
		$('li.list-group-item:eq(2) div:eq('+n+') h4#instruction').text(obj3[n].Honor.instruction);
		$('li.list-group-item:eq(2) div:eq('+n+') span.time').text(obj3[n].Honor.time);
	    }
		//社团经历
        var obj4 = data.ClubList;
		var length4 = obj4.length;
		for(var n=0;n<length4;n++)
		{
	   $('li.list-group-item:eq(3)').append('<div><span class="time float-right"></span><h4 id="clubName"></h4><h4 id="indentity"></h4><h4 id="instruction"></h4></div><br>');
		// $('li.list-group-item span.time:eq(0)').text(obj.pStartTime+'------'+obj.pEndTime);
		$('li.list-group-item:eq(3) div:eq('+n+') h4#clubName').text(obj4[n].Club.clubName);
		$('li.list-group-item:eq(3) div:eq('+n+') h4#indentity').text(obj4[n].Club.indentity);
		$('li.list-group-item:eq(3) div:eq('+n+') h4#instruction').text(obj4[n].Club.instruction);
		$('li.list-group-item:eq(3) div:eq('+n+') span.time').text(obj4[n].Club.startTime+'------'+obj4[n].Club.endTime);
	    }
		//标签
		var skill =data.skill;
        var skilllength = data.SkillList.length;
        for(var n=0;n<skilllength;n++)
        {
          $('div.skill-content').append('<span class="label label-primary" id="skill">'+skill[n]+'</span>');
        };
     },
       error: function(){
        alert('服务端异常');
        }
    });
	// $.getJSON("js/json/student_table_details.json", function(data) {
	// });
//-------------编辑--------------
$('button#edit').click(function(){
  var studentBasicInfo = new Object();
  studentBasicInfo.id=studentid;
  studentBasicInfo.sex = sex;   
  studentBasicInfo.nation = $('select#nation').val();  
  studentBasicInfo.birthDate= $('table#student_details tr:eq(5) input').val();
  studentBasicInfo.phone= $('table#student_details tr:eq(6) input').val();
  studentBasicInfo.email= $('table#student_details tr:eq(7) input').val();
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/editStudent',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(studentBasicInfo),
       dateType: "json",
       success: function(data){
       alert('操作成功');
       location.reload();
     },
       error: function(){
        alert('服务端异常');
        }
    });
});
//性别
$("button#male").click(function(){
  $("button#male").css("background-color","#09C");
    $("button#female").css("background-color","#FFF");
    sex =false;
});
$("button#female").click(function(){
  $("button#female").css("background-color","pink");
    $("button#male").css("background-color","#FFF");
    sex =true;
});

  
});