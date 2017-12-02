$(document).ready(function(){ 
  $("td#switch").click(function(){
  	if($("td#switch").attr("value")=="1"){
  	  alert("是否撤下该条动态");
  	  $("td#switch").html("已撤下<a href="+"#"+"><img src="+"images/switch-off.png"+"></a>");
  	  $("td#switch").attr("value","0");
  	  $("td#switch").css("color","red");
  	  $("td#date").css("color","red");
  	}
  	else if($("td#switch").attr("value")=="0"){
  	  alert("是否展示该条动态");
  	  $("td#switch").html("展示中<a href="+"#"+"><img src="+"images/switch-on.png"+"></a>");
  	  $("td#switch").attr("value","1");
  	  $("td#switch").css("color","#333");
  	  $("td#date").css("color","333");
  	}
});

});