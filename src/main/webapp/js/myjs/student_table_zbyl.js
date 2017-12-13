$(document).ready(function(){
  var info = new Object();
  info.id=window.location.href;
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showWeeklyDetail',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        $('a#progress').attr('href','student_table_progress.html?id='+studentId+'');
        var obj = data.weeklyList;
        $('b#title').text(obj.title);
        $('span#name').text(obj.name);
        $('span#publishedDate').text(obj.publishedDate);
        $('span#stage').text(obj.startTime+'-'+obj.endTime);
        $('div#content').text(obj.content);
        $('td#readoverTime').text(obj.readoverTime);
        $('td#score').text(obj.score);
        $('td#comment').text(obj.comment);
        $('td#teacherName').text(obj.teacherName);
        $('td#email').text(obj.email);
        $('td#phone').text(obj.phone);
        $('td#cReadoverTime').text(obj.cReadoverTime);
        $('td#cScore').text(obj.cScore);
        $('td#cComment').text(obj.cComment);
        $('td#cName').text(obj.cName);
        $('td#cEmail').text(obj.cEmail);
        $('td#cPhone').text(obj.cPhone);
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
      //   $('td#cReadoverTime').text(data.cReadoverTime);
      //   $('td#cScore').text(data.cScore);
      //   $('td#cComment').text(data.cComment);
      //   $('td#cName').text(data.cName);
      //   $('td#cEmail').text(data.cEmail);
      //   $('td#cPhone').text(data.cPhone);
      // });
//ajax
});