$(document).ready(function(){
  $.getJSON("js/json/teacher_table.json", function(data) {
       var table = document.getElementsByTagName ('table')[0];
       var nums = data.teachers.length;
       for ( var i = 0; i < data.teachers.length; i++)
       {
          var tr = table.insertRow(table.rows.length);
          var obj = data.teachers[i];
          var n=0;
           for ( var p in obj)
           {    
               var td = tr.insertCell (tr.cells.length);//cells:返回包含行中所有已经存在的单元格的一个数组。insertCell: 在一行中的指定位置插入一个空的 <td> 元素。
               if(n!=2&&n!=3)
               td.innerText = obj[p];
               if(n==2){
                td.innerHTML =obj[p];
                var td = tr.insertCell (tr.cells.length);
                td.innerHTML ='<td><a href="#" title="点击分配"  data-toggle="modal" data-target="#myModal"><i class="fa fa-user-plus fa-1x"></i></a></td>';
              }
               else if(n==3){
                td.innerHTML =obj[p];
               var td = tr.insertCell (tr.cells.length);
                td.innerHTML ='<td><a href="#">查看列表</a></td>';
              }
              else if(n==4)
              {
                 break;   
              }
              n++;                 
            }
              
              var td = tr.insertCell (tr.cells.length);
              td.innerHTML = '<a href = "javascript:;" title="重置密码" onclick="reset()"><i class="fa fa-repeat fa-2x"></i>&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" title="禁用" onclick="forbidden()" id="forbidden"><i class="fa fa-ban fa-2x"></i></a>';
              if(data.teachers[i].forbidden==1)
                {
                 $('table tr:eq('+i+') td:eq(7)').find("#forbidden").css("color","#337ab7");

                }
              else if(data.teachers[i].forbidden==0)
                {
                 $('table tr:eq('+i+') td:eq(7)').find("#forbidden").css("color","red");
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
  alert($("this").css("color"));
	if($("this").css("color") !="red"){
	  var r=confirm("是否禁用该用户");
      if (r==true)
      {
        alert("禁用成功");
        $("this").css("color","red");
      }	
	}
      else{
      var r=confirm("是否取消禁用该用户");
      if (r==true)
      {
        alert("操作成功");
        $(this).css("color","#337ab7");
      }	
      }
}
function tableimport()
{
  var r=confirm("是否导出表格");
  if(r==true)
  {
    alert("导出成功");
  }
}