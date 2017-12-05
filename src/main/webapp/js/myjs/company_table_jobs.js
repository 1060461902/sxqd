/*
* @Author: chenzexiao
* @Date:   2017-12-04 19:03:04
* @Last Modified by:   chenzexiao
* @Last Modified time: 2017-12-04 19:51:28
*/
$(document).ready(function(){
	var id =window.location.href;
    $.getJSON("js/json/company_table_jobs.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.recruitmentList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.recruitmentList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_jobs_details.html?id='+obj.id+'">'+obj.post;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_students.html">'+obj.totalNumber+'/'+obj.currentNumber+'</a><i class="fa fa-sort-desc fa-fw"></i>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.startTime+'~'+obj.endTime;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.release_time;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-2x"></i>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(4) a').css("color","red");
            }
      } //for
// -------------禁用--------------
$('.forbidden').click(function(){
// alert($(this).attr("id")+'+'+$(this).attr("value"));
 var info = new Object();
 info.id = $(this).attr("id");
 info.roleId = "5";
 if($(this).attr("value")=='true'){
   var r=confirm("是否解禁该用户");
      if (r==true)
      { 
        info.operationType ="4";
        alert("解禁成功");
        $(this).css("color","#337ab7");
        $(this).attr("value",'false');
      } 
    }
    else if($(this).attr("value")=='false'){
       var r=confirm("是否禁用该用户");
      if (r==true)
      { 
        info.operationType ="4";
        alert("禁用成功");
        $(this).css("color","red");
        $(this).attr("value",'true');
      }
    }
});
      var docrTable = $('#table-job').dataTable({
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