function reset()
{
var r=confirm("是否重置为初始密码");
if (r==true)
  {
  alert("重置成功");
  }
}
function forbidden()
{
	var forbidden=document.getElementById("forbidden");
	if(forbidden.style.color !="red"){
	  var r=confirm("是否禁用该用户");
      if (r==true)
      {
        alert("禁用成功");
        document.getElementById("forbidden").style.color ="red";
      }	
	}
      else{
      var r=confirm("是否取消禁用该用户");
      if (r==true)
      {
        alert("操作成功");
        document.getElementById("forbidden").style.color ="#337ab7";
      }	
      }
}
function tableimport()
{
  var r=confirm("是否批量导入");
  if(r==true)
  {
    alert("导入成功");
  }
}