$(document).ready(function(){
  $(".templatemo-left-nav a").click(function () {
     $(".templatemo-left-nav a").css("background","#425FAF");
     $(this).css("background","#324E8F");
    });
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };
  $("[href='data_analysis.html']").click(function(){
    $(".nav-title").text("数据分析");
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };
  });
  $("[href='approval.html']").click(function(){
    $(".nav-title").text("审批");
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };
  });
  $("[href='btn_table.html']").click(function(){
    $(".nav-title").text("信息列表");
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };
  }); 
  $("[href='set.html']").click(function(){
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };
    $(".nav-title").text("设置");
  });   

      $(".loginout").click(function(){
            $.getJSON("./js/json/validate1.json", function(data) {
                    if (data.code == "200") {
                         location.href="admin-login.html"
                    }
                    else if(data.code == "500"){
                         alert('请再次尝试');
                    }
                    else{
                         alert("服务器异常");
                    }
                })
    });
});