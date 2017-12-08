$(document).ready(function(){  
    var info = new Object();
    info.id = window.location.href;
    info.date = "全部时段";
    //ajax
    $.getJSON("js/json/student_table_clock.json", function(data){
        $('a#progress').attr('href','student_table_progress.html?id='+data.id);
        var obj1 = data.month;
        var length1 = data.month.length;
        for(var n=0;n<length1;n++){
            $('select').append('<option>'+obj1[n].month+'</option>');
        }
        var obj2 = data.checkonWorkList;
        var length2 = data.checkonWorkList.length;
        for( var i=0;i<length2;i++){
            var tbody = document.getElementsByTagName ('tbody')[0];
            var tr = tbody.insertRow(tbody.rows.length);
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj2[i].date;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj2[i].gotoWork;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj2[i].comeoffWork;
        }
        //--------筛选-----------
$('option').click(function(){
   var info = new Object();
   info.date= $(this).text();
   // alert(info.date);
//ajax
});
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
   });
});