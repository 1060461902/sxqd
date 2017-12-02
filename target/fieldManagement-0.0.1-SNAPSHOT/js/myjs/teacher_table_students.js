$(document).ready(function(){
  $.getJSON("js/json/student_table.json", function(data) {
       var table = document.getElementsByTagName ('table')[0];
       var nums = data.students.length;
       for ( var i = 0; i < data.students.length; i++)
       {
          var tr = table.insertRow(table.rows.length);
          var obj = data.students[i];
          var n=0;
          var td = tr.insertCell (tr.cells.length);
          td.innerHTML = '<input type="checkbox" value="0">';
           for ( var p in obj)
           {
               var td = tr.insertCell (tr.cells.length);//cells:返回包含行中所有已经存在的单元格的一个数组。insertCell: 在一行中的指定位置插入一个空的 <td> 元素。
               td.innerHTML = '<a href = "student_table_details.html">'+obj[p]+'</a>';
               if (n==6) {
                break;
               };
               n++;
           }         
         }
  })
});
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