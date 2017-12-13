$(document).ready(function(){
  //筛选的一系列操作
  var info = new Object();
  info.grade = '年级';
  info.iteacher = '有无指导老师';
  info.status = '实习状态';
  info.clss = '班级';
  info.company = null;
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showRecruitmentScreening',
       async: true,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.companyName.length;
       for ( var i = 0; i < namelength; i++){
        $('select#company').append('<option id='+data.company[i].id+'>'+data.company[i].company+'</option>');
       }
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       var namelength = data.grade.length;
       for ( var i = 0; i < namelength; i++){
        $('select#grade').append('<option>'+data.grade[i]+'</option>');
       }       
       //筛选（需要表格重新导入）
  $("option").click(function(){
    var info = new Object();
    info.grade = $('select#grade').val();
    info.clss = $('select#class').val();    
    info.company = $('select#company').attr('id');
    info.status = $('select.status').val();
    info.teacher = $('select#teacher').val();
      $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showInternships',
       data: JSON.stringify(info),
       async: true,
       contentType: "application/json",
       dateType: "json",
       success: function(data){
     $('#table').dataTable().fnClearTable(); //清除表格内
     $('#table').dataTable().fnDestroy();
      writein(data);
      trclick();//-------------点击---------
      checked();//--------------部分选择操作checkbox----------------------
      //gd();
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
     $('select#class').empty();
     $('select#grade').empty();
     $('select#company').empty();
  //重新获得筛选条件
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showRecruitmentScreening',
       async: true,
       data: JSON.stringify(info),
       contentType: "application/json",
       dateType: "json",
       success: function(data){
       var namelength = data.companyName.length;
       for ( var i = 0; i < namelength; i++){
        $('select#company').append('<option id='+data.company[i].id+'>'+data.company[i].company+'</option>');
       }
       var namelength = data.clss.length;
       for ( var i = 0; i < namelength; i++){
        $('select#class').append('<option>'+data.clss[i]+'</option>');
       }
       var namelength = data.grade.length;
       for ( var i = 0; i < namelength; i++){
        $('select#grade').append('<option>'+data.grade[i]+'</option>');
       }
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
  //--------获取老师的列表
  var info = new Object();
  info.major = '专业';
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showTeachers',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
    var obj = data.teacherList;
    var length = obj.length;
    for (var i = 0; i < length; i++) {
      $('ul').append('<li>'+obj[i].nickName+'</li>');
      $('li:eq('+i+')').val(obj[i].id);
    };
    $('li').click(function(){
      $('span#checkone').html($(this).text()+'<span style="float:right" class="aaa"><i class="fa fa-times-circle fa-fw"></i></span>');
      $('ul').css('display','none');
      $('span#description').text('已分配');
      window.teacherId = $(this).val();
    $('span>span>i').click(function(){
      $('span#description').text('未分配');
      $('span#checkone').empty();
      $('ul').css('display','block');
    });
    });
     },
       error: function(){
        alert('服务端异常');
        }
    });//ajax
  // $.getJSON("js/json/teacher_table.json", function(data) {
  //   var obj = data.teacherList;
  //   var length = obj.length;
  //   for (var i = 0; i < length; i++) {
  //     $('ul').append('<li>'+obj[i].userName+'</li>');
  //     $('li:eq('+i+')').val(obj[i].id);
  //   };
  //   $('li').click(function(){
  //     $('span#checkone').html($(this).text()+'<span style="float:right" class="aaa"><i class="fa fa-times-circle fa-fw"></i></span>');
  //     $('ul').css('display','none');
  //     $('span#description').text('已分配');
  //     window.teacherId = $(this).val();
  //   $('span>span>i').click(function(){
  //     $('span#description').text('未分配');
  //     $('span#checkone').empty();
  //     $('ul').css('display','block');
  //   });
  //   });
  // });
  //提交学生分配
  $('button#distribute').click(function(){
     var id = new Array();
     var a = 0;
    $('td>input:checkbox').each(function() {
        if ($(this).attr('checked') =='checked') {      
          id[a]=$(this).parent('td').parent('tr').val();
          a++;
        }
    });
    var info = new Object();
    info.ids = id;
    info.teacherId = teacherId;
    $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/assignedStudent',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
       alert('提交成功');
       location.reload();
     },
       error: function(){
        alert('服务端异常');
        }
    });
    //ajax
  }); 
  //----------第一次写入表格-------------------
  var info = new Object();
  info.grade = '年级';
  info.iteacher = '有无指导老师';
  info.status = '实习状态';
  info.clss = '班级';
  info.company = null;
     $.ajax({
       type: 'post',
       url: '/fieldManagement/admin/showInternship',
       async: true,
       contentType: "application/json",
       data: JSON.stringify(info),
       dateType: "json",
       success: function(data){
      writein(data);
      trclick();//-------------点击---------
      checked();//--------------部分选择操作checkbox----------------------
      //gd();
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
//     $.getJSON("js/json/internship_table.json", function(data) {

// });//getjson
//------------------->
function writein(data){
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.internship.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.internship[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.studentId);//对当前行赋值
            $('tr').addClass('pointer');
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="student_table_details.html?id="'+obj.studentId+'">'+obj.nickName+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.clss;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="company_table_details.html?id="'+obj.companyId+'>'+obj.company+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.post;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.status;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.stages;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_details.html?id="'+obj.teacherId+'>'+obj.teacherName+'</a>';
      } //for
}
function trclick () {
$('tbody>tr>td').click(function(){
  var col = $(this).index(); // 列位置 
  if(col!=0){
  var href ="./student_table_clock.html?id="+$(this).parent('tr').val();
  location.href=href;
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
       });
        if($('select.status').val()=='待实习'){
            $('button#gg').css("display","none");
           // $('button#gd').css("display","none");
            $('button#fp').css("display","block");
          }
          else if($('select.status').val()=='实习中'){
            $('button#fp').css("display","none");
            //$('button#gd').css("display","none");
            $('button#gg').css("display","block");
          }
          else if($('select.status').val()=='已结业'){
            $('button#fp').css("display","none");
            $('button#gg').css("display","none");
            //$('button#gd').css("display","block");
          }
      }
      else if(m%2==0){
        $('input:checkbox').each(function () {
        $(this).attr('checked',false);
        $('button#gg').css("display","none");
       // $('button#gd').css("display","none");
        $('button#fp').css("display","none");
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
          if($('select.status').val()=='待实习'){
            $('button#gg').css("display","none");
            // $('button#gd').css("display","none");
            $('button#fp').css("display","block");
          }
          else if($('select.status').val()=='实习中'){
            $('button#fp').css("display","none");
            //$('button#gd').css("display","none");
            $('button#gg').css("display","block");
          }
          else if($('select.status').val()=='已结业'){
            $('button#fp').css("display","none");
            $('button#gg').css("display","none");
           // $('button#gd').css("display","block");
          }
        }
        else
        {
        $('button#gg').css("display","none");
       // $('button#gd').css("display","none");
        $('button#fp').css("display","none");
        }
   });
}

});//document