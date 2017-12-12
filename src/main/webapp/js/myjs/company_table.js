$(document).ready(function(){
  // 导入筛选以及筛选操作
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.Names.length;
       for ( var i = 0; i < namelength; i++){
        $('select').append('<option id='+data.Names[i].id+'>'+data.Names[i].companyName+'</option>');
       }
 $("option").click(function(){
    var type = $(this).text();
    var info = new Object();
    info.companyName = type;
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanies',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
       $('#table-3').dataTable().fnClearTable(); //清除表格内
       $('#table-3').dataTable().fnDestroy();
       writein(data);//写入表格
       fold();//下拉
       resetpsw();//-------------重置密码------
       forbidden();// -------------禁用--------------
       checked();//选择部分
       deleted();//-----------点击删除（需要表格重新导入）-----------------
      var docrTable = $('#table-3').dataTable({
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
    });//ajax
     //  $.getJSON("js/json/company_table.json", function(data) {
     //   $('#table-3').dataTable().fnClearTable(); //清除表格内
     //   $('#table-3').dataTable().fnDestroy();
     //   writein(data);//写入表格
     //   fold();//下拉
     //   resetpsw();//-------------重置密码------
     //   forbidden();// -------------禁用--------------
     //   checked();//选择部分
     //   deleted();//-----------点击删除（需要表格重新导入）-----------------
     //  var docrTable = $('#table-3').dataTable({
     //            "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
     //            "bFilter" : true, //是否启动过滤、搜索功能
     //            "info": false,
     //             "pageLength": 8,
     //            "lengthChange" : false, 
     //              "oLanguage": { //国际化配置
     //                "sProcessing" : "正在获取数据，请稍后...",
     //                "sLengthMenu" : "显示 _MENU_ 条",
     //                "sZeroRecords" : "没有您要搜索的内容",
     //                "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
     //                "sInfoEmpty" : "记录数为0",
     //                "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
     //                "sInfoPostFix" : "",
     //                "sSearch" : "搜索",
     //                "sUrl" : "",
     //                "oPaginate": {
     //                    "sFirst" : "第一页",
     //                    "sPrevious" : "上一页",
     //                    "sNext" : "下一页",
     //                    "sLast" : "最后一页"
     //                }
     //            },
     //          });
     // });
   }); 
     },
       error: function(){
        alert('服务端异常');
        }
    });

 //    $.getJSON("js/json/approval-2-companyname.json", function(data) {
 //       var namelength = data.Names.length;
 //       for ( var i = 0; i < namelength; i++){
 //        $('select').append('<option id='+data.Names[i].id+'>'+data.Names[i].companyName+'</option>');
 //       }
 // $("option").click(function(){
 //    var type = $(this).attr("id");
 //    var info = new Object();
 //    info.companyName = type;
 //      $.getJSON("js/json/company_table.json", function(data) {
 //       $('#table-3').dataTable().fnClearTable(); //清除表格内
 //       $('#table-3').dataTable().fnDestroy();
 //       writein(data);//写入表格
 //       fold();//下拉
 //       resetpsw();//-------------重置密码------
 //       forbidden();// -------------禁用--------------
 //       checked();//选择部分
 //       deleted();//-----------点击删除（需要表格重新导入）-----------------
 //      var docrTable = $('#table-3').dataTable({
 //                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
 //                "bFilter" : true, //是否启动过滤、搜索功能
 //                "info": false,
 //                 "pageLength": 8,
 //                "lengthChange" : false, 
 //                  "oLanguage": { //国际化配置
 //                    "sProcessing" : "正在获取数据，请稍后...",
 //                    "sLengthMenu" : "显示 _MENU_ 条",
 //                    "sZeroRecords" : "没有您要搜索的内容",
 //                    "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
 //                    "sInfoEmpty" : "记录数为0",
 //                    "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
 //                    "sInfoPostFix" : "",
 //                    "sSearch" : "搜索",
 //                    "sUrl" : "",
 //                    "oPaginate": {
 //                        "sFirst" : "第一页",
 //                        "sPrevious" : "上一页",
 //                        "sNext" : "下一页",
 //                        "sLast" : "最后一页"
 //                    }
 //                },
 //              });          
 //     });
 //   });       
 //  });

//   $.getJSON("js/json/company_table.json", function(data) {
//     writein(data);//写入表格
//     fold();//下拉
//     resetpsw();//-------------重置密码------
//     forbidden();// -------------禁用--------------
//     checked();//选择部分
//     deleted();//-----------点击删除（需要表格重新导入）-----------------
//       var docrTable = $('#table-3').dataTable({
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
// -------------第一次写入表格 
  var info =new Object();
  info.number = '实习学生人数';
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanies',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
    writein(data);//写入表格
    fold();//下拉
    resetpsw();//-------------重置密码------
    forbidden();// -------------禁用--------------
    checked();//选择部分
    deleted();//-----------点击删除（需要表格重新导入）-----------------
      var docrTable = $('#table-3').dataTable({
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
function writein(data){
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.compamyViewList.length;
       var j =0;
       for ( var i = 0; i < len; i++)
      {   var j = i+1;
          var obj = data.compamyViewList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_details.html?id='+obj.id+'">'+obj.companyName;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_jobs.html?id='+obj.id+'">'+obj.nowIntership+'/'+obj.allIntership+'</a>';
            var td = tr.insertCell (tr.cells.length);
            if(obj.studentNumber>0){
              td.innerHTML = '<a href="company_table_students.html?id='+obj.id+'">'+obj.studentNumber+'</a><i class="showlist fa fa-fw fa-sort-desc" data-toggle="collapse" data-target="#demo'+j+'"></i><div id="demo'+j+'" class="collapse"><ul></ul></div>';
            }
            else{
              td.innerHTML = 0+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
            }
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.contact;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="重置密码" class="reset"  id="'+obj.id+'"><i class="fa fa-repeat fa-lg"></i></a>&nbsp;&nbsp;<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-lg"></i></a>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(5) a.forbidden').css("color","red");
            }
      } //for
}
//下拉
function fold(){
        $('.showlist').click(function(){
            if($(this).parent("td").children("i").hasClass("fa-sort-desc")){
                  $(this).parent("td").children("i").removeClass("fa-sort-desc");
                  $(this).parent("td").children("i").addClass("fa-sort-up");
                  }
            else if($(this).parent("td").children("i").hasClass("fa-sort-up")){
                  $(this).parent("td").children("i").removeClass("fa-sort-up");
                  $(this).parent("td").children("i").addClass("fa-sort-desc");
                  }
            if($(this).parents('td').find('ul').is(":empty")){//若判断为空 则调用方法
                  var x = $(this).parents('tr').index();
                  var y =x+1;
                  var info = new Object();
                  info.id = $(this).parents('tr').val();
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showCompanyStudentName',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
              var obj2 = data.Names;
              var len2 = data.Names.length;
              for(var n =0;n<len2;n++){
                $("tr:eq("+y+")").find('ul').append('<a href="student_table_details.html?id='+obj2[n].id+'">'+obj2[n].name+'</a>&nbsp;');
              }
     },
       error: function(){
        alert('服务端异常');
        }
    });
             // $.getJSON("js/json/teacherStudentName.json", function(data) {
             //  var obj2 = data.Names;
             //  var len2 = data.Names.length;
             //  for(var n =0;n<len2;n++){
             //    $("tr:eq("+y+")").find('ul').append('<a href="student_table_details.html?id='+obj2[n].id+'">'+obj2[n].name+'</a>&nbsp;');
             //  }
             // });//getJson
            }
          });
}
//重置密码
function resetpsw(){
$('.reset').click(function(){
   var info = new Object();
   info.id = $(this).attr("id");//alert(info.id);
   info.roleId = "4";
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/resetPwd',
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
    });
 });
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
       type: 'get',
       url: '/fieldManagement/admin/forbiddenCompany',
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
       url: '/fieldManagement/admin/forbiddenCompany',
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
    });
      }
    }
});
}
//--------------------------全选checkbox--------------------------
var m=0;
$('th>input:checkbox').click(function() {
      m+=1;
      if(m%2==1){
        $('input:checkbox').each(function() {
        $(this).attr('checked', true);
        $(".operations").css("display","block");
       });
      }
      else if(m%2==0){
        $('input:checkbox').each(function () {
        $(this).attr('checked',false);
        $(".operations").css("display","none");
});
      }
});
//--------------部分选择删除checkbox--------------------------------
function checked(){
   $("td>input:checkbox").click(function(){
    var mm=0;
    $('input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          mm++;
        }
    });
        if(mm>0)
        {
          $(".operations").css("display","block");
        }
        else
        {
          $(".operations").css("display","none");
        }

   });
}
//----------------删除---------------------
function deleted(){
  $(".operations").click(function(){
    var id = new Array();
    var a=0;
    $('td>input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          id[a]=$(this).parent('td').parent('tr').val();
          a++;
        }
    });
    var info = new Object();
    info.id=id;
    $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/deleteCompany',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
    alert("删除成功");
    $('th>input:checkbox').attr('checked',false);
    location.reload();
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
   });
}
});//document