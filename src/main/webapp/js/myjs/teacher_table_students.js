function optionclick (){
    var info = new Object();
    info.id=teacherId[1];
    info.major = $('select#major').val();
    info.clss = $('select#class').val();
    info.status = $('select#status').val();
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudent',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         $('#table').dataTable().fnClearTable(); //清除表格内
         $('#table').dataTable().fnDestroy();
          writein(data);
            var docrTable = $('#table').dataTable({
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
        alert('服务端异常1');
        }
    });//ajax
     $('select#class').empty();
     $('select#major').empty();
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudentScreen',
       async: false,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.major.length;
        $('select#major').append('<option  selected = "selected">专业</option>');
        $('select#class').append('<option  selected = "selected">班级</option>');
       for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.major[i]+'</option>');
       }
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       },
        error: function(){
        alert('服务端异常2');
        }
        });//ajax  
}
function optionclick1 (){
    var info = new Object();
    info.id=teacherId[1];
    info.major = $('select#major').val();
    info.clss = $('select#class').val();
    info.status = $('select#status').val();
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudent',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         $('#table').dataTable().fnClearTable(); //清除表格内
         $('#table').dataTable().fnDestroy();
          writein(data);
            var docrTable = $('#table').dataTable({
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
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudentScreen',
       async: false,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        $('select#class').append('<option  selected = "selected">班级</option>');
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       },
        error: function(){
        alert('服务端异常2');
        }
        });//ajax  
}
function optionclick2 (){
    var info = new Object();
    info.id=teacherId[1];
    info.major = $('select#major').val();
    info.clss = $('select#class').val();
    info.status = $('select#status').val();
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudent',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
         $('#table').dataTable().fnClearTable(); //清除表格内
         $('#table').dataTable().fnDestroy();
          writein(data);
            var docrTable = $('#table').dataTable({
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
        alert('服务端异常1');
        }
    });//ajax
     $('select#major').empty();
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudentScreen',
       async: false,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.major.length;
        $('select#major').append('<option  selected = "selected">专业</option>');
       for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.major[i]+'</option>');
       }
       },
        error: function(){
        alert('服务端异常2');
        }
        });//ajax  
}

function writein(data){
    var tbody = document.getElementsByTagName ('tbody')[0];
    var len = data.students.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.students[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="student_table_details.html?id='+obj.id+'">'+obj.nickName;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.userName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.major;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.clss;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status; 
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.companyName;
      } //for
    //optionclick();
}
$(document).ready(function(){
  // 筛选的一系列操作
    var info = new Object();
    window.teacherId= window.location.href.split('=');
info.id = teacherId[1];
    $('a#tea').attr('href','teacher_table_details.html?id='+info.id);
info.major = '专业';
info.clss = '班级';
info.status = '实习状态';
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudentScreen',
       async: false,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       var namelength = data.major.length;
       for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.major[i]+'</option>');
       }
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
  //   $.getJSON("js/json/approval-2-companyname.json", function(data) {
  // });
    var info= new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];
    info.major = '专业';
    info.clss = '班级';
    info.status = '实习状态';
//第一次导入表格
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudent',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        $('span#name').text(data.name);
           writein(data);
           var docrTable = $('#table').dataTable({
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
  // $.getJSON("js/json/teacher_table_students.json", function(data) {
  //   writein(data);
  //          var docrTable = $('#table').dataTable({
  //               "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
  //               "bFilter" : true, //是否启动过滤、搜索功能
  //               "info": false,
  //                "pageLength": 8,
  //               "lengthChange" : false, 
  //                 "oLanguage": { //国际化配置
  //                   "sProcessing" : "正在获取数据，请稍后...",
  //                   "sLengthMenu" : "显示 _MENU_ 条",
  //                   "sZeroRecords" : "没有您要搜索的内容",
  //                   "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
  //                   "sInfoEmpty" : "记录数为0",
  //                   "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
  //                   "sInfoPostFix" : "",
  //                   "sSearch" : "搜索",
  //                   "sUrl" : "",
  //                   "oPaginate": {
  //                       "sFirst" : "第一页",
  //                       "sPrevious" : "上一页",
  //                       "sNext" : "下一页",
  //                       "sLast" : "最后一页"
  //                   }
  //               },
  //             });
  // })
//function optionclick(){

//}
});
