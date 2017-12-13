$(document).ready(function(){
	  var info =new Object();
  info.id = window.location.href;
	 $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showCompanyDetails',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        window.comid =  data.compamyViewList[0].id;
        var obj = data.compamyViewList[0];
        $("span#name").text(obj.companyName);
        $("span#network").text(obj.network);
        $("span#type").text(obj.type);
        $("span#stage").text(obj.stage);
        $("span#size").text(obj.size);
        $("p#intruction").text(obj.intruction);
        $("p#address").text(obj.address);
        $('div.profile-photo-container>img:eq(0)').attr("src",obj.licence);
        $('div.profile-photo-container>img:eq(1)').attr("src",obj.tax_registration);
        $('div.profile-photo-container>img:eq(2)').attr("src",obj.organization_code);
        //标签
        var mark =data.mark;
        var marklength = data.mark.length;
        for(var n=0;n<marklength;n++)
        {
          $('div.mark-content').append('<span class="label label-primary" id="mark">'+mark[n].mark+'</span>');
        };
        // 相册
        var album = data.Image;
        var length = data.Image.length;
        for(var n=0;n<length;n++)
        {
          $('div.album-2').append("<div class="+" inline-pic"+"><img src="+album[n].url+" alt="+"Photo"+" class="+"img-responsive width="+"60px"+"></div>");
        };
        $('.album-1 img').attr("src",album[0].url);
        $('img').click(function(){
          $('.album-1 img').attr("src",$(this).attr("src"));
        });

     },
       error: function(){
        alert('服务端异常');
        }
    });
	 // $.getJSON("js/json/approval_company.json", function(data) {
	 // });
	 //---------------通过/不通过------------------------
$("button").click(function(){
	var flag = this.value;
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id = id;
      info.passFlag = flag;//要传输的数据
      info.meg =null;
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/comfirmRegister',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         $('th>input:checkbox').attr('checked',false);
         location.href='./approval.html';
     },
       error: function(){
        alert('服务端异常');
        }
    });
   });
});