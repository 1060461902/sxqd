$(document).ready(function(){   
  var info =new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];
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
       data: info,
       dateType: "json",
       success: function(data){
     $('#teacherWeight').text(data.teacherWeight*100);
     $('#companyWeight').text(data.companyWeight*100);
     $('#cAttendance').text(data.cAttendance*100);
     $('#cWeekReport').text(data.cWeekReport*100);
     $('#tWeekReport').text(data.tWeekReport*100);
     $('#tSummary').text(data.tSummary*100);
     $('#tFinalReport').text(data.tFinalReport*100);
        var obj = data.score;
    $('#totalScore').text(obj.totalScore);
    $('#tWeekly').text(obj.tWeekly);
    $('#summary').text(obj.summary);
    $('#report').text(obj.report);
    $('#Weighting').text(obj.Weighting);
    $('#ttotalScore').text(obj.ttotalScore);
    $('#cWeekly').text(obj.cWeekly);
    $('#checkonWork').text(obj.checkonWork);
    $('#ctotalScore').text(obj.ctotalScore);
    $('#additionalPoints').text(obj.additionalPoints);
     },
       error: function(){
        alert('服务端异常');
        }
    });

});