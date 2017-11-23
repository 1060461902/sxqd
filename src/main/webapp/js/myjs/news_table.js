function todelete()
{
	 var r=confirm("是否删除该信息");
      if (r==true)
      {
        alert("删除成功");
        document.getElementById("div1").style.color ="gray";

      }	
}
function showornot()
{
	var n=document.getElementById("state").innerHTML;
	if(n=="已撤下"){
      var r=confirm("是否展示该条动态");
      if (r==true){
      document.getElementById("state").innerHTML ="展示中";
      document.getElementById("state").style.color ="#dddddd";
     }
     }
  	else if(n=="展示中"){
      var r=confirm("是否撤下该条动态");
      if (r==true){
      document.getElementById("state").innerHTML ="已撤下";
      document.getElementById("state").style.color ="red";
     }
  }
}
function todelete2()
{
   var r=confirm("是否删除该信息");
      if (r==true)
      {
        alert("删除成功");
        document.getElementById("div2").style.color ="gray";

      } 
}
function showornot2()
{
  var n=document.getElementById("state2").innerHTML;
  if(n=="已撤下"){
      var r=confirm("是否展示该条动态");
      if (r==true){
      document.getElementById("state2").innerHTML ="展示中";
      document.getElementById("state2").style.color ="#dddddd";
     }
     }
    else if(n=="展示中"){
      var r=confirm("是否撤下该条动态");
      if (r==true){
      document.getElementById("state2").innerHTML ="已撤下";
      document.getElementById("state2").style.color ="red";
     }
  }
}
function tableexport()
{
  var r=confirm("是否导出表格");
  if(r==true)
  {
    alert("导出成功");
  }
}