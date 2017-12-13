$(document).ready(function(){
  var info = new Object();
  info.grade = '年级';
  info.major = '专业';
  info.status = '实习状态';
  info.clss = '班级';
  //---------获取筛选内容
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showScreening',
       async: true,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        var namelength = data.grade.length;
        for ( var i = 0; i < namelength; i++){
        $('select#grade').append('<option>'+data.grade[i]+'</option>');
       }
        var namelength = data.major.length;
        for ( var i = 0; i < namelength; i++){
        $('select#major').append('<option>'+data.major[i]+'</option>');
       }
        var namelength = data.clss.length;
        for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
  $("option").click(function(){
    var info = new Object();
    info.grade = $('select#grade').val();
    info.major = $('select#major').val();
    info.clss = $('select#clss').val();
    info.status = $('select#status').val();
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showStudents',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
      $('#table-stu').dataTable().fnClearTable(); //清除表格内
      $('#table-stu').dataTable().fnDestroy();
      writein(data);
      forbidden();// -------------禁用--------------
      drls();//-------------批量导入------
      addteacher();//-------------单增老师------
      resetpsw();//-------------重置密码------
      checked();//--------------部分选择删除checkbox--------------------------------
      deletestudent();//-----------点击删除（需要表格重新导入）-----------------
      gd();
      var docrTable = $('#table-stu').dataTable({
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
// 更新筛选内容
     $('select#grade').empty();
     $('select#class').empty();
     $('select#major').empty();
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showScreening',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
        var namelength = data.grade.length;
        for ( var i = 0; i < namelength; i++){
        $('select#grade').append('<option>'+data.grade[i]+'</option>');
       }
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
//     $.getJSON("js/json/student_table.json", function(data) {

// });//getjson    
  });
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
//   $.getJSON("js/json/student_table.json", function(data) {

// });
  var info = new Object();
  info.grade = '年级';
  info.major = '专业';
  info.status = '实习状态';
  info.clss = '班级';
//第一次导入表格
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showStudents',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
      writein(data);
      forbidden();// -------------禁用--------------
      drls();//-------------批量导入------
      addteacher();//-------------单增学生------
      resetpsw();//-------------重置密码------
      checked();//--------------部分选择删除checkbox--------------------------------
      deletestudent();//-----------点击删除（需要表格重新导入）-----------------
      gd();
      var docrTable = $('#table-stu').dataTable({
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
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="student_table_details.html?id='+obj.id+'">'+obj.nickName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.userName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.major;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.clss;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_details.html?id='+obj.teacherId+'">'+obj.teacherName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="重置密码" class="reset"  id="'+obj.id+'"><i class="fa fa-repeat fa-2x"></i></a>&nbsp;&nbsp;<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-2x"></i></a>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(7) a.forbidden').css("color","red");
            }
      } //for
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
        info.forbidden =1;
      $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/forbiddenStudent',
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
        info.forbidden =0;
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/forbiddenStudent',
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
});
}

function drls(){
$('button#drls').click(function(){
   if($('input:file').val()==null||$('input:file').val()=='')
   {
    alert('请选择文件');
   }
   else//ajax
   {
    var info =new Object();
    info.excelFile =  new FormData($('#uploadForm')[0]);
    info.roleid = 2;
    $.ajax({
      type: 'post',
      url: '/fieldManagement/admin/uploadStudentExcel',
      cache: false,
      data: info,
      async: true,
      processData: false,
      contentType: false,
      success:function(data){
        if (data.code == "200") {
            alert('导入成功');
            location.reload();
          }
        else{}
     },
       error: function(){
        alert('服务端异常');
        }
    });
 }
});
}
function addteacher(){
$('button#addstudent').click(function(){
  var info = new Object();
  info.nickName = $('input#nickName').val();
  info.userName = $('input#userName').val();
  info.major = $('select#major').val();
  info.clss= $('select#class').val();
  if(info.nickName==null||info.nickName=='')
  {
    alert('请填写姓名');
  }
  else if(info.userName==null||info.userName=='')
  {
    alert('请填写财务工号');
  }
  else//ajax
  {
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/studentRegister',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
         alert('单增成功');
         location.reload();
     },
       error: function(){
        alert('服务端异常');
        }
    });
  }
});
}
function resetpsw(){
$('.reset').click(function(){
   var info = new Object();
   info.id = $(this).attr("id");//alert(info.id);
   info.roleid = "2";
     $.ajax({
       type: 'post',
       url: 'fieldManagement/admin/resetPwd',
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
function deletestudent(){

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
       url: '/fieldManagement/admin/deleteStudent',
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
   // alert(info.id);
   });
}
function gd(){
//-----------点击归档（需要表格重新导入）-----------------
 alert('1');
  $("button#gd").click(function(){
    var id = new Array();
    var a=0;
    $('td>input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {
          id[a]=$(this).parent('td').parent('tr').val();  //alert(id[a]);
          a++;
        }
    });
    var info = new Object();
    info.id=id;
    //ajax
    $('th>input:checkbox').attr('checked',false);
    location.reload();
   // alert(info.id);
   });
}
});//document
