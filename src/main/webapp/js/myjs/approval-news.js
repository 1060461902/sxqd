$(document).ready(function(){
  var info =new Object();
	info.id = window.location.href;
  //alert(id);
	  $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showDynamicNewsDetails',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        window.comid =  data.dynamicNewsDetails.id;
        var obj = data.dynamicNewsDetails;
        $("td#title").text(obj.title);
        $("td img").attr("src",obj.imageUrl);
        $("td#detail").text(obj.detail);
        $("td#nickName").text(obj.nickName);
        $("td#phone").text(obj.phone);
        $("td#email").text(obj.email);
     },
       error: function(){
        alert('服务端异常');
        }
    });
	 // $.getJSON("js/json/approval-news.json", function(data) {

	 // });
	 //---------------通过/不通过------------------------
$("button#pass-button").click(function(){
	var flag = this.value;
	if(flag=="1"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;//要传输的数据
      info.meg = null;
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/comfirmDynamicNews',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         location.href='./approval-3.html';         
     },
       error: function(){
        alert('服务端异常');
        }
    });

    //<---------------------------------------表格重新导入
    }
    else if(flag=="0"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;
      info.meg = $('textarea').val();
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/comfirmDynamicNews',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         location.href='./approval-3.html';
     },
       error: function(){
        alert('服务端异常');
        }
    });     
    }
   });
});