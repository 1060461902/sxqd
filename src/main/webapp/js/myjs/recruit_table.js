function optionclick(){
    var info = new Object();
    info.statu = $('select#status').val();
    info.companyName = $('select#company').val();
    //info.address = $('select#address').val();
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showRecruitment',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
     $('#table-2').dataTable().fnClearTable(); //清除表格内
     $('#table-2').dataTable().fnDestroy();
      writein(data);
      showlist();//-------------下拉---------
      forbidden();// -------------禁用--------------
      checked();//--------------部分选择删除checkbox---------------
      deleterecruit();//-----------点击删除（需要表格重新导入）-----------------
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
}
function writein(data){
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.RecruitmentList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.RecruitmentList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="recruit_table_details.html?id='+obj.id+'">'+obj.post;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_details.html?id='+obj.companyId+'">'+obj.companyName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            if(obj.currentNumber>0){
            td.innerHTML = +obj.currentNumber+'/'+obj.totalNumber+'<i class="showlist fa fa-fw fa-sort-desc" data-toggle="collapse" data-target="#demo'+j+'"></i><div id="demo'+j+'" class="collapse"><ul></ul></div>';
          }
            else if(obj.currentNumber == 0){
            td.innerHTML = '0/'+obj.totalNumber+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
          }
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.postTime;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.address;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-lg"></i>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(6) a').css("color","red");
            }
      } //for
}
function showlist(){
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
       type: 'get',
       url: '/fieldManagement/admin/showRecruitmentStudent',
       async: true,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
              var obj2 = data.Names;
              var len2 = data.Names.length;
              for(var n =0;n<len2;n++){
                $("tr:eq("+y+")").find('ul').append('<a href="student_table_details.html?id='+obj2[n].id+'">'+obj2[n].studentName+'</a>&nbsp;');
               // alert($("tr:eq("+y+")").val());
              }
     },
       error: function(){
        alert('服务端异常');
        }
    });
             // $.getJSON("js/json/teacherStudentName.json", function(data) {
             // });
            }
          });
}
function forbidden(){
$('.forbidden').click(function(){
// alert($(this).attr("id")+'+'+$(this).attr("value"));
 var info = new Object();
 info.id = $(this).attr("id");
 if($(this).attr("value")=='true'){
   var r=confirm("是否解禁该用户");
      if (r==true)
      { 
        info.forbidden =0;
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/forbiddenRecruitment',
       async: true,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
        alert("解禁成功");
           $('#'+info.id+'.forbidden').css("color","#337ab7");
           $('#'+info.id+'.forbidden').attr("value",'false');
     },
       error: function(){
        alert('服务端异常');
        }
    });
      } 
    }
    else if($(this).attr("value")=='false'){
       var r=confirm("是否禁用该用户");
      if (r==true)
      { 
        info.forbidden =1;
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/forbiddenRecruitment',
       async: true,
       contentType: "application/json",
       data: info,
       dateType: "json",
       success: function(data){
        alert("禁用成功");
           $('#'+info.id+'.forbidden').css("color","red");
           $('#'+info.id+'.forbidden').attr("value",'true');
     },
       error: function(){
        alert('服务端异常');
        }
    });
      }
    }
});  
}
function checked(){
   $("td>input:checkbox").click(function(){
    var mm=0;
    $('input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          mm++;
        }
        if(mm>0)
        {
          $(".operations").css("display","block");
        }
        else
        {
          $(".operations").css("display","none");
        }
    });
   });
}
function deleterecruit(){
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
       type: 'post',
       url: '/fieldManagement/admin/deleteRecruitment',
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
    });
   // alert(info.id);
   });
}
$(document).ready(function(){
  //筛选的一系列操作
    var info = new Object();
    info.status = '招聘状态';
    info.companyName = '招聘公司';
      $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showCompanynames',
          data: info,
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.Names.length;
       for ( var i = 0; i < namelength; i++){
        $('select#company').append('<option value='+data.Names[i].id+'>'+data.Names[i].companyName+'</option>');
       }
       // var namelength = data.Names.length;
       // for ( var i = 0; i < namelength; i++){
       //  $('select#address').append('<option id='+data.Names[i].id+'>'+data.Names[i].companyName+'</option>');
       // }     
       //筛选（需要表格重新导入）
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
  //导入表格
    var info = new Object();
    info.statu = '招聘状态';
    info.companyName = '招聘公司';
    // info.place = '工作地点';
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showRecruitment',
         async: true,
         contentType: "application/json",
         data: JSON.stringify(info),
         dateType: "json",
       success: function(data){
      writein(data);
      showlist();//-------------下拉---------
      forbidden();// -------------禁用--------------
      checked();//--------------部分选择删除checkbox---------------
      deleterecruit();//-----------点击删除（需要表格重新导入）-----------------
      var docrTable = $('#table-2').dataTable({
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
//     $.getJSON("js/json/recruit_table.json", function(data) {
//       writein(data);
//       showlist();//-------------下拉---------
//       forbidden();// -------------禁用--------------
//       checked();//--------------部分选择删除checkbox---------------
//       deleterecruit();//-----------点击删除（需要表格重新导入）-----------------
//       var docrTable = $('#table-2').dataTable({
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
});//document