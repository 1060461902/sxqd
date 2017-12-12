$(document).ready(function(){
  window.companyName = window.location.href;
  var info = new Object();
  info.status ='实习状态';
  info.class ='班级';
  info.major ='专业';
  info.id=companyName;
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyStudnetScreening',
       async: true,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.major.length;
       for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.major[i]+'</option>');
       }
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       //筛选（需要表格重新导入）
  $("option").click(function(){
    var info = new Object();
    info.status = $('select#status').val();
    info.class = $('select#class').val();
    info.major = $('select#major').val();
    info.id = companyName;
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyStudent',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
     $('#table-2').dataTable().fnClearTable(); //清除表格内
     $('#table-2').dataTable().fnDestroy();
      writein(data);
      resetpsw();//-------------重置密码------
      forbidden();// -------------禁用--------------
            var docrTable = $('#table-2').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 7,
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
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
     $('select#class').empty();
     $('select#major').empty();
  //重新获得筛选条件
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyStudnetScreening',
       async: true,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.major.length;
       for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.major[i]+'</option>');
       }
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       },
        error: function(){
        alert('服务端异常');
        }
        });//ajax  
    //$.getJSON("js/json/approval-2.json", function(data) {
         // });
  });
     },
       error: function(){
        alert('服务端异常');
        }
    });
  //点一次导入表格
  var info = new Object();
  info.status ='实习状态';
  info.class ='班级';
  info.major ='专业';
  info.id =companyName;
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyStudent',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
      writein(data);
      resetpsw();//-------------重置密码------
      forbidden();// -------------禁用--------------
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
     },
       error: function(){
        alert('服务端异常');
        }
    });
//     $.getJSON("js/json/student_table.json", function(data) {
//       writein(data);
//       resetpsw();//-------------重置密码------
//       forbidden();// -------------禁用--------------
//   var docrTable = $('#students').dataTable({
//                 "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
//                 "bFilter" : true, //是否启动过滤、搜索功能
//                 "info": false,
//                  "pageLength": 8,
//                 "lengthChange" : false, 
//                   "oLanguage": { //国际化配置
//                     "sProcessing" : "正在获取数据，请稍后...",
//                     "sLengthMenu" : "显示 _MENU_ 条",
//                     "sZeroRecords" : "没有您要搜索的内容",
//                     "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
//                     "sInfoEmpty" : "记录数为0",
//                     "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
//                     "sInfoPostFix" : "",
//                     "sSearch" : "搜索",
//                     "sUrl" : "",
//                     "oPaginate": {
//                         "sFirst" : "第一页",
//                         "sPrevious" : "上一页",
//                         "sNext" : "下一页",
//                         "sLast" : "最后一页"
//                     }
//                 },
//               });
// });//getjson
//------------------->
function writein(data){
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.studentList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.studentList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            //$('tr').addClass('pointer');
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="student_table_details.html?id="'+obj.id+'">'+obj.nickName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.userName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.major;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.class;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_details.html?id='+obj.teacherId+'">'+obj.teacherName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="重置密码" class="reset"  id="'+obj.id+'"><i class="fa fa-repeat fa-2x"></i></a>&nbsp;&nbsp;<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-2x"></i></a>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(6) a.forbidden').css("color","red");
            }
      } //for
}
function resetpsw(){
$('.reset').click(function(){
   var info = new Object();
   info.id = $(this).attr("id");//alert(info.id);
   info.operationType ="1";
   info.roleId = "4";
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
   alert('重置成功');
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
});
};
//禁用
function forbidden(){
$('.forbidden').click(function(){
// alert($(this).attr("id")+'+'+$(this).attr("value"));
 var info = new Object();
 info.id = $(this).attr("id");
 info.roleId = "4";
 if($(this).attr("value")=='true'){
   var r=confirm("是否解禁该用户");
      if (r==true)
      { 
        info.operationType ="4";
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        alert("解禁成功");
        $(this).css("color","#337ab7");
        $(this).attr("value",'false');
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
      } 
    }
    else if($(this).attr("value")=='false'){
       var r=confirm("是否禁用该用户");
      if (r==true)
      { 
        info.operationType ="2";
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        alert("禁用成功");
        $(this).css("color","red");
        $(this).attr("value",'true');
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajaX

      }
    }
});
};
});//document