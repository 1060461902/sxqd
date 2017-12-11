$(document).ready(function(){
	window.studentid = window.location.href.split('=');
	var info = new Object();
	info.id = studentid[1];
	$('a#xsjl').attr("href",'student_table_details.html?id='+info.id+'')
	$(".time-div").click(function(){
		location.href='./student_table_clock.html?id='+info.id+'';
	});
});