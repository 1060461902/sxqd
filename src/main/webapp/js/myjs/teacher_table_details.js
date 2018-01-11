$(document).ready(function(){
    var info = new Object();
    var zhf= window.location.href.split('=');
    window.id=zhf[1];
    info.id = zhf[1];
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/teacherDetail',
       async: true,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
	var obj = data.teacherDetail;
	$('a#stu').attr('href','teacher_table_students.html?id='+obj.id);
	$('#nickName').text(obj.nickName);
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
      else 
       $('span#sex').text('（ 性别信息为空 ）');
     if (obj.major ==null)
        $('span#major').text('（ 专业信息为空 ）');
      else
        $('span#major').text(obj.major);
     if (obj.rank==null)
        $('span#rank').text('（ 职称信息为空 ）');     
      else 
	$('span#rank').text(obj.rank);
	$('span#phone').text(obj.phone);
	$('span#email').text(obj.email);
	$('img').attr('src',obj.photo);
  $('input#major').val(obj.major);
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
  teacherBasicInfo.id=id;
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
    var sex=true;
});
$("button#female").click(function(){
  $("button#female").css("background-color","pink");
    $("button#male").css("background-color","#FFF");
    var sex=false;
});

});