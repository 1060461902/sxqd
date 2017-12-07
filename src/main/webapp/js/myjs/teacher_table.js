$(document).ready(function(){
  var info = new Object();
  info.major = null;
  $.getJSON("js/json/teacher_table_name.json", function(data) {
        var namelength = data.majorNameList.length;
        for ( var i = 0; i < namelength; i++){
        $('select').append('<option>'+data.majorNameList[i].majorName+'</option>');
       }
  });
    $.getJSON("js/json/teacher_table.json", function(data) {
       var tbody = document.getElementsByTagName ('tbody')[0];
       var len = data.teacherList.length;
       for ( var i = 0; i < len; i++)
      {
          var obj = data.teacherList[i];
            var tr = tbody.insertRow(tbody.rows.length);
            var j=i+1;
            $("tr:eq("+j+")").val(obj.id);//对当前行赋值
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<input type="checkbox">';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_details.html?id='+obj.id+'">'+obj.nickName;+'</a>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.userName;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = obj.major;
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML = '<a href="teacher_table_students.html?id="'+obj.id+'>'+obj.count+'</a><i class="showlist fa fa-fw fa-sort-desc"></i>';
            var td = tr.insertCell (tr.cells.length);
            td.innerHTML='<a href="#" title="重置密码" class="reset"  id="'+obj.id+'"><i class="fa fa-repeat fa-2x"></i></a>';
            // if(obj.forbidden==true){
            //   $('tr:eq('+j+') td:eq(6) a').css("color","red");
            // }
      } //for
//--------筛选-----------
$('option').click(function(){
   var info = new Object();
   info.major= $(this).text();
//ajax
});
//-------------下拉---------
              $('.showlist').click(function(){
                if($(this).parent("td").children("i").attr("class")=="showlist fa fa-fw fa-sort-desc"){
                  $(this).parent("td").parent("tr").css("height","500px");
                  $(this).parent("td").children("i").removeClass("fa-sort-desc");
                  $(this).parent("td").children("i").addClass("fa-sort-up");
                  }
                else if($(this).parent("td").children("i").attr("class")=="showlist fa fa-fw fa-sort-up"){
                  $(this).parent("td").parent("tr").css("height","44px");
                  $(this).parent("td").children("i").removeClass("fa-sort-up");
                  $(this).parent("td").children("i").addClass("fa-sort-desc");
                  }

              });
//-------------批量导入------
$('button#drls').click(function(){
   if($('input:file').val()==null||$('input:file').val()=='')
   {
    alert('请选择文件');
   }
   else//ajax
   {
    $.ajax({
      type: 'post',
      url: '',
      cache: false,
      data: new FormData($('#uploadForm')[0]),
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
//-------------单增老师------
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
  alert('单增成功');
  location.reload();
  }
});
//-------------重置密码------
$('.reset').click(function(){
   var info = new Object();
   info.id = $(this).attr("id");//alert(info.id);
   info.operationType ="1";
   info.roleId = "3";
   alert('重置成功');
});
// -------------禁用--------------
// $('.forbidden').click(function(){
// // alert($(this).attr("id")+'+'+$(this).attr("value"));
//  var info = new Object();
//  info.id = $(this).attr("id");
//  info.roleId = "4";
//  if($(this).attr("value")=='true'){
//    var r=confirm("是否解禁该用户");
//       if (r==true)
//       { 
//         info.operationType ="4";
//         alert("解禁成功");
//         $(this).css("color","#337ab7");
//         $(this).attr("value",'false');
//       } 
//     }
//     else if($(this).attr("value")=='false'){
//        var r=confirm("是否禁用该用户");
//       if (r==true)
//       { 
//         info.operationType ="2";
//         alert("禁用成功");
//         $(this).css("color","red");
//         $(this).attr("value",'true');
//       }
//     }
// });
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
//-----------点击删除（需要表格重新导入）-----------------
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
    alert("删除成功");
    $('th>input:checkbox').attr('checked',false);
    location.reload();
   // alert(info.id);
    //<---------------------------------------表格重新导入
   });
      var docrTable = $('#table-teacher').dataTable({
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