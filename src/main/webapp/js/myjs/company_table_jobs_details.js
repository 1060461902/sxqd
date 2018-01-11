$(document).ready(function(){ 
	  var info =new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];
    $('a#zpgw').attr('href','company_table_jobs.html?id='+zhf[1]+'');
	  $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showRecruitmentDetails',
       data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        window.comid =  data.recruitmentDetails.id;
        var obj = data.recruitmentDetails;
        $("span#post").text(obj.post);
        $("span#address").text(obj.address);
        $("span#totalnumber").text(obj.totalnumber);
        $("span#postTime").text(obj.postTime);
        $("span#companyName").text(obj.companyName);
        $("span#type").text(obj.type);
        $("span#size").text(obj.size);
        $("span#stage").text(obj.stage);
        $("p#postInfo").text(obj.postInfo);
        $("span#post").text(obj.post);
        $("img#company_logo").attr("src",obj.logo);
     },
       error: function(){
        alert('服务端异常');
        }
    });
	 // $.getJSON("js/json/approval_occupation.json", function(data) {
	 // 	window.comid =  data.recruitmentDetails.id;
  //       var obj = data.recruitmentDetails;
  //       $("span#post").text(obj.post);
  //       $("span#address").text(obj.address);
  //       $("span#totalnumber").text(obj.totalnumber);
  //       $("span#postTime").text(obj.postTime);
  //       $("span#companyName").text(obj.companyName);
  //       $("span#type").text(obj.type);
  //       $("span#size").text(obj.size);
  //       $("span#stage").text(obj.stage);
  //       $("p#postInfo").text(obj.postInfo);
  //       $("span#post").text(obj.post);
  //       $("img#company_logo").attr("src",obj.logo);
	 // });
});