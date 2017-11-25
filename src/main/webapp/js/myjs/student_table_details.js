$(document).ready(function(){  
$("button#male").click(function(){
  $("button#male").css("background-color","#09C");
    $("button#female").css("background-color","#FFF");
});
$("button#female").click(function(){
  $("button#female").css("background-color","pink");
    $("button#male").css("background-color","#FFF");
});

  
});