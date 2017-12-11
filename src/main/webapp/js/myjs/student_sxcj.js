$(document).ready(function(){    
  var info =new Object();
  info.id = window.location.href;
  // $.getJSON("js/json/student_sxcj.json", function(data) {
  // 	$('#totalScore').text(data.totalScore);
  // 	$('#tWeekly').text(data.tWeekly);
  // 	$('#summary').text(data.summary);
  // 	$('#report').text(data.report);
  // 	$('#Weighting').text(data.Weighting);
  //   $('#ttotalScore').text(data.ttotalScore);
  //   $('#cWeekly').text(data.cWeekly);
  //   $('#checkonWork').text(data.checkonWork);
  //   $('#ctotalScore').text(data.ctotalScore);
  //   $('#additionalPoints').text(data.additionalPoints);
  // });
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
    $('#totalScore').text(data.totalScore);
    $('#tWeekly').text(data.tWeekly);
    $('#summary').text(data.summary);
    $('#report').text(data.report);
    $('#Weighting').text(data.Weighting);
    $('#ttotalScore').text(data.ttotalScore);
    $('#cWeekly').text(data.cWeekly);
    $('#checkonWork').text(data.checkonWork);
    $('#ctotalScore').text(data.ctotalScore);
    $('#additionalPoints').text(data.additionalPoints);
     },
       error: function(){
        alert('服务端异常');
        }
    });

});