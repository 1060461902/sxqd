$(document).ready(function(){
  var info = new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];
    window.id=zhf[1];

     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showDynamicNewsDetails',
       data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         var obj = data.dynamicApproves;
         $("#title").text(obj.title);
         $("td#title").text(obj.title);
         $("td img#imageUrl").attr("src",obj.imageUrl);
         $("td#detail").text(obj.detail);
         $("td#date").text(obj.startTime);
         $("td#nickName").text(obj.nickName);
         $("td#phone").text(obj.phone);
         $("td#email").text(obj.email);
         if(obj.showstatus== false){
           $("td#switch").html("已撤下<a href="+"#"+"><img src="+"images/switch-off.png"+"></a>");
           $("td#switch").attr("value","0");
           $("td#switch").css("color","red");
           $("td#date").css("color","red");
         }
      },
       error: function(){
        alert('服务端异常');
        }
    })
   // $.getJSON("js/json/news_table_details.json", function(data) {

   // });
    // 动态操作
  $("td#switch").click(function(){
  	if($("td#switch").attr("value")=="1"){
  	 var r=confirm("是否撤下该条动态");
      if(r==true){
      var info = new Object();
      info.id=id;
      info.showStatus=$("td#switch").attr("value");
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/comfirmShow',
       data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
      $("td#switch").html("已撤下<a href="+"#"+"><img src="+"images/switch-off.png"+"></a>");
      $("td#switch").attr("value","0");
      $("td#switch").css("color","red");
      $("td#date").css("color","red");
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
      }//r==true
  	}
  	else if($("td#switch").attr("value")=="0"){
  	  var r=confirm("是否展示该条动态");
      if(r=true){
      var info = new Object();
      info.id=id;
      info.showStatus=$("td#switch").attr("value");
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/comfirmShow',
       data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
      $("td#switch").html("展示中<a href="+"#"+"><img src="+"images/switch-on.png"+"></a>");
      $("td#switch").attr("value","1");
      $("td#switch").css("color","#333");
      $("td#date").css("color","333");
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
     }
  	}
});//动态操作

});