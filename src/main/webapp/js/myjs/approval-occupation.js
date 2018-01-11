$(document).ready(function(){ 
	  var info =new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];

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
	 // });
	 //---------------通过/不通过------------------------
$("button#pass-button").click(function(){
	var flag = this.value;
	if(flag==1){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=1;//要传输的数据
      info.meg = "通过";
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/comfirmInternship',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         location.href='./approval-2.html';
     },
       error: function(){
        alert('服务端异常');
        }
    });
    }
    else {
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=0;
      info.meg = $('textarea').val();
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/comfirmInternship',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        location.href='./approval-2.html';
     },
       error: function(){
        alert('服务端异常');
        }
    });
    }
   });
});