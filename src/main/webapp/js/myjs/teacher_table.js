$(document).ready(function(){
  $.getJSON("js/json/teacher_table.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var nums = data.teachers.length;
       for ( var i = 0; i < data.teachers.length; i++)
       {
          var tr = tbody.insertRow(tbody.rows.length);
          var obj = data.teachers[i];
          var n=0;
          var td = tr.insertCell (tr.cells.length);
          td.innerHTML = '<input type="checkbox" value="0">';
           for ( var p in obj)
           {
               var td = tr.insertCell (tr.cells.length);//cells:返回包含行中所有已经存在的单元格的一个数组。insertCell: 在一行中的指定位置插入一个空的 <td> 元素。
               if(n==0){
               td.innerHTML = '<a href = "teacher_table_details.html">'+obj[p]+'</a>';
             }
               else if(n==1||n==2)
               {
               td.innerHTML = obj[p]; 
               }
               else if (n==3) {
               td.innerHTML = obj[p]; 
               if(obj[p]!="0"){
               td.innerHTML ='<a href="#" class="showlist">'+obj[p]+'<i class="fa fa-fw fa-sort-desc"></i></a>';
               }
                break;
               };
               n++;
           }
              var td = tr.insertCell (tr.cells.length);
              td.innerHTML = '<a href = "javascript:;" title="重置密码" onclick="reset()"><i class="fa fa-repeat fa-2x"></i>&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" title="禁用" onclick="forbidden()" id="forbidden"><i class="fa fa-ban fa-2x"></i></a>';
              if(data.teachers[i].forbidden==1)
                {
                 $('table tr:eq('+i+') td:eq(5)').find("#forbidden").css("color","#337ab7");

                }
              else if(data.teachers[i].forbidden==0)
                {
                 $('table tr:eq('+i+') td:eq(5)').find("#forbidden").css("color","red");
                }

         }
              $('.showlist').click(function(){
                if($(this).children("i").attr("class")=="fa fa-fw fa-sort-desc"){
                  $(this).parent("td").parent("tr").css("height","500px");
                  $(this).children("i").removeClass("fa-sort-desc");
                  $(this).children("i").addClass("fa-sort-up");
                  }
                else if($(this).children("i").attr("class")=="fa fa-fw fa-sort-up"){
                  $(this).parent("td").parent("tr").css("height","57.8px");
                  $(this).children("i").removeClass("fa-sort-up");
                  $(this).children("i").addClass("fa-sort-desc");
                  }

              });
function forbidden()
{
  alert($(this).css("color"));
  if($(this).css("color") !="red"){
    var r=confirm("是否禁用该用户");
      if (r==true)
      {
        alert("禁用成功");
        $(this).css("color","red");
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
};
               var docrTable = $('#table-teacher').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 3,
                "lengthChange" : false, 
                  "oLanguage": { //国际化配置
                    "sProcessing" : "正在获取数据，请稍后...",
                    "sLengthMenu" : "显示 _MENU_ 条",
                    "sZeroRecords" : "没有您要搜索的内容",
                    "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                    "sInfoEmpty" : "记录数为0",
                    "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
                    "sInfoPostFix" : "",
                    "sSearch" : "搜索",
                    "sUrl" : "",
                    "oPaginate": {
                        "sFirst" : "第一页",
                        "sPrevious" : "上一页",
                        "sNext" : "下一页",
                        "sLast" : "最后一页"
                    }
                },
              });
  });//getjson

});
function reset()
{
var r=confirm("是否重置为初始密码");
if (r==true)
  {
  alert("重置成功");
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