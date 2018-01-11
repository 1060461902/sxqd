$(document).ready(function(){
  var info =new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];
	 $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showCompanyDetails',
       data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        window.comid =  data.compamyViewList[0].id;
        $('a#jobs').attr("href",'company_table_jobs.html?id='+comid);
        var obj = data.compamyViewList[0];
        $('img#identification-photo-2').attr('src',obj.logo);
        $("#name").text(obj.companyName);
        $("#name2").text(obj.companyName);
        $("span#network").text(obj.network);
        $("span#type").text(obj.type);
        $("span#stage").text(obj.stage);
        $("span#size").text(obj.size);
        $("h3#intruction").text(obj.intruction);
        $("h3#address").text(obj.address);
        $('div.profile-photo-container>img:eq(0)').attr("src",obj.licence);
        $('div.profile-photo-container>img:eq(1)').attr("src",obj.taxRegistration);
        $('div.profile-photo-container>img:eq(2)').attr("src",obj.organizationCode);
        //标签
        var mark =data.Mark;
        var marklength = data.Mark.length;
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
});