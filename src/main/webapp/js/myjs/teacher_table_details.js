$(document).ready(function(){
	window.teacherid = window.location.href;
	var info = new Object();
	info.id = teacherid;
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/teacherDetail',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
	var obj = data.teacherDetail;
	$('a#stu').attr('href','teacher_table_students.html?id='+obj.id);
	$('h4#nickName').text(obj.nickName);
	$('td#nickName').text(obj.nickName);
	$('span#userName').text(obj.userName);
	$('td#userName').text(obj.userName);
  if(obj.sex==true||obj.sex==1||obj.sex=='1'){
    $('span#sex').text('男');
    $("button#male").css("background-color","#09C");
    window.sex =true; 
    }
      else if(obj.sex==false||obj.sex==0||obj.sex=='0'){
      $('span#sex').text('女');
      $("button#male").css("background-color","pink");
      window.sex =false;
      } 
	$('span#rank').text(obj.rank);
	$('span#phone').text(obj.phone);
	$('span#email').text(obj.email);
	$('img').attr('src',obj.photo);
	$('input#email').val(obj.email);
	$('input#phone').val(obj.phone);
	$('input#rank').val(obj.rank);
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
// $.getJSON("js/json/teacher_table_details.json", function(data) {

// });//json\
$('button#edit').click(function(){
  var teacherBasicInfo = new Object();
  teacherBasicInfo.id=teacherid;
  teacherBasicInfo.sex = sex; 
  teacherBasicInfo.major = $('input#major').val();
  teacherBasicInfo.rank= $('input#rank').val();  
  teacherBasicInfo.phone= $('input#phone').val();  
  teacherBasicInfo.email= $('input#email').val();  
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/updateTeacher',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(teacherBasicInfo),
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
$("button#male").click(function(){
  $("button#male").css("background-color","#09C");
    $("button#female").css("background-color","#FFF");
    sex=true;
});
$("button#female").click(function(){
  $("button#female").css("background-color","pink");
    $("button#male").css("background-color","#FFF");
    sex=false;
});

});