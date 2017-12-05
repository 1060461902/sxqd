$(document).ready(function(){
  var info = new Object();
  info.status ='全部';
  info.class ='全部';
  info.major ='全部';
    $.getJSON("js/json/student_table.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.studentList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.studentList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            $('tr').addClass('pointer');
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.nickName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.userName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.major;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.class;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.teacherName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="重置密码" class="reset"  id="'+obj.id+'"><i class="fa fa-repeat fa-2x"></i></a>';
            // if(obj.forbidden==true){
            //   $('tr:eq('+j+') td:eq(6) a').css("color","red");
            // }
      } //for
//-------------点击---------
$('tr').click(function(){
  var href ="./student_table_details.html?id="+$(this).val();
  location.href=href;
})

//-------------重置密码------
$('.reset').click(function(){
   var info = new Object();
   info.id = $(this).attr("id");//alert(info.id);
   info.operationType ="1";
   info.roleId = "4";
   alert('重置成功');
});
// -------------禁用--------------
// $('.forbidden').click(function(){
// // alert($(this).attr("id")+'+'+$(this).attr("value"));
//  var info = new Object();
//  info.id = $(this).attr("id");
//  info.roleId = "4";
//  if($(this).attr("value")=='true'){
//    var r=confirm("是否解禁该用户");
//       if (r==true)
//       { 
//         info.operationType ="4";
//         alert("解禁成功");
//         $(this).css("color","#337ab7");
//         $(this).attr("value",'false');
//       } 
//     }
//     else if($(this).attr("value")=='false'){
//        var r=confirm("是否禁用该用户");
//       if (r==true)
//       { 
//         info.operationType ="2";
//         alert("禁用成功");
//         $(this).css("color","red");
//         $(this).attr("value",'true');
//       }
//     }
// });

  var docrTable = $('#students').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 8,
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
//------------------->
});//document