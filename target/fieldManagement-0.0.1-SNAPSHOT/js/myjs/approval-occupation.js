$(document).ready(function(){ 
	var id = window.location.href;
	  // $.ajax({
   //     type: 'get',
   //     url: '/fieldManagement/admin/showRecruitmentDetails',
   //     data: JSON.stringify(id),
   //     async: true,
   //     contentType: "application/json",
   //     dateType: "json",
   //     success: function(data){

   //   },
   //     error: function(){
   //      alert('服务端异常');
   //      }
   //  });
	 $.getJSON("js/json/approval_occupation.json", function(data) {
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
	 });
	 //---------------通过/不通过------------------------
$("button").click(function(){
	var flag = this.value;
	if(flag=="1"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;//要传输的数据
      location.href='./approval-2.html';
    //<---------------------------------------表格重新导入
    }
    else if(flag=="0"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;
      location.href='./approval-2.html';
    }
   });
});