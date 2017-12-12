/*
* @Author: chenzexiao
* @Date:   2017-12-04 19:03:04
* @Last Modified by:   chenzexiao
* @Last Modified time: 2017-12-04 19:51:28
*/
$(document).ready(function(){
  window.companyid = window.location.href;
  var info =new Object();
  info.id = companyid;
  info.month = '招聘时间';
//筛选的一系列操作-------------------------------------------------------------------------
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showRecruitmentScreening',
       async: true,
       data : JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.month.length;
       for ( var i = 0; i < namelength; i++){
        $('select').append('<option>'+data.month[i].screens+'</option>');
       }
       //筛选（需要表格重新导入）
  $("option").click(function(){
    var info = new Object();
    info.month = $(this).text();
    info.id =id;
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyRecruitments',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
     $('#table-job').dataTable().fnClearTable(); //清除表格内
     $('#table-job').dataTable().fnDestroy();
      writein(data);
      forbidden();// -------------禁用--------------
            var docrTable = $('#table-job').dataTable({
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
    });
    //$.getJSON("js/json/approval-2.json", function(data) {
         // });
  });
     },
       error: function(){
        alert('服务端异常');
        }
    });
//第一次导入-------------------------------

    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyRecruitments',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
       writein(data);//写入表格
       forbidden();// -------------禁用--------------
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
     },
       error: function(){
        alert('服务端异常');
        }
    });
//     $.getJSON("js/json/company_table_jobs.json", function(data) {
//       writein(data);//写入表格
//       forbidden();// -------------禁用--------------
// // //-------------下拉---------
// //         $('.showlist').click(function(){
// //             if($(this).parent("td").children("i").hasClass("fa-sort-desc")){
// //                   $(this).parent("td").children("i").removeClass("fa-sort-desc");
// //                   $(this).parent("td").children("i").addClass("fa-sort-up");
// //                   }
// //             else if($(this).parent("td").children("i").hasClass("fa-sort-up")){
// //                   $(this).parent("td").children("i").removeClass("fa-sort-up");
// //                   $(this).parent("td").children("i").addClass("fa-sort-desc");
// //                   }

// //             if($(this).parents('td').find('ul').is(":empty")){//若判断为空 则调用方法
// //                   var x = $(this).parents('tr').index();
// //                   var y =x+1;
// //                   var info = new Object();
// //                   info.id = $(this).parents('tr').val();
// //              $.getJSON("js/json/teacherStudentName.json", function(data) {
// //               var obj2 = data.Names;
// //               var len2 = data.Names.length;
// //               for(var n =0;n<len2;n++){
// //                 $("tr:eq("+y+") td:eq(1)").find('ul').append('<a href="student_table_details.html?id='+obj2[n].id+'">'+obj2[n].name+'</a>&nbsp;');
// //               }
// //              });
// //             }
// //           });
//       var docrTable = $('#table-job').dataTable({
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
//写入表格
function writein(data){
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
            if(obj.currentNumber>0){
            td.innerHTML = obj.currentNumber+'/'+obj.totalNumber;//+'<i class="showlist fa fa-fw fa-sort-desc" data-toggle="collapse" data-target="#demo'+j+'"></i><div id="demo'+j+'" class="collapse"><ul></ul></div>';
          }
            else if(obj.currentNumber == 0){
            td.innerHTML = '0/'+obj.totalNumber;//+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
          }
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.postTime;//startTime+'~'+obj.endTime;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.release_time;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-2x"></i>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(4) a').css("color","red");
            }
      } //for
}
//禁用
function forbidden(){
$('.forbidden').click(function(){
// alert($(this).attr("id")+'+'+$(this).attr("value"));
 var info = new Object();
 info.id = $(this).attr("id");

 if($(this).attr("value")=='true'){
   var r=confirm("是否解禁该用户");
      if (r==true)
      { 
       info.forbidden = 1;
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/forbiddenRecruitment',
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
        info.forbidden = 0;
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/forbiddenRecruitment',
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
    });//ajax
      }
    }
});//click
}
});//document