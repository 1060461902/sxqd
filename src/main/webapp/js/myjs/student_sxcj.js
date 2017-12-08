$(document).ready(function(){    
  var info =new Object();
  info.id = window.location.href;
  $.getJSON("js/json/student_sxcj.json", function(data) {
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
	// "totalScore": 59977,
	// "tWeekly": 38567,
	// "summary": 17014,
	// "report": 93760,
	// "Weighting": 36225,
	// "ttotalScore": 59960,
	// "cWeekly": 28119,
	// "checkonWork": 83753,
	// "ctotalScore": 23073,
	// "additionalPoints": 111
  });
});