$(document).ready(function(){
  var info = new Object();
  info.id=window.location.href;
      $.getJSON("js/json/student_zbyl.json", function(data) {
      	$('b#title').text(data.title);
        $('span#name').text(data.name);
        $('span#publishedDate').text(data.publishedDate);
        $('span#stage').text(data.startTime+'-'+data.endTime);
        $('div#content').text(data.content);
        $('td#readoverTime').text(data.readoverTime);
        $('td#score').text(data.score);
        $('td#comment').text(data.comment);
        $('td#teacherName').text(data.teacherName);
        $('td#email').text(data.email);
        $('td#phone').text(data.phone);
        $('td#cReadoverTime').text(data.cReadoverTime);
        $('td#cScore').text(data.cScore);
        $('td#cComment').text(data.cComment);
        $('td#cName').text(data.cName);
        $('td#cEmail').text(data.cEmail);
        $('td#cPhone').text(data.cPhone);
      });
//ajax
});