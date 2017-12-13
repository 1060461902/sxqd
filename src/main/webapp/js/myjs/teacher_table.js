$(document).ready(function(){
  //---------------获得专业名称---------------------------
    $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/teacherMajor',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.major.length;
       for ( var i = 0; i < namelength; i++){
        $('select').append('<option>'+data.major[i].major+'</option>');
       }
  $("option").click(function(){
    var info = new Object();
    info.major = $(this).text();
      $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showTeachers',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
      //$.getJSON("js/json/teacher_table.json", function(data) {     
     $('#table-teacher').dataTable().fnClearTable(); //清除表格内
     $('#table-teacher').dataTable().fnDestroy();
      writein(data);alert('1');
      showlist();//-------------下拉---------
      drls();//-------------批量导入------
      addteacher();//-------------单增老师------
      resetpsw();//-------------重置密码------
      forbidden();// -------------禁用--------------
      checked();//--------------部分选择删除checkbox--------------------------------
      deleteteacher();//-----------点击删除（需要表格重新导入）-----------------
            var docrTable = $('#table-teacher').dataTable({
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
         // });
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
  });
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax

  // $.getJSON("js/json/teacher_table_name.json", function(data) {
  //      var namelength = data.major.length;
  //      for ( var i = 0; i < namelength; i++){
  //       $('select').append('<option>'+data.major[i].major+'</option>');
  //      }
  //});
  var info = new Object();
  info.major = '专业';
  //第一次导入表格------------------------
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/showTeachers',
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
      writein(data);
      showlist();//-------------下拉---------
      drls();//-------------批量导入------
      addteacher();//-------------单增老师------
      resetpsw();//-------------重置密码------
      forbidden();// -------------禁用--------------
      checked();//--------------部分选择删除checkbox--------------------------------
      deleteteacher();//-----------点击删除（需要表格重新导入）-----------------
        var docrTable = $('#table-teacher').dataTable({
                "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示   
                "bFilter" : true, //是否启动过滤、搜索功能
                "info": false,
                 "pageLength": 6,
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
//    $.getJSON("js/json/teacher_table.json", function(data) {

//});//getjson
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
//------------------->
function writein(data){
      var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.teacherList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.teacherList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            window.j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            //alert($("tr:eq("+j+")").val());
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_details.html?id='+obj.id+'">'+obj.nickName;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.userName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.major;
            var td = tr.insertCell (tr.cells.length);
            if(obj.count>0){
              td.innerHTML = '<a href="teacher_table_students.html?id="'+obj.id+'>'+obj.count+'</a><i class="showlist fa fa-fw fa-sort-desc" data-toggle="collapse" data-target="#demo'+j+'"></i><div id="demo'+j+'" class="collapse"><ul></ul></div>';
            }
            else if(obj.count==0)
            {
              td.innerHTML = 0+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
            }
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="重置密码" class="reset"  id="'+obj.id+'"><i class="fa fa-repeat fa-2x"></i></a>&nbsp;&nbsp;<a href="#" title="禁用/解禁" class="forbidden" id="'+obj.id+'" value='+obj.forbidden+'><i class="fa fa-ban fa-2x"></i></a>';
            if(obj.forbidden==true){
              $('tr:eq('+j+') td:eq(6) a').css("color","red");
            }
      } //for
};
function showlist () {
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
       url: '/fieldManagement/admin/teacherStudentName',
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
    });//ajax
             // $.getJSON("js/json/teacherStudentName.json", function(data) {

             // });
            }//if
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
    var info = new Object();
    info.excelFile = new FormData($('#uploadForm')[0]);
    info.roleId = 3;
    $.ajax({
      type: 'get',
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
$('button#addteacher').click(function(){
  var info = new Object();
  info.nickName = $('input#nickName').val();
  info.userName = $('input#userName').val();
  info.major = $('input#major').val();
  if(info.nickName==null||info.nickName=='')
  {
    alert('请填写姓名');
  }
  else if(info.userName==null||info.userName=='')
  {
    alert('请填写财务工号');
  }
  else if(info.major==null||info.major=='')
  {
    alert('请填写专业');
  }
  else//ajax
  {
     $.ajax({
       type: 'get',
       url: '/fieldManagement/admin/teacherRegister',
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
    });//ajax
  }
});
}
function resetpsw(){
$('.reset').click(function(){
   var info = new Object();
   info.id = $(this).attr("id");//alert(info.id);
   info.roleid = "3";
     $.ajax({
       type: 'get',
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
       url: '/fieldManagement/admin/forbiddenTeacher',
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
       type: 'get',
       url: '/fieldManagement/admin/forbiddenTeacher',
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
function deleteteacher(){
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
       url: '/fieldManagement/admin/deleteTeacher',
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
    //<---------------------------------------表格重新导入
   });
} 
});//document