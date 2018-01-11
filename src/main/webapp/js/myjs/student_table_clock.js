function optionclick(){
    var info = new Object();
    info.month =  $('select').val();
      var zhf= window.location.href.split('=');
      info.id = zhf[1];
      $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showCheck',
       data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
     $('#table-1').dataTable().fnClearTable(); //清除表格内
     $('#table-1').dataTable().fnDestroy();
      writein(data);
            var docrTable = $('#table-1').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 10,
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
}
function writein(data){
        var obj2 = data.date;
        var length2 = data.date.length;
        for( var i=0;i<length2;i++){
            var tbody = document.getElementsByTagName ('tbody')[0];
            var tr = tbody.insertRow(tbody.rows.length);
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj2[i].date;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj2[i].startTime;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj2[i].endTime;
        }
}
$(document).ready(function(){
//筛选
    var info = new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];

      $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showCheckScreening',
       async: true,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
        var obj1 = data.month;
        var length1 = data.month.length;
        for(var n=0;n<length1;n++){
            $('select').append('<option>'+obj1[n].screens+'</option>');
        }
     },
       error: function(){
        alert('服务端异常');
        }
    });   
    var info = new Object();
    var zhf= window.location.href.split('=');
    info.id = zhf[1];
    info.month = "全部时段";
    $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showCheck',
       async: true,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
        $('span#name').text(data.name);
        $('a#progress').attr('href','student_table_progress.html?id='+data.id);//+'&name='+data.name);
        writein(data);
    var docrTable = $('#table-1').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "lengthChange" : false, 
                "pageLength": 10,
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
   //  $.getJSON("js/json/student_table_clock.json", function(data){
   // });
});