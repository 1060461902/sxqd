$(document).ready(function(){  
	//alert(window.location.href);
    window.studentid = window.location.href;
	var info = new Object();
	info.id = studentid;
	//ajax
	$.getJSON("js/json/student_table_details.json", function(data) {
		var obj =data.studentResume;
		$('h4#nickName').text(obj.basicInfo.nickName);
		$('table#student_details tr:eq(0) td').text(obj.basicInfo.nickName);
		$('span#userName').text(obj.basicInfo.userName);
		$('table#student_details tr:eq(1) td').text(obj.basicInfo.userName);
		if(obj.basicInfo.sex==false||obj.basicInfo.sex==0||obj.basicInfo.sex=='0'){
		$('span#sex').text('男');
		$("button#male").css("background-color","#09C");
		window.sex =false;	
		}
	    else if(obj.basicInfo.sex==true||obj.basicInfo.sex==1||obj.basicInfo.sex=='1'){
	    $('span#sex').text('女');
	    $("button#male").css("background-color","pink");
	    window.sex =true;
	    }	
		$('span#nation').text(obj.basicInfo.nation);
		$('span#birthDate').text(obj.basicInfo.birthDate);
		$('table#student_details tr:eq(5) input').val(obj.basicInfo.birthDate);
		$('span#phone').text(obj.basicInfo.phone);
		$('table#student_details tr:eq(6) input').val(obj.basicInfo.phone);
		$('span#email').text(obj.basicInfo.email);
		$('table#student_details tr:eq(7) input').val(obj.basicInfo.email);
		$('img').attr('src',obj.basicInfo.imageUrl);
		//项目经历
		$('li.list-group-item span.time:eq(0)').text(obj.pStartTime+'------'+obj.pEndTime);
		$('h4#projectName').text(obj.projectName);
		$('h4#pRole').text(obj.pRole);
		$('h4#pInstruction').text(obj.pInstruction);
		//所获荣誉
		$('li.list-group-item span.time:eq(1)').text(obj.time);
	    $('h4#honorName').text(obj.honorName);
		$('h4#hInstruction').text(obj.hInstruction);
		//社团经历
		$('li.list-group-item span.time:eq(2)').text(obj.cStartTime+'------'+obj.cEndTime);
		$('h4#clubName').text(obj.clubName);
		$('h4#cRole').text(obj.cRole);
		$('h4#cInstruction').text(obj.cInstruction);
		//标签
		var skill =obj.skill;
        var skilllength = obj.skill.length;
        for(var n=0;n<skilllength;n++)
        {
          $('div.skill-content').append('<span class="label label-primary" id="skill">'+skill[n].skill+'</span>');
        };
        //英语水平
        $('h4#english').text(obj.english);	
	});
//-------------编辑--------------
$('button#edit').click(function(){
  var studentBasicInfo = new Object();
  studentBasicInfo.id=studentid;
  studentBasicInfo.sex = sex;   
  studentBasicInfo.nation = $('select#nation').val();  
  studentBasicInfo.birthDate= $('table#student_details tr:eq(5) input').val();
  studentBasicInfo.phone= $('table#student_details tr:eq(6) input').val();
  studentBasicInfo.email= $('table#student_details tr:eq(7) input').val();
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