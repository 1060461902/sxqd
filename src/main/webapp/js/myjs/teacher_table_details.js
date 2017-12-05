$(document).ready(function(){
	var id = window.location.href;
	var info = new Object();
	info.id = id;
	//ajax
$.getJSON("js/json/teacher_table_details.json", function(data) {
	var obj = data.teacherDetail;
	$('a#stu').attr('href','teacher_table_students.html?id='+obj.id);
	$('h5#nickName').text(obj.nickName);
	$('td#nickName').text(obj.nickName);
	$('span#userName').text(obj.userName);
	$('td#userName').text(obj.userName);
	$('span#sex').text(obj.sex);
	$('span#rank').text(obj.rank);
	$('span#phone').text(obj.phone);
	$('span#email').text(obj.email);
	$('img').attr('src',obj.photo);
	$('input#email').val(obj.email);
	$('input#phone').val(obj.phone);
	$('input#rank').val(obj.rank);
	if(obj.sex=='男'){
		$("button#male").css("background-color","#09C");
	}
	else if(obj.sex=='女'){
		$("button#male").css("background-color","pink");
	}
	// 写入专业
	var namelength = data.majorList.length;
        for ( var i = 0; i < namelength; i++){
        $('select').append('<option>'+data.majorList[i].companyName+'</option>');
       }
});
$("button#male").click(function(){
  $("button#male").css("background-color","#09C");
    $("button#female").css("background-color","#FFF");
});
$("button#female").click(function(){
  $("button#female").css("background-color","pink");
    $("button#male").css("background-color","#FFF");
});

});