$(document).ready(function(){
    var info =new Object();
    info.id = window.location.href;
    $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showWeekly',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
        var obj = data.weeklyList;
        var length = data.weeklyList.length;
        var tbody = document.getElementsByTagName ('tbody')[0];
        for (var i = 0; i <length; i++) {
            var obj = data.weeklyList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.week;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.teacherScore;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.companyScore;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.datetime;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="student_table_zbyl.html?id='+obj.id+'" target="iFrame1">查看</a>';
        }
        var docrTable = $('#table-zb').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : false, //是否启动过滤、搜索功能
                "lengthChange" : false,
                "pageLength": 6,
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
              });    //table
     },
       error: function(){
        alert('服务端异常');
        }
    });
    //   $.getJSON("js/json/student_zb.json", function(data) {
    //     var obj = data.weeklyList;
    //     var length = data.weeklyList.length;
    //     var tbody = document.getElementsByTagName ('tbody')[0];
    //     for (var i = 0; i <length; i++) {
    //         var obj = data.weeklyList[i];
    //         var tr = tbody.insertRow(tbody.rows.length);
    //         var j=i+1;
    //         $("tr:eq("+j+")").val(obj.id);//对当前行赋值
    //         var td = tr.insertCell (tr.cells.length);
    //         td.innerHTML = obj.week;
    //         var td = tr.insertCell (tr.cells.length);
    //         td.innerHTML = obj.teacherScore;
    //         var td = tr.insertCell (tr.cells.length);
    //         td.innerHTML = obj.companyScore;
    //         var td = tr.insertCell (tr.cells.length);
    //         td.innerHTML = obj.datetime;
    //         var td = tr.insertCell (tr.cells.length);
    //         td.innerHTML = '<a href="student_table_zbyl.html?id='+obj.id+'" target="iFrame1">查看</a>';
    //     }
    //     var docrTable = $('#table-zb').dataTable({  
    //             "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
    //             "bFilter" : false, //是否启动过滤、搜索功能
    //             "lengthChange" : false, 
    //             "pageLength": 6,
    //               "oLanguage": { //国际化配置  
    //                 "sProcessing" : "正在获取数据，请稍后...",    
    //                 "sLengthMenu" : "显示 _MENU_ 条",    
    //                 "sZeroRecords" : "没有您要搜索的内容",    
    //                 "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",    
    //                 "sInfoEmpty" : "记录数为0",    
    //                 "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
    //                 "sInfoPostFix" : "",    
    //                 "sSearch" : "搜索",    
    //                 "sUrl" : "",    
    //                 "oPaginate": {    
    //                     "sFirst" : "第一页",    
    //                     "sPrevious" : "上一页",    
    //                     "sNext" : "下一页",    
    //                     "sLast" : "最后一页"    
    //                 }  
    //             },                            
    //           });    //table
    // });
});
    