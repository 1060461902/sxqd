/*
* @Author: chenzexiao
* @Date:   2017-10-26 14:35:23
* @Last Modified by:   chenzexiao
* @Last Modified time: 2017-10-26 15:24:38
*/
$(document).ready(function(){  
    if ($(".badge").text()==0) {
      $(".badge").css("display","none")
    };
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
