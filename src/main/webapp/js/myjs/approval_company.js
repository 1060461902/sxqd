$(document).ready(function(){ 
	var id = window.location.href;
  alert(id);
	  // $.ajax({
   //     type: 'get',
   //     url: '/fieldManagement/admin/showCompanyDeails',
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
	 $.getJSON("js/json/approval_company.json", function(data) {
	 	window.comid =  data.compamyViewList[0].id;
	 	window.email =  data.compamyViewList[0].email;
        var obj = data.compamyViewList[0];
        $("span#name").text(obj.companyName);
        $("span#network").text(obj.network);
        $("span#type").text(obj.type);
        $("span#stage").text(obj.stage);
        $("span#size").text(obj.size);
        $("span#slogans").text(obj.slogans);
        $("span#mark").text(obj.mark);
        $("p#intruction").text(obj.intruction);
        $("p#address").text(obj.address);
        $("img#identification-photo").attr("src",obj.logo);
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
      location.href='./approval.html';
    //<---------------------------------------表格重新导入
    }
    else if(flag=="0"){
      var id = new Array();
      id[0] = comid;
      var info = new Object();
      info.id=id;
      info.passFlag=flag;
      location.href='./approval.html';
    }
   });
});