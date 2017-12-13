$(document).ready(function(){
  var info = new Object();
  info.id=window.location.href;
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showSummaryDetail',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        var obj = data.summary;
        $('b#title').text(obj.title);
        $('span#name').text(obj.name);
        $('span#publishedDate').text(obj.publishedDate);
        $('span#stage').text(obj.startTime+'-'+obj.endTime);
        $('div#content').text(objcontent);
        $('td#readoverTime').text(obj.readoverTime);
        $('td#score').text(obj.score);
        $('td#comment').text(obj.comment);
        $('td#teacherName').text(obj.teacherName);
        $('td#email').text(obj.email);
        $('td#phone').text(obj.phone);
     },
       error: function(){
        alert('服务端异常');
        }
    });
      // $.getJSON("js/json/student_zbyl.json", function(data) {
      // 	$('b#title').text(data.title);
      //   $('span#name').text(data.name);
      //   $('span#publishedDate').text(data.publishedDate);
      //   $('span#stage').text(data.startTime+'-'+data.endTime);
      //   $('div#content').text(data.content);
      //   $('td#readoverTime').text(data.readoverTime);
      //   $('td#score').text(data.score);
      //   $('td#comment').text(data.comment);
      //   $('td#teacherName').text(data.teacherName);
      //   $('td#email').text(data.email);
      //   $('td#phone').text(data.phone);
      // });
//ajax
});