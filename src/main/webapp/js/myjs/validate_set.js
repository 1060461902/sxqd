/*
* @Author: chenzexiao
* @Date:   2017-10-26 14:35:23
* @Last Modified by:   chenzexiao
* @Last Modified time: 2017-10-26 15:24:38
*/
$(document).ready(function(){
    // $.getJSON("js/json/weight.json", function(data) {
    //   $('.setDiv input#a:eq(0)').val(data.teacherWeight);
    //   $('.setDiv input#a:eq(1)').val(data.companyWeight);
    //   $('.setDiv input#b:eq(0)').val(data.tWeekReport);
    //   $('.setDiv input#b:eq(1)').val(data.tSummary);
    //   $('.setDiv input#b:eq(2)').val(data.tFinalReport );
    //   $('.setDiv input#c:eq(0)').val(data.cWeekReport);
    //   $('.setDiv input#c:eq(1)').val(data.cAttendance);
    // });
      $.ajax({
       type: 'get',
       url: '/fieldworkmanage/admin/weight',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
      $('.setDiv input#x:eq(0)').val(data.teacherWeight);
      $('.setDiv input#x:eq(1)').val(data.companyWeight);
      $('.setDiv input#y:eq(0)').val(data.tWeekReport);
      $('.setDiv input#y:eq(1)').val(data.tSummary);
      $('.setDiv input#y:eq(2)').val(data.tFinalReport );
      $('.setDiv input#z:eq(0)').val(data.cWeekReport);
      $('.setDiv input#z:eq(1)').val(data.cAttendance);
     },
       error: function(){
        alert('服务端异常');
        }
    });
  $('button#qzbtn').click(function(){
    if(parseInt($('.setDiv input#x:eq(0)').val())+parseInt($('.setDiv input#x:eq(1)').val())!=100){
      alert("请使老师权重和企业权重之和为100");
      $('.setDiv input#a:eq(0)').focus();
    }
    else if(parseInt($('.setDiv input#y:eq(0)').val())+parseInt($('.setDiv input#y:eq(1)').val())+parseInt($('.setDiv input#y:eq(2)').val())!=100){
      alert("请使老师评分下三项之和为100");
      $('.setDiv input#b:eq(0)').focus();
    }
    else if(parseInt($('.setDiv input#z:eq(0)').val())+parseInt($('.setDiv input#z:eq(1)').val())!=100){
      alert("请使企业评分下两项之和为100");
      $($('.setDiv input#c:eq(0)')).focus();
    }
    else {
      var info = new Object();
      info.teacherWeight = '0.'+ $('.setDiv input#x:eq(0)').val();
      info.companyWeight = '0.'+ $('.setDiv input#x:eq(1)').val();
      info.tWeekReport = '0.'+ $('.setDiv input#y:eq(0)').val();
      info.tSummary = '0.'+ $('.setDiv input#y:eq(1)').val();
      info.tFinalReport = '0.'+ $('.setDiv input#y:eq(2)').val();
      info.cWeekReport = '0.'+ $('.setDiv input#z:eq(0)').val();
      info.cAttendance = '0.'+ $('.setDiv input#z:eq(1)').val();
      //alert(info.cAttendance);
      $.ajax({
       type: 'get',
       url: '/fieldworkmanage/admin/weight',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
                    if (data.code == "200") {
                         alert('操作成功');
                    }
                    else if(data.code == "500"){
                         alert('操作异常');
                    }
     },
       error: function(){
        alert('服务端异常');
        }
    });
    }
  });
});
function validate_oldpsw(oldpsw,alerttxt1)
{
  if (oldpsw==null||oldpsw=="")
    {
     alert(alerttxt1);
     return false;
    }
  else {
  	return true;
    }
}
function validate_newpsw(newpsw,alerttxt)
{
  if (newpsw==null||newpsw=="")
    {
     alert(alerttxt);
     return false;
    }
  else {
  	return true;
    }
}
function validate_newpsw2(newpsw2,alerttxt)
{
  if (newpsw2==null||newpsw2=="")
    {
     alert(alerttxt);
     return false;
    }
  else {
  	return true;
    }
}

function validate_form()
{
  var oldpsw=$("#oldpsw").val();
  var newpsw=$("#newpsw").val()
  var newpsw2=$("#newpsw2").val()
  if (validate_oldpsw(oldpsw,"请输入您的原密码")==false)
    {$("#oldpsw").focus();return false}
  else if(validate_newpsw(newpsw,"请填写您的新密码")==false)
  	{$("#newpsw").focus();return false}
  else if(validate_newpsw2(newpsw2,"请再次填写您的新密码")==false)
  	{$("#newpsw2").focus();return false}
  else if(newpsw!=newpsw2)
    {alert("两次密码不相符！");return false}
  else{
         // $.getJSON("./js/json/validate1.json", function(data) {
         //            if (data.code == "200") {
         //                 alert('操作成功');
         //            }
         //            else if(data.code == "500"){
         //                 alert('原密码错误');
         //            }
         //            else{
         //                 alert("服务器异常");
         //            }
         //        })
    var info = new Object();  // alert(info);
    info.username = "admin"; //alert(info.username);
    info.oldpassword= oldpsw; //alert(info.oldpassword);
    info.newpassword = newpsw;  //alert(info.newpassword); 
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/modifyPwd',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
                    if (data.code == "200") {
                         alert('操作成功');
                    }
                    else if(data.code == "500"){
                         alert('原密码错误');
                    }
     },
       error: function(){
        alert('服务端异常');
        }
    });
      return true;
    }
}
