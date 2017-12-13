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
       type: 'get',
       url: '/fieldManagement/admin/showScore',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        var obj = data.score;
    $('#totalScore').text(obj[0].totalScore);
    $('#tWeekly').text(obj[0].tWeekly);
    $('#summary').text(obj[0].summary);
    $('#report').text(obj[0].report);
    $('#Weighting').text(obj[0].Weighting);
    $('#ttotalScore').text(obj[0].ttotalScore);
    $('#cWeekly').text(obj[0].cWeekly);
    $('#checkonWork').text(obj[0].checkonWork);
    $('#ctotalScore').text(obj[0].ctotalScore);
    $('#additionalPoints').text(obj[0].additionalPoints);
     },
       error: function(){
        alert('服务端异常');
        }
    });

});