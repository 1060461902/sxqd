$(document).ready(function(){ 
	var id = window.location.href;
  //alert(id);
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
	 $.getJSON("js/json/approval-news.json", function(data) {
	 	window.comid =  data.dynamicNewsDetails.id;
        var obj = data.dynamicNewsDetails;
        $("td#title").text(obj.title);
        $("td img").attr("src",obj.imageUrl);
        $("td#detail").text(obj.detail);
        $("td#nickName").text(obj.nickName);
        $("td#phone").text(obj.phone);
        $("td#email").text(obj.email);
	 });
	 //---------------通过/不通过------------------------
$("button#pass-button").click(function(){
	var flag = this.value;
	if(flag=="1"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;//要传输的数据
      location.href='./approval-3.html';
    //<---------------------------------------表格重新导入
    }
    else if(flag=="0"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;
      info.msg = $('textarea').val();
      location.href='./approval-3.html';
    }
   });
});