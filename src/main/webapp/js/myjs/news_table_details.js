$(document).ready(function(){
  var id = window.location.href;
   $.getJSON("js/json/approval-news.json", function(data) {
    window.comid =  data.dynamicNewsDetails.id;
        var obj = data.dynamicNewsDetails;
        $("td#title").text(obj.title);
        $("td img#imageUrl").attr("src",obj.imageUrl);
        $("td#detail").text(obj.detail);
        $("td#date").text(obj.startTime+"~");
        $("td#nickName").text(obj.nickName);
        $("td#phone").text(obj.phone);
        $("td#email").text(obj.email);
        if(obj.showstatus=='0'){
          $("td#switch").html("已撤下<a href="+"#"+"><img src="+"images/switch-off.png"+"></a>");
          $("td#switch").attr("value","0");
          $("td#switch").css("color","red");
          $("td#date").css("color","red");
        }
   });
    // 动态操作
  $("td#switch").click(function(){
  	if($("td#switch").attr("value")=="1"){
  	 var r=confirm("是否撤下该条动态");
      if(r==true){
      var info = new Object();
      info.id=id;
      info.showStatus=$("td#switch").attr("value");
      $("td#switch").html("已撤下<a href="+"#"+"><img src="+"images/switch-off.png"+"></a>");
      $("td#switch").attr("value","0");
      $("td#switch").css("color","red");
      $("td#date").css("color","red");
      }
  	}
  	else if($("td#switch").attr("value")=="0"){
  	  var r=confirm("是否展示该条动态");
      if(r=true){
      var info = new Object();
      info.id=id;
      info.showStatus=$("td#switch").attr("value");
  	  $("td#switch").html("展示中<a href="+"#"+"><img src="+"images/switch-on.png"+"></a>");
  	  $("td#switch").attr("value","1");
  	  $("td#switch").css("color","#333");
  	  $("td#date").css("color","333");
     }
  	}
});

});