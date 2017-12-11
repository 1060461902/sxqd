$(document).ready(function(){
  // 筛选的一系列操作
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.Names.length;
       for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.Names[i].companyName+'</option>');
        $('select#class').append('<option>'+data.Names[i].companyName+'</option>');
        $('select#status').append('<option>'+data.Names[i].companyName+'</option>');
       }
       //筛选（需要表格重新导入）
  $("option").click(function(){
    var info = new Object();
    info.companyName = $('select#major').val();
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
          checkbox();
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
  });//option
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
  //   $.getJSON("js/json/approval-2-companyname.json", function(data) {
  // });
    var info= new Object(); 
    info.id= window.location.href;
//第一次导入表格
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeacherStudent',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
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
            td.innerHTML = obj.class;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status; 
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.companyName; 
            // if(obj.forbidden==true){
            //   $('tr:eq('+j+') td:eq(6) a').css("color","red");
            // }
      } //for
}
});
